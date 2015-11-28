import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionHandlerRegistry;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DataAnalyserMain {

	/**
	 * Program to get market data from Internet (start with Yahoo)
	 * and analysis the data using different strategy
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
						
		Logger logger = LogManager.getLogger(DataAnalyserMain.class);
		
		DataAnalyserOptions bean = new DataAnalyserOptions();
		logger.info("Start");
		OptionHandlerRegistry registry = OptionHandlerRegistry.getRegistry();
		registry.registerHandler(Date.class, DateOptionHandler.class);
		CmdLineParser parser = new CmdLineParser(bean);
		
		List<String> argsList = Arrays.asList(args);
		argsList.forEach(System.out::println);
		
		logger.info("parse argument: " + args);
		try {
			parser.parseArgument(args);
		}
		catch (CmdLineException e) {
			logger.error(e.getMessage());
			parser.printUsage(System.err);
		}
		
		try {			
			DataAnalyser analyser = new DataAnalyser(bean);
			analyser.run();
		}
		catch(Exception e)
		{
			logger.error("Error running Data Analyser: "+ e);
			e.printStackTrace();
		}
		
	}

}
