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

public class ConectarLtsp extends Handler {
	public boolean receive(MsgTCP msg, Servidor serv, ObjectOutputStream enviar) {
		if(msg.getMsg().startsWith( "LTSP" )){
			Ltcp ltcp= new Ltcp();
			System.out.println("Ltsp conectado na porta: " +msg.getPorta());
			ltcp.setPORT(msg.getPorta());
			ltcp.setNclientes(msg.getNclients());
			ltcp.setProc(msg.getProc());

			serv.getQ().add(ltcp);


			return true;
		} 
		else {
			return nextSuccessor(msg,serv,enviar);
		}
	}



}
