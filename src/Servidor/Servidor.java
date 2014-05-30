package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import MsgsDoServidor.ConectarCliente;
import MsgsDoServidor.ConectarLtsp;
import MsgsDoServidor.Handler;
import MsgsDoServidor.Update;






public class Servidor {

	public static final int SERVICE_PORT =8000;
	static Socket socket;
	public  Comparator<Ltcp> comparador = new ComparadorLtsp();
	public  PriorityBlockingQueue<Ltcp> Q = new PriorityBlockingQueue<Ltcp>(6, comparador);
	
	private static int  port;
	Handler conectarHandler;
	Handler conectarLtspHandler;
	Handler updateHandler;

	public Servidor(){
		conectarHandler = new ConectarCliente();
		conectarLtspHandler = new ConectarLtsp();
		updateHandler = new Update();

		conectarHandler.setSuccessor(conectarLtspHandler);
		conectarLtspHandler.setSuccessor(updateHandler);
	}
		
	public  PriorityBlockingQueue<Ltcp> getQ() {
		return Q;
	}

	public void setQ(PriorityBlockingQueue<Ltcp> q) {
		Q = q;
	}
	public  void adcionarQ(Ltcp ltcp){
		Q.add(ltcp);
	}

	public static void main(String[] args){
		Servidor sv = new Servidor();
			try
			{
				
				ServerSocket server = new ServerSocket (SERVICE_PORT);
		
					
					for(;;){
						
						socket = server.accept();
						TratarThread t= new TratarThread(socket,sv);
						t.start();	
					}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}



	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Servidor.port = port;
	}
}