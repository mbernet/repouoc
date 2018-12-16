package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CloneTimestampMatrixTest {

	private TimestampMatrix tMtxOne;
	private TimestampMatrix tMtxTwo;

	@Before
	public void setUp() throws Exception {
		tMtxOne = new TimestampMatrix(Fixtures.twoParticipants());
	}

	@Test
	public void EmptyTimestampVectorShouldCloneToEmptyTimestampVector() {
		tMtxTwo = tMtxOne.clone();
		assertEquals(tMtxOne, tMtxTwo);
	}

	@Test
	public void whenFirstHasValuesShouldCloneToTimestampVectorWithSameValues() {
		// inform matrix 1
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtxOne);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq1(),
				Fixtures.timestampNode2Seq1());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq1());

		tMtxTwo = tMtxOne.clone();
		assertEquals(tMtxOne, tMtxTwo);
	}
}
