package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import recipes_service.data.AddOperation;
import recipes_service.data.Operation;
import recipes_service.data.Recipe;

public class ListNewerLogTest {

	private Log log;

	@Before
	public void setUp() {
		log = new Log(Fixtures.twoParticipants());
	}

	@Test
	public void whenLogIsEmptyShouldAlwaysReturnEmptyList() {
		TimestampVector sum = new TimestampVector(Fixtures.twoParticipants());
		List<Operation> listNewer = log.listNewer(sum);
		assertTrue(listNewer.isEmpty());
	}

	@Test
	public void whenLogIsLatterShouldReturnEmptyList() {
		// log is at seq 0
		Timestamp ts = Fixtures.timestampNode1Seq0();
		log.add(Fixtures.newAddOperationAt(ts));
		// partner summary is at seq 1
		TimestampVector sum = new TimestampVector(Fixtures.twoParticipants());
		sum.updateTimestamp(Fixtures.timestampNode1Seq1());

		List<Operation> listNewer = log.listNewer(sum);
		assertTrue(listNewer.isEmpty());
	}

	@Test
	public void whenAllLogIsSoonerShouldReturnListOfAllLogs() {
		// host 1 log is at seq 3
		Timestamp ts = Fixtures.timestampNode1Seq1();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode1Seq2();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode1Seq3();
		log.add(Fixtures.newAddOperationAt(ts));
		// host 2 log is at seq 2
		ts = Fixtures.timestampNode2Seq1();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode2Seq2();
		log.add(Fixtures.newAddOperationAt(ts));
		// partner summary is at seq 0 for all logs
		TimestampVector sum = new TimestampVector(Fixtures.twoParticipants());
		sum.updateTimestamp(Fixtures.timestampNode1Seq0());
		sum.updateTimestamp(Fixtures.timestampNode2Seq0());

		List<Operation> listNewer = log.listNewer(sum);
		assertEquals(5, listNewer.size());

		List<String> expecteds= new ArrayList<String>();
		expecteds.add("NODE_1:   1");
		expecteds.add("NODE_1:   2");
		expecteds.add("NODE_1:   3");
		expecteds.add("NODE_2:   1");
		expecteds.add("NODE_2:   2");
		for (Operation op: listNewer) {
			String current= op.getTimestamp().toString();
			assertTrue("listNewer should contain all expected Timestmps!", expecteds.contains(current));
			expecteds.remove(current);
		}
		assertTrue(expecteds.isEmpty());
	}

	@Test
	public void whenLogIsPartlySoonerShouldReturnListOfSoonerLogs() {
		// host 1 log is at seq 3
		Timestamp ts = Fixtures.timestampNode1Seq1();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode1Seq2();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode1Seq3();
		log.add(Fixtures.newAddOperationAt(ts));
		// host 2 log is at seq 2
		ts = Fixtures.timestampNode2Seq1();
		log.add(Fixtures.newAddOperationAt(ts));
		ts = Fixtures.timestampNode2Seq2();
		log.add(Fixtures.newAddOperationAt(ts));
		// partner summary is at seq 2 for all logs
		TimestampVector sum = new TimestampVector(Fixtures.twoParticipants());
		sum.updateTimestamp(Fixtures.timestampNode1Seq2());
		sum.updateTimestamp(Fixtures.timestampNode2Seq2());

		List<Operation> listNewer = log.listNewer(sum);
		assertEquals(1, listNewer.size());
		assertEquals("NODE_1:   3", listNewer.get(0).getTimestamp().toString());
	}
}
