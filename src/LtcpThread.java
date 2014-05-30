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
	private int port;
	
	

	public LtcpThread(Socket conexao) {
	
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
				System.out.println(msg.getMsg());
				if(msg.getMsg().startsWith("CNT")){		
						System.out.println("Cliente conectado");			
						
						Ltcp.setProc((float) (Ltcp.getProc() + (Math.random()*10)));		
						System.out.println("processamento de :"+ Ltcp.getProc());
						Ltcp.setNclientes(Ltcp.getNclientes() + 1);
						System.out.println(Ltcp.getNclientes());
						Ltcp.atualizarServidor();
						
				}
			}
				
			}
		catch (Exception e) {
			// TODO: handle exception
		}

}
}
