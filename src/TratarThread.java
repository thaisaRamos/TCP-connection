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


public class TratarThread extends Thread {

	public static final int SERVICE_PORT =8000;
	static Socket socket;
	static ObjectOutputStream enviar;
	static ObjectInputStream receber;
	MsgTCP msg;
	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public TratarThread(Socket conexao) {

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
				//System.out.println(msg.getMsg());

				if(msg.getMsg().startsWith( "LTSP" )){
					System.out.println("Ltsp conectado na porta: " +msg.getPorta());
					Ltcp ltcp = new Ltcp(msg.getPorta());
					//Servidor.setPort(msg.getPorta());
					Servidor.adcionarQ(ltcp);
					System.out.println(Servidor.getQ().size());

				}
				if(msg.getMsg().startsWith( "CLIENTE" )){
					if(Servidor.getQ().isEmpty()){
						msg.setMsg("espera ltcp");
						enviar.writeObject(msg);
					}
					else{
						System.out.println("cliente conectado no servidor");
						Ltcp PrimeiroltspDaFila = Servidor.getQ().peek();
						//System.out.println(Servidor.getQ().size());
						msg.setMsg("ACEITA");
						msg.setPorta(PrimeiroltspDaFila.getPORT());
						enviar.writeObject(msg);
					}
				}
				if(msg.getMsg().startsWith("ATUALIZAR")){
					System.out.println("atualizar");
					int nClient =msg.getNclients();
					float proc =msg.getProc();
					int port =msg.getPorta();
					for (Iterator<Ltcp> iterator=Servidor.getQ().iterator();iterator.hasNext();){
						
						Ltcp ltcp = iterator.next();
						
						if (ltcp.getPORT() == port){
							
								iterator.remove();
								ltcp.setProc(proc);
								ltcp.setNclientes(nClient);	
								
								Servidor.getQ().add(ltcp);
								
							}
						}
					
				}
			}
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}
}







