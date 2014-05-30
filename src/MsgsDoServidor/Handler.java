/**
 * 
 */
package MsgsDoServidor;




import java.io.ObjectOutputStream;

import Ltcp.MsgTCP;

import Servidor.Servidor;

public abstract class Handler {

	private Handler successor = null;

	public abstract boolean receive(MsgTCP msg, Servidor sv,ObjectOutputStream enviar);


	public void setSuccessor(Handler su) {

		this.successor = su;
	}

	public boolean nextSuccessor(MsgTCP msg, Servidor sv,ObjectOutputStream enviar){
		if (this.successor == null) return false;
		return this.successor.receive(msg,sv,enviar);
	}

}

