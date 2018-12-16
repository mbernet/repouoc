package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;
import static recipes_service.tsae.data_structures.Fixtures.NODE_1;
import static recipes_service.tsae.data_structures.Fixtures.NODE_2;
import static recipes_service.tsae.data_structures.Fixtures.timestampNode1Seq2;
import static recipes_service.tsae.data_structures.Fixtures.timestampNode2Seq2;
import static recipes_service.tsae.data_structures.Fixtures.timestampNode3SeqNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinTimestampVectorTimestampMatrixTest {

	private TimestampMatrix tMtx;
	@Before
	public void setUp() throws Exception {
		tMtx = new TimestampMatrix(Fixtures.twoParticipants());
	}

	@Test
	public void testWhenEmptyShoudReturnEmtpyTVect() {
		TimestampVector minTimestampVector = tMtx.minTimestampVector();
		Assert.assertTrue(minTimestampVector.getLast(Fixtures.NODE_1).isNullTimestamp());
		Assert.assertTrue(minTimestampVector.getLast(Fixtures.NODE_2).isNullTimestamp());
	}

	@Test
	public void testWhenAllTimestampsAreEqualShoudReturnEachTimestamp() {
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtx);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq2());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq2());

		TimestampVector minTimestampVector = tMtx.minTimestampVector();
		Assert.assertEquals(Fixtures.timestampNode1Seq0(), minTimestampVector.getLast(Fixtures.NODE_1));
		Assert.assertEquals(Fixtures.timestampNode2Seq2(), minTimestampVector.getLast(Fixtures.NODE_2));
	}

	@Test
	public void testWhenTimestampsInNode1AreOlderShoudReturnTimestampsInNode1TVect() {
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtx);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq0());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq1(),
				Fixtures.timestampNode2Seq1());

		TimestampVector minTimestampVector = tMtx.minTimestampVector();
		Assert.assertEquals(Fixtures.timestampNode1Seq0(), minTimestampVector.getLast(Fixtures.NODE_1));
		Assert.assertEquals(Fixtures.timestampNode2Seq0(), minTimestampVector.getLast(Fixtures.NODE_2));	}
	@Test
	public void testWhenTimestampsInNode2AreOlderShoudReturnTimestampsInNode2TVect() {
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtx);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq2(),
				Fixtures.timestampNode2Seq2());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq1(),
				Fixtures.timestampNode2Seq1());

		TimestampVector minTimestampVector = tMtx.minTimestampVector();
		Assert.assertEquals(Fixtures.timestampNode1Seq1(), minTimestampVector.getLast(Fixtures.NODE_1));
		Assert.assertEquals(Fixtures.timestampNode2Seq1(), minTimestampVector.getLast(Fixtures.NODE_2));
	}
	@Test
	public void whenOlderTimestampsAreInBothShoudReturnOlderTimestampsFromEach() {
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtx);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq0(),
				Fixtures.timestampNode2Seq2());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq1(),
				Fixtures.timestampNode2Seq1());

		TimestampMatrix clone = tMtx.clone();
		TimestampVector minTimestampVector = tMtx.minTimestampVector();
		Assert.assertEquals(Fixtures.timestampNode1Seq0(), minTimestampVector.getLast(Fixtures.NODE_1));
		Assert.assertEquals(Fixtures.timestampNode2Seq1(), minTimestampVector.getLast(Fixtures.NODE_2));


		// also, original tMtx should not change
		Assert.assertEquals(clone, tMtx);
	}
	/**
	 * Matrix:
	 * 	A= A2, B1, NULL
	 *  B= A2, B2, NULL
	 *  C= NULL, NULL, NULL
	 */
	@Test
	public void whenOlderTimestampsAreNullShoudReturnOlderTimestampsForEachNode() {
		tMtx= new TimestampMatrix(Fixtures.threeParticipants());
		TimestampMatrixFiller f= new TimestampMatrixFiller(tMtx);
		f.fill(NODE_1, timestampNode1Seq2(), timestampNode2Seq2(), timestampNode3SeqNull());
		f.fill(NODE_2, timestampNode1Seq2(), timestampNode2Seq2(), timestampNode3SeqNull());

		TimestampVector minTimestampVector = tMtx.minTimestampVector();

		assertEquals(Fixtures.timestampNode1SeqNull(), minTimestampVector.getLast(Fixtures.NODE_1));
		assertEquals(Fixtures.timestampNode2SeqNull(), minTimestampVector.getLast(Fixtures.NODE_2));
		assertEquals(Fixtures.timestampNode3SeqNull(), minTimestampVector.getLast(Fixtures.NODE_3));
	}
}
