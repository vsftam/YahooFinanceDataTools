import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Get Stock data from yahoo finance page from url
 * http://ichart.finance.yahoo.com/table.csv?s=DE&a=00&b=30&c=2009&d=00&e=29&f=2010&g=d&ignore=.csv
 */

/**
 * @author Vincent
 *
 */
public class YahooFinanceDataSource extends InternetDataSource<YahooFinanceStockRow> {

	Logger logger = LogManager.getLogger(YahooFinanceDataSource.class);
	
	private static final String URI_PREFIX = "http://ichart.finance.yahoo.com/table.csv?s=";
	private static final String URI_SUFFIX = "&g=d&ignore=.csv";
	
	
	public YahooFinanceDataSource() {
		super();
		setHasHeader(true);
	}

	public void setSourceInfo(String stockSymbol, Date start, Date end) throws URISyntaxException
	{
		String startDateString = getStartDateString(start);
		String endDateString = getEndDateString(end);
		
		_address = new URI(URI_PREFIX + stockSymbol + startDateString + endDateString + URI_SUFFIX); 
		logger.info("Reading url: "+ _address);
	}
	
	private String getStartDateString(Date start)
	{
		return getDateString(start, true);
	}
	
	private String getEndDateString(Date end)
	{
		return getDateString(end, false);
	}
	
	private String getDateString(Date date,  boolean isStart)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mm = cal.get(Calendar.MONTH);
		int dd = cal.get(Calendar.DAY_OF_MONTH);
		int yyyy = cal.get(Calendar.YEAR);
		
		String mmStr = (mm < 10) ? ("0"+mm) : Integer.toString(mm);
		String ddStr = (dd < 10) ? ("0"+dd) : Integer.toString(dd);
		
		return isStart ?  "&a="+mmStr+"&b="+ddStr+"&c="+yyyy : "&d="+mmStr+"&e="+ddStr+"&f="+yyyy;
	}

	@Override
	public YahooFinanceStockRow parseRow(String input) throws ParseException, NumberFormatException
	{
		YahooFinanceStockRow row = new YahooFinanceStockRow();
		String[] fields = input.split(",");
		
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		// as of date
		Date asOfDate = formatter.parse(fields[0]);
		row.setAsOfDate(asOfDate);
		
		// Open
		BigDecimal open = new BigDecimal(fields[1]);
		row.setOpen(open);
		
		// High
		BigDecimal high = new BigDecimal(fields[2]);
		row.setHigh(high);
		
		// Low
		BigDecimal low = new BigDecimal(fields[3]);
		row.setLow(low);
		
		// Close
		BigDecimal close = new BigDecimal(fields[4]);
		row.setClose(close);
		
		// Volume
		int volume = Integer.parseInt( fields[5] );
		row.setVolume(volume);
				
		// Adj Close
		BigDecimal adjclose = new BigDecimal(fields[6]);
		row.setAdjClose(adjclose);
		
		return row;
	}
}
