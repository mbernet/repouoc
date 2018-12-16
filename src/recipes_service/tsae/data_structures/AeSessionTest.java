package recipes_service.tsae.data_structures;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AeSessionTest {

	private List<String> participants = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		participants.add("495432551@10.1.255.251:35001");
		participants.add("495432551@10.1.255.247:35000");
		participants.add("495432551@10.1.255.249:35001");
		participants.add("495432551@10.1.255.248:35000");
		participants.add("495432551@10.1.255.250:35001");
		participants.add("495432551@10.1.255.252:35001");
		participants.add("495432551@10.1.255.250:35000");
		participants.add("495432551@10.1.255.252:35000");
		participants.add("495432551@10.1.255.247:35001");
		participants.add("495432551@10.1.255.249:35000");
		participants.add("495432551@10.1.255.251:35000");
	}

	@Test
	public void whenAeSessionFinishesBothAcksShouldBeEqual() {
		TimestampMatrix expectedAck = expectedAck();
		TimestampMatrix differentAck = differentAck();
		differentAck.updateMax(expectedAck);
		assertEquals(differentAck, expectedAck);

		TimestampMatrix initialAck = expectedAck();
		expectedAck= initialAck.clone();
		differentAck = differentAck();
		initialAck.updateMax(differentAck);
		assertEquals(initialAck, expectedAck);
	}

	private TimestampMatrix expectedAck() {
		TimestampMatrix ack = new TimestampMatrix(participants);

		TimestampVector sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.251:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.247:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.249:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.248:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.250:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", -1000));
		ack.update("495432551@10.1.255.250:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", -1000));
		ack.update("495432551@10.1.255.247:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.249:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.251:35000", sum);

		return ack;
	}

	private TimestampMatrix differentAck() {
		TimestampMatrix ack = new TimestampMatrix(participants);

		TimestampVector sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", -1000));


		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.247:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.249:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.248:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.250:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", -1000));
		ack.update("495432551@10.1.255.250:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.252:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 1));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 0));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", -1000));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", -1000));
		ack.update("495432551@10.1.255.247:35001", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.249:35000", sum);

		sum = new TimestampVector(participants);
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35001", 4));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.248:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35001", 5));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35001", 6));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.250:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.252:35000", 3));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.247:35001", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.249:35000", 2));
		sum.updateTimestamp(new Timestamp("495432551@10.1.255.251:35000", 4));
		ack.update("495432551@10.1.255.251:35000", sum);
		return ack;
	}
}
