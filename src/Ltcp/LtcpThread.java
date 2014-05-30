package Ltcp;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class LtcpThread extends Thread {
	
	public static final int SERVICE_PORT =8000;
	static Socket socket;
	static ObjectOutputStream enviar;
	static ObjectInputStream receber;
	MsgTCP msg;
	Ltcp ltcp;
	
	

	public LtcpThread(Socket conexao,Ltcp ltcpp) {
		ltcp =ltcpp;
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
				ltcp.atualizarServidor();
				msg =(MsgTCP)receber.readObject();
				boolean existeMsg;
				existeMsg = ltcp.conectar.receive(msg, ltcp );
				if(!existeMsg){
					System.out.println("Essa mensagem nao existe");
				}
				
				ltcp.atualizarServidor();
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}

}
}
