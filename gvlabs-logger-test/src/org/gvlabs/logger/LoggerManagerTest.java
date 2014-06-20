package org.gvlabs.logger;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.gvlabs.logger.LoggerInjector.initLog;
import static org.gvlabs.logger.StaticConsoleLogger.*;

@LoggerSettings(level = LoggerLevel.TRACE)
public class LoggerManagerTest {

	static { initLog("[TestUnit]"); }
	
	private final static String TEST_MSG = "Logger Test";
	private final static String FILE_PATH = "./log.txt";

	@ConsoleLogger
	private static Logger consoleLogManager;

	@FileLogger(filePath = FILE_PATH)
	private static Logger fileLogManager;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConsoleLog() {
		consoleLogManager.error(TEST_MSG);
		consoleLogManager.error(TEST_MSG, new ArithmeticException());
		consoleLogManager.info(TEST_MSG);
		consoleLogManager.debug(TEST_MSG);
		consoleLogManager.trace(TEST_MSG);
		consoleLogManager.warn(TEST_MSG);

	}

	@Test
	public void testFileLog() {
		fileLogManager.error(TEST_MSG);
		fileLogManager.error(TEST_MSG, new ArithmeticException());
		fileLogManager.info(TEST_MSG);
		fileLogManager.debug(TEST_MSG);
		fileLogManager.trace(TEST_MSG);
		fileLogManager.warn(TEST_MSG);
		File logFile = new File(FILE_PATH);
		assertTrue(logFile.exists());
	}

	@Test
	public void testMailLog() {
	}
	
	@Test
	public void testStaticLogger() {
		error(TEST_MSG);
		error(TEST_MSG, new ArithmeticException());
		info(TEST_MSG);
		debug(TEST_MSG);
		trace(TEST_MSG);
		warn(TEST_MSG);
	}

}
