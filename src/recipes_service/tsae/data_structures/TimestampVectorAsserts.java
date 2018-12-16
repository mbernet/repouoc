package recipes_service.tsae.data_structures;

import static org.junit.Assert.assertTrue;

public class TimestampVectorAsserts {

	public static void checkContains(TimestampVector timestampVector, String...expecteds) {
		String actual = timestampVector.toString();
		for(String expected: expecteds) {
			assertTrue(actual.contains(expected));
		}
	}

}
