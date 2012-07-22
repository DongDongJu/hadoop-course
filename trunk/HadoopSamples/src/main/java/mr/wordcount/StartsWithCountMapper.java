package mr.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class StartsWithCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	private final static IntWritable countOne = new IntWritable(1);
	private final Text reusableText = new Text();
	Logger log = Logger.getLogger(StartsWithCountMapper.class);
	@Override	
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		StringTokenizer tokenizer = new StringTokenizer(value.toString());
		while (tokenizer.hasMoreTokens()) {
			reusableText.set(tokenizer.nextToken().substring(0, 1));
			log.info("Emitting: [" + reusableText + "-" + countOne + "]");
			context.write(reusableText, countOne);
		}
	}
}