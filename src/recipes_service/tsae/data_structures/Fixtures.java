package recipes_service.tsae.data_structures;

import java.util.ArrayList;
import java.util.List;

import recipes_service.data.AddOperation;
import recipes_service.data.Operation;
import recipes_service.data.Recipe;

public class Fixtures {
	public static final String NODE_1 = "NODE_1";
	public static final long SEQ_NUMBER_0 = 0;
	public static final long SEQ_NUMBER_1 = 1;
	public static final String NODE_2 = "NODE_2";
	private static final long SEQ_NUMBER_2 = 2;
	public static final long SEQ_NUMBER_3 = 3;
	public static final String NODE_3 = "NODE_3";

	public static List<String> twoParticipants() {
		List<String> participants = new ArrayList<String>();
		participants.add(NODE_1);
		participants.add(NODE_2);
		return participants;
	}

	public static List<String> threeParticipants() {
		List<String> participants = twoParticipants();
		participants.add(NODE_3);
		return participants;
	}

	public static Timestamp timestampNode1SeqNull() {
		return new Timestamp(NODE_1, Timestamp.NULL_TIMESTAMP_SEQ_NUMBER);
	}

	public static Timestamp timestampNode1Seq0() {
		return new Timestamp(NODE_1, SEQ_NUMBER_0);
	}

	public static Timestamp timestampNode2Seq0() {
		return new Timestamp(NODE_2, SEQ_NUMBER_0);
	}

	public static Timestamp timestampNode1Seq1() {
		return new Timestamp(NODE_1, SEQ_NUMBER_1);
	}

	public static Timestamp timestampNode1Seq2() {
		return new Timestamp(NODE_1, SEQ_NUMBER_2);
	}

	public static Timestamp timestampNode1Seq3() {
		return new Timestamp(NODE_1, SEQ_NUMBER_3);
	}

	public static Timestamp timestampNode2SeqNull() {
		return new Timestamp(NODE_2, Timestamp.NULL_TIMESTAMP_SEQ_NUMBER);
	}

	public static Timestamp timestampNode2Seq1() {
		return new Timestamp(NODE_2, SEQ_NUMBER_1);
	}

	public static Timestamp timestampNode2Seq2() {
		return new Timestamp(NODE_2, SEQ_NUMBER_2);
	}

	public static Timestamp timestampNode3SeqNull() {
		return new Timestamp(NODE_3, Timestamp.NULL_TIMESTAMP_SEQ_NUMBER);
	}

	public static AddOperation newAddOperationAt(Timestamp ts) {
		Recipe recipe = newRecipe(ts);
		AddOperation op = new AddOperation(recipe, ts);
		return op;
	}

	public static Recipe newRecipe(Timestamp ts) {
		return new Recipe("title", "recipe", "author", ts);
	}

	public static Timestamp timestampNode3Seq0() {
		return new Timestamp(Fixtures.NODE_3, SEQ_NUMBER_0);
	}

	public static Timestamp timestampNode3Seq1() {
		return new Timestamp(Fixtures.NODE_3, SEQ_NUMBER_1);
	}

}
