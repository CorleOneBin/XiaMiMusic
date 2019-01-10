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

import java.io.IOException;
import java.util.Map;

/**
 * 进行一个数据的去重
 */
public class Step1 {

    public static boolean run(Configuration conf, Map<String,String> paths){

        try {

            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);
            job.setJobName("step1");
            job.setJarByClass(Step1.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(NullWritable.class);

            job.setMapperClass(Step1_Mapper.class);
            job.setReducerClass(Step1_Reducer.class);

            FileInputFormat.addInputPath(job,new Path(paths.get("Step1Input")));

            Path outPath = new Path(paths.get("Step1Output"));

            if(fs.exists(outPath)){
                fs.delete(outPath);
            }

            FileOutputFormat.setOutputPath(job,outPath);
            boolean f = job.waitForCompletion(true);
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

    static class Step1_Mapper extends Mapper<LongWritable,Text, Text, NullWritable>{

        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            if(key.get() != -1){
                context.write(new Text(value),NullWritable.get());
            }
        }
    }

    /**
     * 进来的样本
     * 做到
     *
     * 2019-01-09 22:23:28 - 15274464875 - 137570 - play
     * 2019-01-09 22:23:33 - 15274464875 - 137570 - download
     * 2019-01-09 22:23:49 - 15274464875 - 395126 - play
     *
     */
    static class Step1_Reducer extends Reducer<Text, NullWritable,Text,NullWritable>{

        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

            String[] tokens = StringUtils.split(key.toString(),'-');
            String user = tokens[3];
            String item = tokens[4];
            String action = tokens[5];

            context.write(new Text(user +"\t"+item+"\t"+action),NullWritable.get());

        }

    }


}
