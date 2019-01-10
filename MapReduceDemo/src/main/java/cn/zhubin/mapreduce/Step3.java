package cn.zhubin.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
 *
 * 构建同现矩阵
 *
 */
public class Step3 {

    public static boolean run(Configuration conf, Map<String,String> paths){

        try {

            FileSystem fs = FileSystem.get(conf);
            Job job = Job.getInstance(conf);

            job.setJobName("Step3");
            job.setJarByClass(Step3.class);

            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setMapperClass(Step3_Mapper.class);
            job.setReducerClass(Step3_Reducer.class);

            FileInputFormat.addInputPath(job,new Path(paths.get("Step3Input")));

            Path outpath = new Path(paths.get("Step3Output"));
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

    /**
     *
     * 输入数据
     *
     * 15274464875  137570:3,395126:1,332126:2
     *
     * 排列组合
     * 输出
     * 137570:395126 1
     * 137570:332126 1
     * 395126:395126 1
     *
     */
     static class Step3_Mapper extends Mapper<LongWritable, Text,Text, IntWritable>{

        protected void map(LongWritable key,Text value, Context context) throws IOException, InterruptedException {

            String[] tokens = StringUtils.split(value.toString(),'\t');
            String[] v = StringUtils.split(tokens[1],',');
            for(int i = 0; i < v.length ; i++){
                String musicA = StringUtils.split(v[i],':')[0];
                for(int j = 0; j < v.length; j++){
                    String musicB = StringUtils.split(v[j],':')[0];
                    context.write(new Text(musicA+":"+musicB),new IntWritable(1));

                }
            }

        }

    }

    /**
     * 输入数据
     * 137570:395126 1
     * 137570:395126 1
     * 137570:395126 1
     *
       137570:332126 1
     * 395126:395126 1
     *
     * 输出
     * 137570:395126 3
     * 137570:332126 1
     * 395126:395126 1
     *
     */
    static class Step3_Reducer extends Reducer<Text,IntWritable,Text,IntWritable>{

        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            context.write(key,new IntWritable(sum));

        }

    }


}
