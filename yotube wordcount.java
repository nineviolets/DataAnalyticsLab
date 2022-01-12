import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {

  public static class TokenizerMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, one);
      }
    }
  }

  public static class IntSumReducer
       extends Reducer<Text,IntWritable,Text,IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "word count");
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(IntSumReducer.class);
    job.setReducerClass(IntSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}


***********************************************************************
steps 
save code in desktop in a folder(wordcount)
create another folder(input_data) in wordcount folder
create new document (empty folder) name it as (input data)
  
  **************
  enter this
  Mohammed
  Omar
  Sheeha
  Shila
  Pallo
  Gfrd
  Isthuna
  Hamida
  Ram
  Jess
  Sunu (repeate some names)
  ****************
  in wordcount folder create another file(classees)
  
  export hadoop_classpath=$(hadoop classpath)
  echo $hadoop_classpath
  hadoop fs -mkdf /wordcount
  hadoop fs -mkdir /wordcount
  
  
  goto localhost:50070
  hadoop fs -put(input_data) file ) drage and drop /wordcount/input
  
  goto localhost -> utilities 
  in terminal 
  cd (drag and drop word count)
  clear
  
  
  javac -classpath ${hadoop_classpath}
-d drag and drop classes and wordcoud.java file file into terminal

  check the classes file and dra and drop all three files into terminal
  jar -cvf firsttutorial.jar -C tutorial_classes/.
  
  
  hadoop jar
  drag drop firsttutoal fileinto terminal
