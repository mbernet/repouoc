package recipes_service.tsae.data_structures;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import recipes_service.data.AddOperation;
import recipes_service.data.Operation;
import recipes_service.data.Recipe;

public class AddOperationsToLogTest {

	private Log log;

	@Before
	public void setUp() {
		log= new Log(Fixtures.twoParticipants());
	}

	@Test
	public void testAddOperationToHostEmptyLogShouldSuccceed() {
		Timestamp ts= Fixtures.timestampNode1Seq0();
		Recipe recipe= new Recipe("title", "recipe", "author", ts);
		Operation op= new AddOperation(recipe, ts);
		assertTrue( log.add(op) );
	}

	@Test
	public void testAddOperationToHostLogWithPreviousOperationsShouldSuccceed() {
		Timestamp ts= Fixtures.timestampNode1Seq0();
		assertTrue( log.add(Fixtures.newAddOperationAt(ts)) );

		ts= Fixtures.timestampNode1Seq1();
		assertTrue( log.add(Fixtures.newAddOperationAt(ts)) );
	}


	@Test
	public void testAddSameOperationTwiceToHostLogShouldFail() {
		Timestamp ts= Fixtures.timestampNode1Seq0();
		Recipe recipe= Fixtures.newRecipe(ts);
		Operation op= new AddOperation(recipe, ts);
		assertTrue( log.add(op) );
		assertFalse( log.add(op) );
	}


	@Test
	public void testAddOperationsWithSameSeqToDifferentHostsShouldSuccceed() {
		Timestamp ts= Fixtures.timestampNode1Seq0();
		assertTrue( log.add(Fixtures.newAddOperationAt(ts)) );

		ts= Fixtures.timestampNode2Seq0();
		assertTrue( log.add(Fixtures.newAddOperationAt(ts)) );
	}

	/*
	 * --------------------------------------------------------------------------
	 * PRIVATE METHODS
	 * --------------------------------------------------------------------------
	 */
}
