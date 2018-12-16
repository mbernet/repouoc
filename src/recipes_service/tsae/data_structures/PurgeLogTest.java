package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;
import static recipes_service.tsae.data_structures.Fixtures.*;

import org.junit.Before;
import org.junit.Test;

public class PurgeLogTest {

	private Log log;
	private TimestampMatrix ack;

	@Before
	public void setUp() {
		log = new Log(Fixtures.twoParticipants());
		ack = new TimestampMatrix(Fixtures.twoParticipants());
	}

	/**
	 * Log: Matrix: A= A1, B0 B= A0, B1
	 */
	@Test
	public void whenLogIsEmptyShouldDoNothing() {
		TimestampMatrixFiller f = new TimestampMatrixFiller(ack);
		f.fill(NODE_1, timestampNode1Seq1(), timestampNode2Seq0());
		f.fill(NODE_2, timestampNode1Seq0(), timestampNode2Seq1());

		log.purgeLog(ack);

		assertTrue(log.toString().isEmpty());
	}

	/**
	 * Log: A2, B1 Matrix: A= A1, B0 B= A1, B0
	 */
	@Test
	public void whenLogIsAtTimestampAfterLastShouldDoNothing() {
		log.add(newAddOperationAt(timestampNode1Seq2()));
		log.add(newAddOperationAt(timestampNode2Seq1()));
		TimestampMatrixFiller f = new TimestampMatrixFiller(ack);
		f.fill(NODE_1, timestampNode1Seq1(), timestampNode2Seq0());
		f.fill(NODE_2, timestampNode1Seq1(), timestampNode2Seq0());

		log.purgeLog(ack);

		checkLogContent(log, "AddOperation [recipe=[title, recipe, author], timestamp=NODE_1:   2]",
				"AddOperation [recipe=[title, recipe, author], timestamp=NODE_2:   1]");

	}

	private void checkLogContent(Log log, String... contents) {
		String actual = log.toString();
		// operation in the string are not ordered as it is constructed
		// iterating the keys of the Log.log hash which are stored "randomly"
		// at least ordering is VM dependent. So we remove all operations until
		// string is empty to check for equality
		for (String content : contents) {
			actual = actual.replace(content, "");
		}
		assertTrue("Content should be empty after removing all expected content!", actual.trim().isEmpty());
	}

	/**
	 * Log: A1, B1 Matrix: A= A1, B1 B= A2, B2
	 */
	@Test
	public void whenLogIsAtLastTimestampShouldRemoveLast() {
		log.add(newAddOperationAt(timestampNode1Seq1()));
		log.add(newAddOperationAt(timestampNode2Seq1()));
		TimestampMatrixFiller f = new TimestampMatrixFiller(ack);
		f.fill(NODE_1, timestampNode1Seq1(), timestampNode2Seq1());
		f.fill(NODE_2, timestampNode1Seq2(), timestampNode2Seq2());

		log.purgeLog(ack);

		assertTrue(log.toString().isEmpty());
	}

	/**
	 * Log: A1, A2, A3, B1, B2 Matrix: A= A3, B1 B= A2, B1
	 */
	@Test
	public void whenLogIsBeforeLastTimestampShouldRemoveLast() {
		log.add(newAddOperationAt(timestampNode1Seq1()));
		log.add(newAddOperationAt(timestampNode1Seq2()));
		log.add(newAddOperationAt(timestampNode1Seq3()));
		log.add(newAddOperationAt(timestampNode2Seq1()));
		log.add(newAddOperationAt(timestampNode2Seq2()));
		TimestampMatrixFiller f = new TimestampMatrixFiller(ack);
		f.fill(NODE_1, timestampNode1Seq3(), timestampNode2Seq1());
		f.fill(NODE_2, timestampNode1Seq2(), timestampNode2Seq1());

		log.purgeLog(ack);

		checkLogContent(log, "AddOperation [recipe=[title, recipe, author], timestamp=NODE_1:   3]",
				"AddOperation [recipe=[title, recipe, author], timestamp=NODE_2:   2]");
	}

	/**
	 * Log: A1, A2 Matrix: A= A2, B1, NULL B= A2, B2, NULL C= NULL, NULL, NULL
	 */
	@Test
	public void whenPartnerAckIsNullShouldNotPurgeOps() {
		log.add(newAddOperationAt(timestampNode1Seq1()));
		log.add(newAddOperationAt(timestampNode1Seq2()));
		ack = new TimestampMatrix(Fixtures.threeParticipants());
		TimestampMatrixFiller f = new TimestampMatrixFiller(ack);
		f.fill(NODE_1, timestampNode1Seq2(), timestampNode2Seq2(), timestampNode3SeqNull());
		f.fill(NODE_2, timestampNode1Seq2(), timestampNode2Seq2(), timestampNode3SeqNull());

		log.purgeLog(ack);

		checkLogContent(log, "AddOperation [recipe=[title, recipe, author], timestamp=NODE_1:   1]",
				"AddOperation [recipe=[title, recipe, author], timestamp=NODE_1:   2]");
	}
}
