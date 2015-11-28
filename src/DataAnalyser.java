import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * 
 */

/**
 * @author Vincent
 *
 */
public class DataAnalyser {

	Logger logger = LogManager.getLogger(DataAnalyser.class);
	Date _start;
	Date _end;
	String _ticker;
	
	public DataAnalyser(DataAnalyserOptions options)
	{
		_start = options.getStart();
		_end = options.getEnd();
		_ticker = options.getTicker();
	}
	
	public void run() throws URISyntaxException, IOException, ParseException
	{
		logger.info("Running for " + _ticker + " from " + _start + " to " + _end);
		YahooFinanceDataSource ds = new YahooFinanceDataSource();		
		ds.setSourceInfo(_ticker, _start, _end);
		List<YahooFinanceStockRow> dataSet = ds.retrieve();
		
		int i = 1;
		for(YahooFinanceStockRow data : dataSet) {
			logger.info( i++ + " ["+ data.toString() + "]" );
		}
	}
}
