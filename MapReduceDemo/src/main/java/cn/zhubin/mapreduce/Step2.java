package cn.zhubin.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.yarn.webapp.ResponseInfo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 构建用户矩阵
 *
 *
 * 15274464875  137570  play
 * 15274464875  137570  download
 * 15274464875  395126  play
 *
 */
public class Step2 {

    public static boolean run(Configuration conf, Map<String,String> paths){

        try {

            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);

            job.setJobName("Step2");
            job.setJarByClass(Step2.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(Text.class);

            job.setMapperClass(Step2_Mapper.class);
            job.setReducerClass(Step2_Reducer.class);

            FileInputFormat.addInputPath(job,new Path(paths.get("Step2Input")));

            Path outpath = new Path(paths.get("Step2Output"));
            if(fs.exists(outpath)){
                fs.delete(outpath);
            }

            FileOutputFormat.setOutputPath(job,outpath);

            boolean f =  job.waitForCompletion(true);
            System.out.println("success");
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


    /**
     * 进来的数据
     * 15274464875  137570  play
     * 15274464875  137570  download
     * 15274464875  395126  play
     */
    static class Step2_Mapper extends Mapper<LongWritable, Text,Text,Text>{

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String[] tokens = StringUtils.split(value.toString(),'\t');

            String user = tokens[0].trim();
            String music = tokens[1].trim();
            String action = tokens[2].trim();
            int sorce = StartRun.R.get(action);

            context.write(new Text(user),new Text(music+":"+sorce));

        }
    }

    /**
     * mapper端过来的数据
     * 15274464875  137570:1
     * 15274464875  137570:3
     * 15274464875  395126:1

     */

    static class Step2_Reducer extends Reducer<Text,Text,Text, Text>{

        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            Map<String,Integer> r = new HashMap<String, Integer>();

            for(Text val:values){

                String tokens[] = StringUtils.split(val.toString(),':');
                String music = tokens[0];
                int sorce = Integer.parseInt(tokens[1]);
                sorce =  ((Integer)(r.get(music) == null ? 0 : r.get(music))).intValue() + sorce;

                r.put(music,sorce);

            }

            StringBuilder sb = new StringBuilder();
            for(Map.Entry<String,Integer> entry : r.entrySet()){
                sb.append(entry.getKey()+":"+entry.getValue()+",");
            }

            context.write(key,new Text(sb.toString()));
            //输出数据  15274464875  137570:3,395126:1,
        }

    }

}
