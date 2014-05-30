package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;


public class TratarThread extends Thread {

	public static final int SERVICE_PORT =8000;
	static Socket socket;
	static ObjectOutputStream enviar;
	static ObjectInputStream receber;
	MsgTCP msg;
	private int port;
	Servidor serv;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public TratarThread(Socket conexao,Servidor sv) {
		serv = sv;
		socket = conexao;
		try{
			receber= new ObjectInputStream(socket.getInputStream());

			enviar = new ObjectOutputStream(socket.getOutputStream());
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}


	}

	public void run(){
		
		try{
			for(;;){
				msg =(MsgTCP)receber.readObject();
				boolean existeMsg =serv.conectarHandler.receive(msg,serv,enviar);
				if(!existeMsg){
					System.out.println("essa mensagem nao existe");
				}
				
			}
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}
}







