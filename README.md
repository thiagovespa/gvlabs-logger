gvlabs-logger
=================

gvlabs-logger

Simple Logger

Requirements: Java 5+

Usage:

	import static org.gvlabs.logger.StaticConsoleLogger.*;


		error(TEST_MSG);
		error(TEST_MSG, new ArithmeticException());
		info(TEST_MSG);
		debug(TEST_MSG);
		trace(TEST_MSG);
		warn(TEST_MSG);