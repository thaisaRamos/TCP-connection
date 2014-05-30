package MsgsDoLtsp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;



public class Conectar extends Handler {

	public boolean receive(MsgTCP msg,Ltcp ltcp) {
		if(msg.getMsg().startsWith("CNT")){		
			System.out.println("Cliente conectado");			
			ltcp.setProc((float) (ltcp.getProc() + (Math.random()*10)));		
			
			ltcp.setNclientes(ltcp.getNclientes() + 1);
			System.out.println("processamento de :"+ ltcp.getProc() + "  numero de cliente :"+ ltcp.getNclientes() + "  porta: "+ ltcp.getPORT());
			ltcp.atualizarServidor();
			
			return true;
	}
		else{
			return nextSuccessor(msg,ltcp);
		}

	

}
}