/*
* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package recipes_service.tsae.sessions;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import communication.ObjectInputStream_DS;
import communication.ObjectOutputStream_DS;
import recipes_service.ServerData;
import recipes_service.communication.Message;
import recipes_service.communication.MessageAErequest;
import recipes_service.communication.MessageEndTSAE;
import recipes_service.communication.MessageOperation;
import recipes_service.communication.MsgType;
import recipes_service.data.AddOperation;
import recipes_service.data.Operation;
import recipes_service.data.RemoveOperation;
import recipes_service.tsae.data_structures.TimestampMatrix;
import recipes_service.tsae.data_structures.TimestampVector;

//LSim logging system imports sgeag@2017
import lsim.worker.LSimWorker;
import edu.uoc.dpcs.lsim.LSimFactory;
import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;

/**
 * @author Joan-Manuel Marques
 * December 2012
 *
 */
public class TSAESessionPartnerSide extends Thread{
	// Needed for the logging system sgeag@2017
	private LSimWorker lsim = LSimFactory.getWorkerInstance();
	
	private Socket socket = null;
	private ServerData serverData = null;
	
	public TSAESessionPartnerSide(Socket socket, ServerData serverData) {
		super("TSAEPartnerSideThread");
		this.socket = socket;
		this.serverData = serverData;
	}

	public void run() {

		Message msg = null;

		int current_session_number = -1;
		try {
			ObjectOutputStream_DS out = new ObjectOutputStream_DS(socket.getOutputStream());
			ObjectInputStream_DS in = new ObjectInputStream_DS(socket.getInputStream());
			
			// receive originator's summary and ack
			msg = (Message) in.readObject();
			current_session_number = msg.getSessionNumber();
			
			if (msg.type() == MsgType.AE_REQUEST){
			
				TimestampVector localSummary;
				TimestampMatrix localAck;
				
				synchronized(serverData) {
					localSummary = serverData.getSummary().clone();
	                serverData.getAck().update(serverData.getId(), localSummary);
	                localAck = serverData.getAck().clone();
				}
				
				// send operations
				MessageAErequest msgAE = (MessageAErequest) msg;
				List<Operation> newerOperations = new ArrayList<Operation>();
				
				newerOperations = this.serverData.getLog().listNewer(msgAE.getSummary());
				for(Operation op: newerOperations) {
					MessageOperation msgOp = new MessageOperation(op);
					msgOp.setSessionNumber(current_session_number);
					out.writeObject(msgOp);
				}

				// send to originator: local's summary and ack
				msg = new MessageAErequest(localSummary, localAck);
				msg.setSessionNumber(current_session_number);
	 	        out.writeObject(msg);

	            // receive operations
				msg = (Message) in.readObject();
				List<MessageOperation> operations = new ArrayList<MessageOperation>();
				while (msg.type() == MsgType.OPERATION){
					operations.add((MessageOperation)msg);
					msg = (Message) in.readObject();
				}
				
				// receive message to inform about the ending of the TSAE session
				if (msg.type() == MsgType.END_TSAE){
					
					msg = new MessageEndTSAE();  
					out.writeObject(msg);	
					
					synchronized(serverData) {
						for(MessageOperation op: operations) {
							switch(op.getOperation().getType()) {
								case ADD:
									AddOperation addOp = (AddOperation)op.getOperation();
									this.serverData.addRecipeOperation(addOp);
									break;
								case REMOVE:
									RemoveOperation removeOp = (RemoveOperation)op.getOperation();
									this.serverData.removeRecipeOperation(removeOp);
									break;
							}
							
						}
					
						//update summaries and trigger the message ordering component
						serverData.getSummary().updateMax(msgAE.getSummary());
						serverData.getAck().updateMax(msgAE.getAck());
						serverData.getLog().purgeLog(serverData.getAck());
					}
				}
				
			}
			socket.close();		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//lsim.log(Level.FATAL, "[TSAESessionPartnerSide] [session: "+current_session_number+"]" + e.getMessage());
			e.printStackTrace();
            System.exit(1);
		}catch (IOException e) {
	    }
		
		//lsim.log(Level.TRACE, "[TSAESessionPartnerSide] [session: "+current_session_number+"] End TSAE session");
	}
}