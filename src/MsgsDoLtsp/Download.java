package MsgsDoLtsp;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;




public class Download extends Handler{

	
	public boolean receive(MsgTCP msg,Ltcp ltcp) {
		if(msg.getMsg().startsWith("DOWNLOAD")){
			try {
				String caminho = javax.swing.JOptionPane.showInputDialog("digite o arquivo para download");   

				Runtime.getRuntime().exec("explorer C:\\Users\\Thaisa\\Downloads\\"+ caminho);
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			return true;
		}
		else {
			return nextSuccessor(msg, ltcp);
		}
		
}
}
	
