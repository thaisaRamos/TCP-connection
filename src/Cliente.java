import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;




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


	public static void main(String args[])
	{
		Cliente client = new Cliente();
		client.solicitarConexao();
		
		for(;;){
			try{
				MsgTCP msg = (MsgTCP)receber.readObject();
			//	System.out.println(msg.getMsg());
			//	System.out.println(msg.getPorta());

				if(msg.getMsg().startsWith("espera")){
					client.solicitarConexao();
				}
				if(msg.getMsg().startsWith("ACEITA")){
					//System.out.println("conectado");
					
					PORT = msg.getPorta();
					msg.setMsg("CNT CLIENTE-LTSP");
					aux = new Socket("localhost",PORT);
					
					ObjectOutputStream out = new ObjectOutputStream(aux.getOutputStream());
					out.writeObject(msg);
					
				}

				if (msg.getMsg().contains("aus")){	
					try{

						System.out.println ("Conexão aceita para:"+ msg.getMsg().substring(18, 24));			
						ltsp_port = Integer.parseInt(msg.getMsg().substring(18,24).trim());
						try {
							ltsp_address = InetAddress.getByName(msg.getMsg().substring(8,17));
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(ltsp_address);
						client.conexaoLtcp = new Socket("localHost",8001);
						msg.setMsg("ltscp");
						enviar.writeObject(msg);
					}
					catch (Exception e) {
						// TODO: handle exception
					}


				}
			}
			catch (Exception e) {
				//System.out.println("hmm");
			}
		}
	}


	

}
