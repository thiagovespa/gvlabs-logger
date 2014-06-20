package org.gvlabs.logger.impl;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.gvlabs.logger.LoggerLevel;

/**
 * File Logger Implementation
 * 
 * @author Thiago Galbiatti Vespa
 * 
 */
public class FileLoggerImpl extends AbstractWriterLogger {

	/**
	 * Constructor
	 * 
	 * @param filePath
	 *            file path
	 * @param maxLogLevel
	 *            max log level
	 * @param prefix
	 *            prefix
	 * @throws IOException
	 *             ioexception
	 */
	public FileLoggerImpl(String filePath, LoggerLevel maxLogLevel,
			String prefix) throws IOException {
		super(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
				filePath), "UTF-8")), maxLogLevel, prefix);
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

}
