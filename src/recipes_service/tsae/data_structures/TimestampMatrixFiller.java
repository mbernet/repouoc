package recipes_service.tsae.data_structures;

import java.util.ArrayList;
import java.util.Set;

public class TimestampMatrixFiller {

	private TimestampMatrix tMtx;
	private Set<String> participants;

	public TimestampMatrixFiller(TimestampMatrix tMtx) {
		this.tMtx= tMtx;
		participants= tMtx.timestampMatrix.keySet();
	}

	public void fill(String node, Timestamp... timestamps) {
		TimestampVector tVect = new TimestampVector(new ArrayList<String>(participants));
		for (Timestamp tstmp : timestamps) {
			tVect.updateTimestamp(tstmp);
		}
		tMtx.update(node, tVect);		
	}

}
