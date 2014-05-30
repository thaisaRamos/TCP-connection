/**
 * 
 */
package MsgsDoLtsp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;


public abstract class Handler {
	
	private Handler successor = null;
	
	public abstract boolean receive(MsgTCP msg,Ltcp ltcp);
	
	
	public void setSuccessor(Handler su) {
	
		this.successor = su;
	}
	
	public boolean nextSuccessor(MsgTCP msg,Ltcp ltcp){
	if (this.successor == null) return false;
	return this.successor.receive(msg,ltcp);
	}
	
}

