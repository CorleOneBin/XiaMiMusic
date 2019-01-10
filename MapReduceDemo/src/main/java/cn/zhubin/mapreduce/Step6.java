package cn.zhubin.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 排序，推荐十个物品给每个用户
 * @author BIN
 *
 */
public class Step6{

    public static boolean run(Configuration config , Map<String,String> paths) {
        try {
            FileSystem fs = FileSystem.get(config);
            Job job = Job.getInstance(config);

            job.setJarByClass(Step6.class);
            job.setJobName("Step6");

            job.setMapOutputKeyClass(PairWritable.class);
            job.setMapOutputValueClass(Text.class);

            job.setMapperClass(Step6_Mapper.class);
            job.setReducerClass(Step6_Reducer.class);
            job.setSortComparatorClass(Sort.class);
            job.setGroupingComparatorClass(Group.class);


            FileInputFormat.addInputPath(job, new Path(paths.get("Step6Input")));
            Path outPath = new Path(paths.get("Step6Output"));
            if(fs.exists(outPath)) {
                fs.delete(outPath);
            }
            FileOutputFormat.setOutputPath(job, outPath);
            boolean f = job.waitForCompletion(true);
            return f;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 样本  15274464875	43223,1.0
     * @author BIN
     *
     */
    static class Step6_Mapper extends Mapper<LongWritable, Text, PairWritable, Text>{

        protected void map(LongWritable key, Text value, Mapper.Context context)
                throws IOException, InterruptedException {
            String[] tokens = Pattern.compile("[\t,]").split(value.toString());
            PairWritable p = new PairWritable();
            String user = tokens[0];
            String item = tokens[1];
            double num = Double.parseDouble(tokens[2]);
            p.setUid(user);
            p.setNum(num);
            context.write(p, new Text(item+":"+num));
        }

    }
    /**
     * 进来的是用户名相同的，排好序的数据，然后取前十个拼成一行输出
     * @author BIN
     *
     */
    static class Step6_Reducer extends Reducer<PairWritable, Text, Text, Text> {

        protected void reduce(PairWritable key, Iterable<Text> iterable,
                              Context context) throws IOException, InterruptedException {
            int flag = 0;
            StringBuilder sb = new StringBuilder();
            for(Text t : iterable) {
                if(flag > 10)
                    break;
                sb.append(t.toString()+",");
            }
            context.write(new Text(key.getUid()), new Text(sb.toString()));
        }

    }



    /**
     * 按照推荐度降序排序
     * @author BIN
     *
     */
    static class Sort extends WritableComparator {

        public Sort() {
            super(PairWritable.class,true);
        }

        public int compare(WritableComparable a, WritableComparable b) {
            PairWritable p1 = (PairWritable)a;
            PairWritable p2 = (PairWritable)b;
            int c1 = p1.getUid().compareTo(p2.getUid());
            if(c1 == 0) {
                return -Double.compare(p1.getNum(), p1.getNum());
            }
            return c1;
        }

    }

    /**
     * 按照用户名分组
     * @author BIN
     *
     */
    static class Group extends WritableComparator{
        public Group() {
            super(PairWritable.class,true);
        }

        public int compare(WritableComparable a, WritableComparable b) {
            PairWritable p1 = (PairWritable)a;
            PairWritable p2 = (PairWritable)b;
            int c1 = p1.getUid().compareTo(p2.getUid());
            return c1;
        }
    }

    static class PairWritable implements WritableComparable<PairWritable>{

        private String uid;
        private double num;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public double getNum() {
            return num;
        }

        public void setNum(double num) {
            this.num = num;
        }

        public void readFields(DataInput in) throws IOException {
            this.uid = in.readUTF();
            this.num = in.readDouble();
        }

        public void write(DataOutput out) throws IOException {
            out.writeUTF(uid);
            out.writeDouble(num);
        }

        public int compareTo(PairWritable o) {
            int p1 = this.getUid().compareTo(o.getUid());
            if(p1 == 0) {
                return Double.compare(this.getNum(), o.getNum());
            }
            return p1;
        }

    }
}