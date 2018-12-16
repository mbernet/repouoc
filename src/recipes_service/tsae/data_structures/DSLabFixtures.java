package recipes_service.tsae.data_structures;

import java.util.ArrayList;
import java.util.List;

public class DSLabFixtures {
	public static final String NODE_35012 = "groupXX@127.0.1.1:35012";
	public static final String NODE_35002= "groupXX@127.0.1.1:35002";
	public static final String NODE_35011= "groupXX@127.0.1.1:35011";
	public static final String NODE_35008= "groupXX@127.0.1.1:35008";
	public static final String NODE_35003 = "groupXX@127.0.1.1:35003";
	public static final String NODE_35000 = "groupXX@127.0.1.1:35000";
	public static final String NODE_35010 = "groupXX@127.0.1.1:35010";
	public static final String NODE_35006 = "groupXX@127.0.1.1:35006";
	public static final String NODE_35001 = "groupXX@127.0.1.1:35001";
	public static final String NODE_35009 = "groupXX@127.0.1.1:35009";
	public static final String NODE_35004 = "groupXX@127.0.1.1:35004";
	public static final String NODE_35007 = "groupXX@127.0.1.1:35007";
	public static final String NODE_35005 = "groupXX@127.0.1.1:35005";

	public static List<String> therteenParticipants() {
		List<String> participants = new ArrayList<String>();
		participants.add(NODE_35012);
		participants.add(NODE_35002);
		participants.add(NODE_35011);
		participants.add(NODE_35008);
		participants.add(NODE_35003);
		participants.add(NODE_35000);
		participants.add(NODE_35010);
		participants.add(NODE_35006);
		participants.add(NODE_35001);
		participants.add(NODE_35009);
		participants.add(NODE_35004);
		participants.add(NODE_35007);
		participants.add(NODE_35005);
		return participants;
	}

	public static TimestampVector summaryNode1(List<String> participants) {
		TimestampVector tVect = new TimestampVector(participants);
		tVect.updateTimestamp(newTimestamp(NODE_35012, 1));
		tVect.updateTimestamp(newTimestamp(NODE_35002, 4));
		tVect.updateTimestamp(newTimestamp(NODE_35011, 4));
		tVect.updateTimestamp(newTimestamp(NODE_35008, 1));
		tVect.updateTimestamp(newTimestamp(NODE_35003, 5));
		tVect.updateTimestamp(newTimestamp(NODE_35000, Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp(NODE_35010, 4));
		tVect.updateTimestamp(newTimestamp(NODE_35006, 4));
		tVect.updateTimestamp(newTimestamp(NODE_35001, Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp(NODE_35009, 1));
		tVect.updateTimestamp(newTimestamp(NODE_35004, 3));
		tVect.updateTimestamp(newTimestamp(NODE_35007, 0));
		tVect.updateTimestamp(newTimestamp(NODE_35005, 1));
		return tVect;
		
	}
	public static Timestamp newTimestamp(String node, long tstmp) {
		return new Timestamp(node, tstmp);
	}

	public static List<String> fiveParticipants() {
		List<String> participants = new ArrayList<String>();
		participants.add("groupXX@192.168.1.128:35003");
		participants.add("groupXX@192.168.1.128:35000");
		participants.add("groupXX@192.168.1.128:35002");
		participants.add("groupXX@192.168.1.128:35001");
		participants.add("groupXX@192.168.1.128:35004");
		return participants;
	}
	public static TimestampVector summaryNode2_1(List<String> participants) {
		TimestampVector tVect = new TimestampVector(participants);
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35003", 0));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35000", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35002", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35001", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35004", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		return tVect;
	}
	public static TimestampVector summaryNode2_null(List<String> participants) {
		TimestampVector tVect = new TimestampVector(participants);
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35003", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35000", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35002", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35001", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		tVect.updateTimestamp(newTimestamp("groupXX@192.168.1.128:35004", Timestamp.NULL_TIMESTAMP_SEQ_NUMBER));
		return tVect;
	}
}
