import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, Text>{
	public void map(LongWritable key, Text value, Context context) throws
	IOException, InterruptedException{
				
		String[] cLineArray;
		String[] author;
		String[] date;
		String line;
		String[] tokens;
		String kv_pairs;

		String currentLine = null;
		currentLine = value.toString();
		
		cLineArray = currentLine.toString().split("<===>");
		author = cLineArray[0].toString().split(" ");
		date = cLineArray[1].toString().split(" ");
		line = cLineArray[2].toLowerCase();
		//System.out.println(line);
		line = line.replaceAll("[^A-Za-z0-9\\s]", "");
		//System.out.println(line);
		line = line.replaceAll("\\s{2,}", " ").trim();
		//System.out.println(line);
		//System.out.println(line);
		tokens = line.toString().split(" ");
		//System.out.println(author[author.length -1]);
		//System.out.println(date[date.length -1]);
		
		int t = 0;
		for (t=0; t<tokens.length +1; t++){
			
			if(t==0){
				if(tokens[t].length() >= 1){
					kv_pairs = "_START_" + "," + tokens[t];
					context.write(new Text(kv_pairs), new Text("one"));
				} // if 1
			}
			
			else if (t==tokens.length){
				if(tokens[t-1].length() >= 1){
					kv_pairs = tokens[t-1] + "," + "_END_";
					context.write(new Text(kv_pairs), new Text("one"));
				} // if 1
			}
			
			else {
				if(tokens[t].length() >= 1){
					kv_pairs = tokens[t-1] + "," + tokens[t];
					context.write(new Text(kv_pairs), new Text("one"));
				} // if 1
			}
			
		} // for
		
	}
} // mapper
