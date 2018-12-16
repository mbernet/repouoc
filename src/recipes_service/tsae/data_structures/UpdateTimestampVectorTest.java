package recipes_service.tsae.data_structures;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UpdateTimestampVectorTest {

	@Test
	public void addingTimestampsToAnEmptyTVectorShouldSucceed() {
		TimestampVector tstmpVect= new TimestampVector(Fixtures.twoParticipants());

		tstmpVect.updateTimestamp(Fixtures.timestampNode1Seq0());
		String[] expected_1_0= {"NODE_1:   0", "NODE_2:   -1000"};
		checkTstmpVectContains(tstmpVect.toString(), expected_1_0);

		tstmpVect.updateTimestamp(Fixtures.timestampNode2Seq0());
		String[] expected_2_0= {"NODE_1:   0", "NODE_2:   0"};
		checkTstmpVectContains(tstmpVect.toString(), expected_2_0);

		tstmpVect.updateTimestamp(Fixtures.timestampNode1Seq1());
		String[] expected_1_1= {"NODE_1:   1", "NODE_2:   0"};
		checkTstmpVectContains(tstmpVect.toString(), expected_1_1);

		tstmpVect.updateTimestamp(Fixtures.timestampNode1Seq2());
		String[] expected_1_2= {"NODE_1:   2", "NODE_2:   0"};
		checkTstmpVectContains(tstmpVect.toString(), expected_1_2);


		tstmpVect.updateTimestamp(Fixtures.timestampNode2Seq1());
		String[] expected_2_1= {"NODE_1:   2", "NODE_2:   1"};
		checkTstmpVectContains(tstmpVect.toString(), expected_2_1);


		tstmpVect.updateTimestamp(Fixtures.timestampNode2Seq2());
		String[] expected_2_2= {"NODE_1:   2", "NODE_2:   2"};
		checkTstmpVectContains(tstmpVect.toString(), expected_2_2);

	}

	//---------------------------------------------------------
	// PRIVATE
	//---------------------------------------------------------

	private void checkTstmpVectContains(String tstmpVect, String[] expected) {
		for (String expectedTstmp: expected) {
			assertTrue(tstmpVect + " This TimestampVector represented as a String should contain:"+expectedTstmp, tstmpVect.contains(expectedTstmp));
		}
	}

}
