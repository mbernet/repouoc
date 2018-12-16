package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EqualsTimestampVectorTest {

	private TimestampVector tVectOne;
	private TimestampVector tVectTwo;

	@Before
	public void setUp() throws Exception {
		tVectOne= new TimestampVector(Fixtures.twoParticipants());
		tVectTwo= new TimestampVector(Fixtures.twoParticipants());
	}

	@Test
	public void whenEveryTimestampInFirstEqualsCorrespondingInSecondShouldBeEqual() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq0());
		tVectTwo.updateTimestamp(Fixtures.timestampNode1Seq0());
		assertEquals(tVectOne, tVectTwo);
	}
	@Test
	public void whenBothFirstAndSecondAreEmptyShouldBeEqual() {
		assertEquals(tVectOne, tVectTwo);
	}
	@Test
	public void whenSomeTimestampInFirstIsDifferentFromCorrespondingInSecondShouldNotBeEqual() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq0());
		tVectTwo.updateTimestamp(Fixtures.timestampNode2Seq0());
		assertNotEquals(tVectOne, tVectTwo);
	}

}
