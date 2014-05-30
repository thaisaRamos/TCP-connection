package Ltcp;
import java.io.Serializable;


public class MsgTCP implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;
	private float proc;
	private int nclients;
	private int idDoLtcp;
	private int porta;
	public String getMsg() {
		return msg;
	}
	public void clear(){
		msg = new String();
		proc = 0;
		nclients=0;
		idDoLtcp= 0;
		
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getProc() {
		return proc;
	}

	public void setProc(float proc) {
		this.proc = proc;
	}

	public int getNclients() {
		return nclients;
	}

	public void setNclients(int nclients) {
		this.nclients = nclients;
	}

	public int getIdDoLtcp() {
		return idDoLtcp;
	}

	public void setIdDoLtcp(int idDoLtcp) {
		this.idDoLtcp = idDoLtcp;
	}
	public int getPorta() {
		return porta;
	}
	public void setPorta(int porta) {
		this.porta = porta;
	}



}
