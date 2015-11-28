import java.util.Date;
import org.kohsuke.args4j.Option;

/**
 * Option object for args4j
 */

/**
 * @author Vincent
 *
 */
public class DataAnalyserOptions {

	@Option(name="-start",usage="start date")
	Date start;
	
	@Option(name="-end",usage="end date")
	Date end;
	
	@Option(name="-ticker",usage="ticker to query")
	String ticker;

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
}
