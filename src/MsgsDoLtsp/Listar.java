 package MsgsDoLtsp;



import java.io.File;

import Ltcp.Ltcp;
import Ltcp.MsgTCP;

public class Listar extends Handler {

	public boolean receive(MsgTCP msg,Ltcp ltcp) {
		if (msg.getMsg().startsWith("LISTAR")){
			String caminho = javax.swing.JOptionPane.showInputDialog("digite o diretorio a ser listado");   
	        StringBuilder sb = new StringBuilder();   
	           
	        File raiz = new File(caminho);   
	           
	        for(File f: raiz.listFiles()) {   
	            if(f.isFile()) {   
	                System.out.println(f.getName());   
	                sb.append(f.getName());   
	                sb.append("\n");   
	            }   
	        }   
	        javax.swing.JOptionPane.showMessageDialog(null, sb.toString()); 
		return true;
		}
		else{
			return nextSuccessor(msg,ltcp);
		}
	}


}
