package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MergeMinTimestampVectorTest {

	private TimestampVector tVectOne;
	private TimestampVector tVectTwo;

	@Before
	public void setUp() throws Exception {
		tVectOne= new TimestampVector(Fixtures.twoParticipants());
		tVectTwo= new TimestampVector(Fixtures.twoParticipants());
	}

	@Test
	public void whenLocalTimestampIsNewerItShouldBeUpdatedByPartners() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq1());
		Timestamp older= Fixtures.timestampNode1Seq0();
		tVectTwo.updateTimestamp(older);

		tVectOne.mergeMin(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(older, last);
	}

	@Test
	public void whenPartnerTimestampIsNewerLocalTimestampShouldRemain() {
		Timestamp older= Fixtures.timestampNode1Seq0();
		tVectOne.updateTimestamp(older);
		tVectTwo.updateTimestamp(Fixtures.timestampNode1Seq1());

		tVectOne.mergeMin(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(older, last);
	}
	@Test
	public void whenBothTimestampAreTheSameShouldRemainTheSame() {
		tVectOne.updateTimestamp(Fixtures.timestampNode1Seq1());
		Timestamp newer= Fixtures.timestampNode1Seq1();
		tVectTwo.updateTimestamp(newer);

		tVectOne.mergeMin(tVectTwo);

		Timestamp last = tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(newer, last);
	}

	@Test
	public void whenUpdatingManyHostsShouldUpdateThemAllIndependently() {
		Timestamp olderHost1= Fixtures.timestampNode1Seq0();
		tVectOne.updateTimestamp(olderHost1);
		tVectOne.updateTimestamp(Fixtures.timestampNode2Seq1());
		tVectTwo.updateTimestamp(Fixtures.timestampNode1Seq1());
		Timestamp olderHost2= Fixtures.timestampNode2Seq0();
		tVectTwo.updateTimestamp(olderHost2);

		tVectOne.mergeMin(tVectTwo);

		Timestamp lastHost1= tVectOne.getLast(Fixtures.NODE_1);
		assertEquals(olderHost1, lastHost1);
		Timestamp lastHost2= tVectOne.getLast(Fixtures.NODE_2);
		assertEquals(olderHost2, lastHost2);
	}
}
