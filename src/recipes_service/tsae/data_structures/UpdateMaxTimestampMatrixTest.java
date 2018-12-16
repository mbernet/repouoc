package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UpdateMaxTimestampMatrixTest {

	private TimestampMatrix tMtxOne;
	private TimestampMatrix tMtxTwo;

	@Before
	public void setUp() throws Exception {
		tMtxOne = new TimestampMatrix(Fixtures.twoParticipants());
		tMtxTwo = new TimestampMatrix(Fixtures.twoParticipants());
	}

	@Test
	public void whenTimestampsInOneAreNewerItShouldRemainTheSame() {
		// inform matrix 1
		TimestampMatrixFiller f= new TimestampMatrixFiller(tMtxOne); 
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq1(), Fixtures.timestampNode2Seq1());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq0(), Fixtures.timestampNode2Seq1());
		// inform matrix 2
		f= new TimestampMatrixFiller(tMtxTwo); 
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq0(), Fixtures.timestampNode2Seq0());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq0(), Fixtures.timestampNode2Seq1());

		String before = tMtxOne.toString();
		tMtxOne.updateMax(tMtxTwo);

		String after = tMtxOne.toString();
		assertEquals(before, after);
	}

	@Test
	public void whenTimestampInTwoAreNewerShouldReplaceTimestampsInOne() {
		// inform matrix 1
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtxOne);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq1(), Fixtures.timestampNode2Seq1());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq0(), Fixtures.timestampNode2Seq0());
		// inform matrix 2
		f= new TimestampMatrixFiller(tMtxTwo);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq3(), Fixtures.timestampNode2Seq2());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq3(), Fixtures.timestampNode2Seq2());

		tMtxOne.updateMax(tMtxTwo);

		// NOTE: check separately because key ordering in ConcurrentHashmap changes between skds, so toString returns different results.
		TimestampVectorAsserts.checkContains(tMtxOne.getTimestampVector("NODE_1"), "NODE_1:   3", "NODE_2:   2");
		TimestampVectorAsserts.checkContains(tMtxOne.getTimestampVector("NODE_2"), "NODE_1:   3", "NODE_2:   2");
	}

	@Test
	public void whenBothTimestampMatrixAreTheSameShouldRemainTheSame() {
		// inform matrix 1
		TimestampMatrixFiller f = new TimestampMatrixFiller(tMtxOne);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq3(), Fixtures.timestampNode2Seq1());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq1(), Fixtures.timestampNode2Seq2());
		// inform matrix 2
		f= new TimestampMatrixFiller(tMtxTwo);
		f.fill(Fixtures.NODE_1, Fixtures.timestampNode1Seq3(), Fixtures.timestampNode2Seq1());
		f.fill(Fixtures.NODE_2, Fixtures.timestampNode1Seq1(), Fixtures.timestampNode2Seq2());

		String before = tMtxOne.toString();
		tMtxOne.updateMax(tMtxTwo);

		String after = tMtxOne.toString();
		assertEquals(before, after);
	}

	@Test
	public void testInitializedFromEmpty() {
		List<String> participants = DSLabFixtures.fiveParticipants();
		TimestampMatrix tMtx = new TimestampMatrix(participants);
		String currentNode = "groupXX@192.168.1.128:35003";
		TimestampVector summary= DSLabFixtures.summaryNode2_1(participants);
		tMtx.update(currentNode, summary);
		currentNode = "groupXX@192.168.1.128:35000";
		summary= DSLabFixtures.summaryNode2_null(participants);
		TimestampVector localSummary= summary;
		tMtx.update(currentNode, summary);
		currentNode = "groupXX@192.168.1.128:35002";
		summary= DSLabFixtures.summaryNode2_null(participants);
		tMtx.update(currentNode, summary);
		currentNode = "groupXX@192.168.1.128:35001";
		summary= DSLabFixtures.summaryNode2_null(participants);
		tMtx.update(currentNode, summary);
		currentNode = "groupXX@192.168.1.128:35004";
		summary= DSLabFixtures.summaryNode2_null(participants);
		tMtx.update(currentNode, summary);

		TimestampMatrix partnerAck= new TimestampMatrix(participants);
		tMtx.updateMax(partnerAck);

		TimestampVector inAck = tMtx.getTimestampVector(currentNode);
		assertEquals(localSummary, inAck);
	}
}
