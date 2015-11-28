import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/*
 * Takes a url and returns data type T
 */
public abstract class InternetDataSource<T> {

	public URI _address;
	public boolean _hasHeader;
	BufferedReader _reader;
	
	public List<T> retrieve() throws IOException, ParseException
	{
		_reader = new BufferedReader(new InputStreamReader(_address.toURL().openStream()));
		List<T> obj = readData();
	    _reader.close();
		
		return obj;
	}

	public boolean getHasHeader() {
		return _hasHeader;
	}
	
	public void setHasHeader(boolean hasHeader) {
		_hasHeader = hasHeader;
	}
	
	
	public List<T> readData() throws IOException, ParseException
	{
		List<T> result = new ArrayList<T>();
		
		// read off header
	    String inputLine = _reader.readLine();
	    
		while ((inputLine = _reader.readLine()) != null) {
	        
	        T data = parseRow(inputLine);
	        result.add(data);
	    }
		return result;
	}
	
	abstract public T parseRow(String T) throws ParseException;
}
