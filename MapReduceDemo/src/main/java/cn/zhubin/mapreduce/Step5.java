package cn.zhubin.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 将矩阵相乘的值相加，得到最终的结果举证
 */
public class Step5 {
    public static boolean run(Configuration config, Map<String,String> paths) {
        try {
            FileSystem fs = FileSystem.get(config);
            Job job = Job.getInstance(config);

            job.setJarByClass(Step5.class);
            job.setJobName("Step5");

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setMapperClass(Step5_Mapper.class);
            job.setReducerClass(Step5_Reducer.class);

            FileInputFormat.addInputPath(job, new Path(paths.get("Step5Input")));
            Path outPath = new Path(paths.get("Step5Output"));
            if(fs.exists(outPath)) {
                fs.delete(outPath);
            }

            FileOutputFormat.setOutputPath(job, outPath);

            boolean f = job.waitForCompletion(true);

            return f;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    static class Step5_Mapper extends Mapper<LongWritable, Text, Text, Text> {

        // 123 432123,9.0
        protected void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            String[] tokens = Pattern.compile("[\t]").split(value.toString());
            context.write(new Text(tokens[0]), new Text(tokens[1]));
        }
    }

    static class Step5_Reducer extends Reducer<Text, Text, Text, Text> {

        protected void reduce(Text key, Iterable<Text> iterable, Context conotext)
                throws IOException, InterruptedException {

            Map<String,Double> map = new HashMap<String,Double>();

            // 123 432123,9.0
            for(Text text : iterable) {
                String[] tokens = StringUtils.split(text.toString(), ',');
                String item = tokens[0];
                double pref = Double.parseDouble(tokens[1]);
                if(map.containsKey(item)) {
                    map.put(item, map.get(item)+pref);
                }else {
                    map.put(item, pref);
                }
            }

            for(Map.Entry<String, Double> entry : map.entrySet()) {
                conotext.write(new Text(key), new Text(entry.getKey() + "," + entry.getValue()));
            }
            //  123  31234,10.0           最终相加的一个值
        }

    }

}

