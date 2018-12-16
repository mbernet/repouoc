package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.junit.Before;
import org.junit.Test;

public class UpdateMaxTimestampVectorTest {

	private TimestampVector tVectOne;
	private TimestampVector tVectTwo;

	@Before
	public void setUp() throws Exception {
		tVectOne= new TimestampVector(Fixtures.twoParticipants());
		tVectTwo= new TimestampVector(Fixtures.twoParticipants());
	}

	@Test
	public void whenTimestampInOneIsNewerItShouldRemainTheSame() {
		Timestamp newer= Fixtures.timestampNode1Seq1();
		tVectOne.updateTimestamp(newer);
		tVectTwo.updateTimestamp(Fixtures.timestampNode1Seq0());

		tVectOne.updateMax(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(newer, last);
	}

	@Test
	public void whenTimestampTwoIsNewerItShouldReplaceTheFirst() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq0());
		Timestamp newer= Fixtures.timestampNode1Seq1();
		tVectTwo.updateTimestamp(newer);

		tVectOne.updateMax(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(newer, last);
	}
	@Test
	public void whenBothTimestampAreTheSameShouldRemainTheSame() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq1());
		Timestamp newer= Fixtures.timestampNode1Seq1();
		tVectTwo.updateTimestamp(newer);

		tVectOne.updateMax(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(newer, last);
	}

	@Test
	public void whenUpdatingManyHostsShouldUpdateThemAllIndependently() {
		Timestamp newerHost1= Fixtures.timestampNode1Seq1();
		tVectOne.updateTimestamp(newerHost1);
		tVectOne.updateTimestamp(Fixtures.timestampNode2Seq0());
		tVectTwo.updateTimestamp(Fixtures.timestampNode1Seq0());
		Timestamp newerHost2= Fixtures.timestampNode2Seq1();
		tVectTwo.updateTimestamp(newerHost2);

		tVectOne.updateMax(tVectTwo);

		Timestamp lastHost1= tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(newerHost1, lastHost1);
		Timestamp lastHost2= tVectOne.getLast(Fixtures.NODE_2);
		assertEquals(newerHost2, lastHost2);
	}

	@Test
	public void testWhenTsAreOlderDontUpdateToThem() {
		List<String> participants= Arrays.asList("groupXX@192.168.1.2:35006",
				"groupXX@192.168.1.2:35001",
				"groupXX@192.168.1.2:35000",
				"groupXX@192.168.1.2:35007",
				"groupXX@192.168.1.2:35003",
				"groupXX@192.168.1.2:35012",
				"groupXX@192.168.1.2:35009",
				"groupXX@192.168.1.2:35002",
				"groupXX@192.168.1.2:35008",
				"groupXX@192.168.1.2:35005",
				"groupXX@192.168.1.2:35004",
				"groupXX@192.168.1.2:35011",
				"groupXX@192.168.1.2:35010");
		
		TimestampVector local= new TimestampVector(participants);
		Timestamp t= new Timestamp("groupXX@192.168.1.2:35006", 2);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35001", 4);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35000", 4);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35007", 2);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35003", 4);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35012", 4);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35009", 5);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35002", 2);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35008", 3);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35005", 4);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35004", -1000);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35011", 2);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35010", 2);
		local.updateTimestamp(t);		

//		// arrives
		TimestampVector partner= new TimestampVector(participants);
		t= new Timestamp("groupXX@192.168.1.2:35006", 1);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35001", 1);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35000", 1);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35007", 0);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35003", 0);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35012", 0);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35009", 2);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35002", -1000);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35008", 1);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35005", 1);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35004", -1000);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35011", 0);
		local.updateTimestamp(t);
		t= new Timestamp("groupXX@192.168.1.2:35010", 0);
		local.updateTimestamp(t);	

		local.updateMax(partner);

		String s= local.toString();
		System.out.println(s);
	}
}
