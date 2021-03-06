package org.gvlabs.logger.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;

import org.gvlabs.logger.Logger;
import org.gvlabs.logger.LoggerLevel;

/**
 * Writer Log Base Class
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public abstract class AbstractWriterLogger extends Logger {

	private static final java.util.logging.Logger JLOGGER = java.util.logging.Logger
			.getAnonymousLogger();

	private Writer writer;

	/**
	 * Constructor
	 * 
	 * @param writer
	 *            writer used to log
	 * @param maxLogLevel
	 *            max log level
	 * @param prefix
	 *            prefix
	 */
	public AbstractWriterLogger(Writer writer, LoggerLevel maxLogLevel,
			String prefix) {
		super(maxLogLevel, prefix);
		this.writer = writer;
	}

	@Override
	public void log(LoggerLevel level, String msg, Throwable e) {
		try {
			StackTraceElement ste = getLastStackTrace();

			StringBuilder cmplSB = new StringBuilder();
			if (getPrefix() != null) {
				cmplSB.append(getPrefix()).append(" ");
			}
			cmplSB.append(DateFormat.getDateTimeInstance().format(new Date()))
					.append(" [").append(level.toString()).append("] ")
					.append(ste.getClassName()).append(".")
					.append(ste.getMethodName()).append("() (")
					.append(ste.getFileName()).append(":")
					.append(ste.getLineNumber()).append(") ").append(msg);

			writer.write(cmplSB.toString());
			writer.write("\n");
			if (e != null) {
				e.printStackTrace(new PrintWriter(this.writer));
			}
			writer.flush();
		} catch (IOException ioe) {
			JLOGGER.log(Level.SEVERE, "Unexpected error", e);
			try {
				close();
			} catch (IOException e1) {
				JLOGGER.log(Level.SEVERE, "Unexpected error", e);
			}

		}
	}

	/**
	 * Close the writer
	 * 
	 * @throws IOException
	 *             ioexception
	 */
	public void close() throws IOException {
		if (writer != null) {
			writer.close();
		}
		writer = null;
	}

}
