package MsgsDoServidor;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;

import Servidor.Servidor;


public class ConectarCliente extends Handler {

	public boolean receive(MsgTCP msg, Servidor serv,ObjectOutputStream enviar){

		if(msg.getMsg().startsWith( "CLIENTE" )){
			if(serv.getQ().isEmpty()){
				msg.setMsg("espera ltcp");
				try{
					enviar.writeObject(msg);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
			else{
				System.out.println("cliente conectado no servidor");
				Ltcp PrimeiroltspDaFila = serv.getQ().peek();
				//System.out.println(Servidor.getQ().size());
				msg.setMsg("ACEITA");
				msg.setPorta(PrimeiroltspDaFila.getPORT());
				try{
					enviar.writeObject(msg);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}


			return true;
		} 
		else {

			return nextSuccessor(msg, serv,enviar);
		}
	}



}
