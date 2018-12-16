package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EqualsTimestampMatrixTest {

	private TimestampMatrix tMtxOne;
	private TimestampMatrix tMtxTwo;

	@Before
	public void setUp() throws Exception {
		tMtxOne = new TimestampMatrix(Fixtures.twoParticipants());
		tMtxTwo = new TimestampMatrix(Fixtures.twoParticipants());
	}

	@Test
	public void whenFirstEqualsSecondShouldEqualToTrue() {
		setEqualSummariesTo(tMtxOne);
		setEqualSummariesTo(tMtxTwo);

		assertEquals(tMtxOne, tMtxTwo);
	}


	@Test
	public void whenBothFirstAndSecondAreEmptyShouldBeEqual() {
		assertEquals(tMtxOne, tMtxTwo);
	}

	@Test
	public void whenSomeTimestampsInFirstAreDifferentFromCorrespondingInSecondShouldNotBeEqual() {
		setEqualSummariesTo(tMtxOne);
		// inform matrix two
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtxTwo);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq0());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq3(),
				Fixtures.timestampNode2Seq1());

		assertNotEquals(tMtxOne, tMtxTwo);
	}

	private void setEqualSummariesTo(TimestampMatrix tMtx) {
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtx);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq1(),
				Fixtures.timestampNode2Seq2());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq3(),
				Fixtures.timestampNode2Seq1());
	}
}
