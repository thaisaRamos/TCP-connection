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






public class Servidor {

	public static final int SERVICE_PORT =8000;
	static Socket socket;
	private static Comparator<Ltcp> comparador = new ComparadorLtsp();
	private static PriorityBlockingQueue<Ltcp> Q = new PriorityBlockingQueue<Ltcp>(6, comparador);
	private static int i=0;
	private static int  port;
		
	public static PriorityBlockingQueue<Ltcp> getQ() {
		return Q;
	}

	public void setQ(PriorityBlockingQueue<Ltcp> q) {
		Q = q;
	}
	public static void adcionarQ(Ltcp ltcp){
		Q.add(ltcp);
	}

	public static void main(String[] args){
			try
			{
				
				ServerSocket server = new ServerSocket (SERVICE_PORT);
		
					
					for(;;){
						
						socket = server.accept();
						TratarThread t= new TratarThread(socket);
						t.start();	
					}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}

	public static int getI() {
		return i;
	}

	public static void setI(int i) {
		Servidor.i = i;
	}

	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Servidor.port = port;
	}
}