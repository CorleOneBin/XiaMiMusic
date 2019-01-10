package cn.zhubin.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 矩阵相乘
 */
public class Step4 {

    public static boolean run(Configuration conf, Map<String,String> paths){

        try {

            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);

            job.setJobName("Step4");
            job.setJarByClass(Step3.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setMapperClass(Step4_Mapper.class);
            job.setReducerClass(Step4_Reducer.class);

            FileInputFormat.addInputPath(job,new Path(paths.get("Step4Input1")));
            FileInputFormat.addInputPath(job,new Path(paths.get("Step4Input2")));

            Path outpath = new Path(paths.get("Step4Output"));
            if(fs.exists(outpath)){
                fs.delete(outpath);
            }

            FileOutputFormat.setOutputPath(job,outpath);

            boolean f =  job.waitForCompletion(true);
            return f;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    static class Step4_Mapper extends Mapper<LongWritable, Text, Text, Text> {

        private String flag;

        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit split = (FileSplit) context.getInputSplit();
            flag = split.getPath().getParent().getName();
        }

        /**
         * 同现 434534:54345 2
         *    434534:23424 3
         *    432432:12323 2
         *
         * 用户评分 15274464875 434523:2,43434:3
         */
        protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {

            String[] tokens = Pattern.compile("[\t,]").split(value.toString());

            if ("step3".equals(flag)) {
                /**
                 *  样本 434334:43423 2
                 *      345233:23443 1
                 */
                String[] vs = tokens[0].split(":");
                String item1 = vs[0];
                String item2 = vs[1];
                String num = tokens[1];
                context.write(new Text(item1), new Text("A:" + item2 + "," + num));
            } else if ("step2".equals(flag)) {
                /**
                 * 样本 15274464875 434523:2,43434:3
                 */
                String user = tokens[0];
                for (int i = 1; i < tokens.length; i++) {
                    String[] vector = tokens[i].split(":");
                    String item = vector[0];
                    String num = vector[1];
                    context.write(new Text(item), new Text("B:" + user + "," + num));
                    //
                }
            }
        }

    }

    static class Step4_Reducer extends Reducer<Text, Text, Text, Text> {

        protected void reduce(Text key, Iterable<Text> iterable, Context context)
                throws IOException, InterruptedException {

            // 用来放一个物品（key）对应的其他所有物品的同现次数
            Map<String, Integer> mapa = new HashMap<String, Integer>();

            // 用来放一个物品(key) ， 对应的所有用户对其的推荐权重分数
            Map<String, Integer> mapb = new HashMap<String, Integer>();
            /**
             * 示例 423121 A:43221,1
             *
             *    423121 B:15274465875,2
             */
            for (Text text : iterable) {
                String line = text.toString();
                if (line.startsWith("A:")) {
                    String[] kv = StringUtils.split(line.substring(2), ',');
                    mapa.put(kv[0], Integer.parseInt(kv[1]));
                } else {
                    if (line.startsWith("B:")) {
                        String[] kv = StringUtils.split(line.substring(2), ',');
                        mapb.put(kv[0], Integer.parseInt(kv[1]));
                    }
                }
            }

            double result = 0;
            Iterator<String> iter = mapa.keySet().iterator();
            while (iter.hasNext()) {
                String item = iter.next();
                int num = mapa.get(item);
                Iterator<String> iterb = mapb.keySet().iterator();
                while (iterb.hasNext()) {
                    String user = iterb.next();
                    double pref = mapb.get(user);
                    result = num * pref;
                    context.write(new Text(user), new Text(item + "," + result));
                    // 15274464875 432123,9.0              矩阵每一行相乘的结果，还要相加
                }
            }
        }
    }

}
