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





public class Ltcp {


	public static int PORT;

	private static ServerSocket serverSocket;
	private static int nclientes;
	private static float proc;
	private int id;
	static MsgTCP msg;
	static ObjectOutputStream output;
	ObjectInputStream receber;
	static Socket conexao;
	static Socket aux;
	public Ltcp(int port){
		nclientes =0;
		proc = 0;
		PORT = port;

	}
	public static void atualizarServidor(){
		MsgTCP msg = new MsgTCP();
		msg.setMsg("ATUALIZAR");
		msg.setNclients(getNclientes());
		msg.setProc(getProc());
		msg.setPorta(PORT);
		try{
			conexao = new Socket ("localhost", 8000);
			output = new ObjectOutputStream(conexao.getOutputStream());
			output.writeObject(msg);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void solicitarConexao(){
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
		msg.setPorta(PORT);
		try{
			output.writeObject(msg);
			//this.serverSocket = new ServerSocket(PORT);



		}
		catch (Exception e) {
			// TODO: handle exception
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
		if (args.length != 1){
			System.out.println("parametro errados");

		}

		Ltcp ltcp = new Ltcp(Integer.parseInt(args[0]));
		PORT = Integer.parseInt(args[0]);
		Ltcp.setPORT(PORT);
		ltcp.solicitarConexao();
		try{
			serverSocket = new ServerSocket(PORT);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		for(;;){
			try{
				aux= serverSocket.accept();		
				System.out.println("recebido conexao");
				LtcpThread t = new LtcpThread(aux);
				t.start();
			}
			catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public static float getProc() {
		return proc;
	}

	public static void setProc(float proce) {
		proc = proce;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getNclientes() {
		return nclientes;
	}

	public static void setNclientes(int nclientess) {
		nclientes = nclientess;
	}
	public int getPORT() {
		return PORT;
	}

	public static void setPORT(int pORT) {
		PORT = pORT;
	}
}






