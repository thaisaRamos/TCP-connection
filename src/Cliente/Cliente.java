package Cliente;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import Ltcp.MsgTCP;




public class Cliente{

	public static int PORT;
	static ObjectOutputStream enviar;
	static ObjectInputStream receber;
	public static Integer ltsp_port;
	public static InetAddress ltsp_address;
	static Socket conexao;
	static Socket aux;
	Socket conexaoLtcp;
	MsgTCP msg;
	ObjectOutputStream out;
	boolean conexaoEstabelecida=false;

	public Cliente(){
		solicitarConexao();
		receberMensagens();

	}

	private void receberMensagens() {
		while(!conexaoEstabelecida){

			try{
				MsgTCP msg = (MsgTCP)receber.readObject();


				if(msg.getMsg().startsWith("espera")){
					solicitarConexao();
				}
				if(msg.getMsg().startsWith("ACEITA")){

					PORT = msg.getPorta();
					msg.setMsg("CNT CLIENTE-LTSP");
					aux = new Socket("localhost",PORT);
					 out = new ObjectOutputStream(aux.getOutputStream());
					out.writeObject(msg);
					conexaoEstabelecida = true;
				}



			}
			catch (Exception e) {

			}
		}

	}

	public void solicitarConexao(){
		try
		{
			conexao = new Socket ("localhost", 8000);
			msg = new MsgTCP();
			msg.setMsg( "CLIENTE CONECTAR" );

			enviar = new ObjectOutputStream(conexao.getOutputStream());
			enviar.writeObject(msg);
			receber= new ObjectInputStream(conexao.getInputStream());

		}
		catch (Exception e) {
			solicitarConexao();
		}
	}
	public void listarArquivos() {
		msg.setMsg("LISTAR");
		try{
			out.writeObject(msg);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void DownloadAquivo() {
		msg.setMsg("DOWNLOAD");
		try{
			out.writeObject(msg);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}


	public void desconect() {
		msg.setMsg("DESCONECTAR");
		try{
			out.writeObject(msg);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
