
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OptionHandler;
import org.kohsuke.args4j.spi.Parameters;
import org.kohsuke.args4j.spi.Setter;

/**
 * @author Vincent
 *
 */
public class DateOptionHandler extends OptionHandler<Date> {

	/**
	 * @param parser
	 * @param option
	 * @param setter
	 */
	public DateOptionHandler(CmdLineParser parser, OptionDef option,
			Setter<? super Date> setter) {
		super(parser, option, setter);
	}

	/* (non-Javadoc)
	 * @see org.kohsuke.args4j.spi.OptionHandler#parseArguments(org.kohsuke.args4j.spi.Parameters)
	 */
	@Override
	public int parseArguments(Parameters params) throws CmdLineException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			Date date =	sdf.parse(params.getParameter(0));
			setter.addValue(date);
		}
		catch(ParseException pe) {
			throw new CmdLineException(owner, pe.getCause());
		}
		
		return 1;
	}

	/* (non-Javadoc)
	 * @see org.kohsuke.args4j.spi.OptionHandler#getDefaultMetaVariable()
	 */
	@Override
	public String getDefaultMetaVariable() {
		return "DATE";
	}

}
