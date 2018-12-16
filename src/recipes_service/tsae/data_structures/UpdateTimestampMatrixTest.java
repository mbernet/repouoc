package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class UpdateTimestampMatrixTest {

	private TimestampMatrix tMtx;

	@Before
	public void setUp() throws Exception {
		tMtx = new TimestampMatrix(Fixtures.twoParticipants());
	}

	@Test
	public void addingTimestampsToAnEmptyTimestampMatrixShouldSucceed() {
		List<String> participants = Fixtures.twoParticipants();
		participants.add(Fixtures.NODE_3);

		// inform node 1
		TimestampVector tVect = new TimestampVector(participants);
		tVect.updateTimestamp(Fixtures.timestampNode1Seq1());
		tVect.updateTimestamp(Fixtures.timestampNode2Seq1());
		tVect.updateTimestamp(Fixtures.timestampNode3Seq1());
		tMtx.update(Fixtures.NODE_1, tVect);
		// inform node 2
		tVect = new TimestampVector(participants);
		tVect.updateTimestamp(Fixtures.timestampNode1Seq0());
		tVect.updateTimestamp(Fixtures.timestampNode2Seq1());
		tVect.updateTimestamp(Fixtures.timestampNode3Seq0());
		tMtx.update(Fixtures.NODE_2, tVect);
		// inform node 3
		tVect = new TimestampVector(participants);
		tVect.updateTimestamp(Fixtures.timestampNode1Seq0());
		tVect.updateTimestamp(Fixtures.timestampNode2Seq0());
		tVect.updateTimestamp(Fixtures.timestampNode3Seq1());
		tMtx.update(Fixtures.NODE_3, tVect);

		TimestampVectorAsserts.checkContains(tMtx.getTimestampVector("NODE_3"), "NODE_3:   1", "NODE_1:   0", "NODE_2:   0");
		TimestampVectorAsserts.checkContains(tMtx.getTimestampVector("NODE_1"), "NODE_3:   1", "NODE_1:   1", "NODE_2:   1");
		TimestampVectorAsserts.checkContains(tMtx.getTimestampVector("NODE_2"), "NODE_3:   0", "NODE_1:   0", "NODE_2:   1");
	}

	@Test
	public void addingSummaryTimestampsToAnEmptyTimestampMatrixShouldSucceed() {
		List<String> participants = DSLabFixtures.therteenParticipants();
		tMtx = new TimestampMatrix(participants);

		// inform summary
		TimestampVector summary= DSLabFixtures.summaryNode1(participants);
		tMtx.update(DSLabFixtures.NODE_35000, summary);

		TimestampVectorAsserts.checkContains(tMtx.getTimestampVector(DSLabFixtures.NODE_35000),
			"groupXX@127.0.1.1:35012:   1\n",
			"groupXX@127.0.1.1:35002:   4\n",
			"groupXX@127.0.1.1:35011:   4\n",
			"groupXX@127.0.1.1:35008:   1\n",
			"groupXX@127.0.1.1:35003:   5\n",
			"groupXX@127.0.1.1:35000:   -1000\n",
			"groupXX@127.0.1.1:35010:   4\n",
			"groupXX@127.0.1.1:35006:   4\n",
			"groupXX@127.0.1.1:35001:   -1000\n",
			"groupXX@127.0.1.1:35009:   1\n",
			"groupXX@127.0.1.1:35004:   3\n",
			"groupXX@127.0.1.1:35007:   0\n",
			"groupXX@127.0.1.1:35005:   1\n"
		);
	}

	@Test
	public void addingSummaryTimestampsToAnEmptyTimestampMatrixShouldSucceed2() {
		List<String> participants = DSLabFixtures.fiveParticipants();
		tMtx = new TimestampMatrix(participants);

		String currentNode = "groupXX@192.168.1.128:35003";
		TimestampVector summary= DSLabFixtures.summaryNode2_1(participants);
		tMtx.update(currentNode, summary);
		
		TimestampVector inAck = tMtx.getTimestampVector(currentNode);
		assertEquals(summary, inAck);
	}
}
