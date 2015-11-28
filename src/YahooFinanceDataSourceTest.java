import static org.junit.Assert.*;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class YahooFinanceDataSourceTest {

	YahooFinanceDataSource _ds;
	String _dataGood;
	String _dataBadDate;
	String _dataBadData;
	
	@Before
	public void setUp() throws Exception {
		_dataGood = "2013-04-30,435.10,445.25,432.07,442.78,24697800,439.87";
		_dataBadDate = "20150130,435.10,445.25,432.07,442.78,24697800,439.87";
		_dataBadData = "2013-04-30,435.10,445.25,432.07,442.78,24697800.3,439.87";
		_ds = new YahooFinanceDataSource();
		_ds.setHasHeader(false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParseGoodRow() {
		try {
			_ds.parseRow(_dataGood);
		}
		catch (ParseException e) {
			fail("ParseRow throws exception " + e.getMessage());
		}
	} 
	
	@Test
	public void testParseBadAsOfDate() {
		try {
			_ds.parseRow(_dataBadDate);
			fail("Should have thrown parse exception with bad date: " + _dataBadDate);
		}
		catch (ParseException e) {
			// okay!
		}
	}

	@Test
	public void testParseBadData() {
		try {
			_ds.parseRow(_dataBadData);
			fail("Should have thrown parse exception with bad data: " + _dataBadData);
		} catch (ParseException|NumberFormatException e) {
			// okays!
		}
	}
}
