import java.io.DataOutput;
import java.io.DataInput;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import java.io.IOException;

public class Tuple implements WritableComparable{

	private Text value;
	private IntWritable count;

	Tuple(){
		set(new Text(),new IntWritable());
	}

	Tuple(Text v,IntWritable c){
		set(v,c);
	}

	@Override
	public void write(DataOutput out) throws IOException{
		value.write(out);
		count.write(out);
	}

	public void set(Text t,IntWritable c){
		this.value=t;
		this.count=c;
	}

	@Override
	public void readFields(DataInput input) throws IOException {
		value.readFields(input);
		count.readFields(input);
	}
	
	@Override
	public int compareTo(Object o){
		Tuple that=(Tuple) o;
		return this.value.compareTo(that.getValue());
	}

	
	public int hashCode(){
		return value.hashCode()*100+count.hashCode();
	}

	public String toString(){
		return value+" "+count;
	}

	public Text getValue(){
		return this.value;
	}

	public IntWritable getCount(){
		return this.count;
	}

}
