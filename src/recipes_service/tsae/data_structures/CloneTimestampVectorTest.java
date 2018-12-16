package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CloneTimestampVectorTest {

	private TimestampVector tVectOne;
	private TimestampVector tVectTwo;

	@Before
	public void setUp() throws Exception {
		tVectOne= new TimestampVector(Fixtures.twoParticipants());
	}

	@Test
	public void EmptyTimestampVectorShouldCloneToEmptyTimestampVector() {
		tVectTwo= tVectOne.clone();
		assertEquals(tVectOne, tVectTwo);
		// with same nodes?! 
	}

	@Test
	public void whenFirstHasValuesShouldCloneToTimestampVectorWithSameValues() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq0());
		tVectOne.updateTimestamp(Fixtures.timestampNode2Seq1());

		tVectTwo= tVectOne.clone();

		assertEquals(tVectOne, tVectTwo);
	}
}
