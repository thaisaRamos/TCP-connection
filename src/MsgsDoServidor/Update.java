package MsgsDoServidor;

import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;

import Servidor.Servidor;

public class Update extends Handler{
	public boolean receive(MsgTCP msg, Servidor serv, ObjectOutputStream enviar) {
		if(msg.getMsg().startsWith("ATUALIZAR")){
			
			int nClient =msg.getNclients();
			float proc =msg.getProc();
			int port =msg.getPorta();
	
			for (Iterator<Ltcp> iterator=serv.getQ().iterator();iterator.hasNext();){
				
				Ltcp ltcpU = iterator.next();
				if (ltcpU.getPORT() == port){
						iterator.remove();
						ltcpU.setProc(proc);
						ltcpU.setNclientes(nClient);	
						serv.Q.add(ltcpU);
					}
				}
			
		
		
				
		
	return true;
		} 
		else {
			return nextSuccessor(msg, serv,enviar);
	}
	}
}
