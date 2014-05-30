package Ltcp;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import MsgsDoLtsp.Conectar;
import MsgsDoLtsp.Desconectar;
import MsgsDoLtsp.Download;
import MsgsDoLtsp.Handler;
import MsgsDoLtsp.Listar;





public class Ltcp {


	public int PORT;

	private static ServerSocket serverSocket;
	private  int nclientes;
	private  float proc;
	private  int porta;
	MsgTCP msg;
	ObjectOutputStream output;
	ObjectInputStream receber;
	Socket conexao;
	Socket aux;

	Handler conectar;
	Handler desconectar;
	Handler download;
	Handler listar;
	
	public Ltcp(){
		nclientes =0;
		proc = 0;
		PORT =0;

	}

	public void atualizarServidor(){
		MsgTCP msg = new MsgTCP();
		msg.setMsg("ATUALIZAR");
		msg.setNclients(getNclientes());
		msg.setProc(getProc());
		msg.setPorta(getPORT());
	
		try{
			conexao = new Socket ("localhost", 8000);
			output = new ObjectOutputStream(conexao.getOutputStream());
			output.writeObject(msg);
		}
		catch (Exception e) {
			System.out.println("n envio");
		}
	}
	public void solicitarConexao(){
		conectar = new Conectar();
		desconectar = new Desconectar();
		listar = new Listar();
		download = new Download();
		
		conectar.setSuccessor(desconectar);
		desconectar.setSuccessor(listar);
		listar.setSuccessor(download);
		try{

			conexao = new Socket ("localhost", 8000);
			
			
			output = new ObjectOutputStream(conexao.getOutputStream());

			pedirRegistro();


		}
		catch (Exception e) {
			solicitarConexao();
		}
	}
	public void pedirRegistro(){
		msg = new MsgTCP();
		msg.setMsg( "LTSP CONECTAR" );
		msg.setNclients(0);
		msg.setProc(0);
		msg.setPorta(getPORT());
		try{
			output.writeObject(msg);
			//this.serverSocket = new ServerSocket(PORT);



		}
		catch (Exception e) {
			pedirRegistro();
		}
	}
	public void escutar(){
		try{

			/*	System.out.println(PORT);
			aux= serverSocket.accept();		
			System.out.println("recebido conexao");
			receber = new ObjectInputStream(aux.getInputStream());
			msg =(MsgTCP)receber.readObject();
			 */
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String args[])
	{
		//if (args.length != 1){
		//System.out.println("parametro errados");

		//}
		Ltcp ltcp = new Ltcp();
		ltcp.GeraPorta();
		
		
		
		ltcp.solicitarConexao();
		for(;;){
			try{
				ltcp.aux= serverSocket.accept();		
				System.out.println("recebido conexao");
				LtcpThread t = new LtcpThread(ltcp.aux,ltcp);
				t.start();
			}
			catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	private void GeraPorta() {
		setPORT((int) ((int) 7000 +(Math.random()*100)));
		try{
			serverSocket = new ServerSocket(getPORT());
		}
		catch (Exception e) {
			setPORT((int) ((int) 7000 +(Math.random()*100)));
			GeraPorta();
		}

		
	}

	public  float getProc() {
		return proc;
	}

	public  void setProc(float proce) {
		proc = proce;
	}

	public int getId() {
		return porta;
	}

	public void setId(int id) {
		this.porta = id;
	}

	public int getNclientes() {
		return nclientes;
	}

	public void setNclientes(int nclientess) {
		nclientes = nclientess;
	}
	public int getPORT() {
		return PORT;
	}

	public void setPORT(int pORT) {
		PORT = pORT;
	}
}






