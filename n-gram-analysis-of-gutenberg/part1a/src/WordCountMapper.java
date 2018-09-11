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
		
//		String[] words = value.toString().split(" ");
//		for(String word: words) {
//			context.write(new Text(word), new Text("one"));
//		} // for
		
		byte[] inputFile = value.getBytes();
		String[] cLineArray;
		String[] author;
		String[] date;
		String line;
		String[] tokens;
		String kv_pairs;

//		BufferedReader bfr = new BufferedReader(new StringReader(new String(inputFile)));		
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
				
		int i = 0;
		while (i < tokens.length){
			if(tokens[i].length() >= 1){
				kv_pairs = tokens[i] + "\t" + date[date.length -1];
				context.write(new Text(kv_pairs), new Text("one"));
//				continue;
			}
			
//			kv_pairs = tokens[i] + "\t" + date[date.length -1];
//			context.write(new Text(tokens[i]), new Text(date[date.length -1]));
//			context.write(new Text(kv_pairs), new Text("one"));
//			System.out.println(kv_pairs);
			
			i = i + 1;
				
//			try(PrintWriter out = new PrintWriter( new FileWriter("tokens.txt", true) )){
//			    out.println(kv_pairs);
//			}  // writing the tokens to the file ....... slows down the process!!!!!
				
		} // while tokens are available
			
//		System.out.println(tokens[tokens.length-1]);

	
	}
} // mapper