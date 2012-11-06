package org.gvlabs.logger.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.gvlabs.logger.engine.LoggerLevel;

public class FileLoggerImpl extends AbstractWriterLogger {

	public FileLoggerImpl(String filePath, LoggerLevel maxLogLevel,
			String prefix) throws IOException {
		super(new BufferedWriter(new FileWriter(filePath)), maxLogLevel, prefix);
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

}
