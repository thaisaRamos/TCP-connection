package MsgsDoLtsp;


import Ltcp.Ltcp;
import Ltcp.MsgTCP;


public class Desconectar extends Handler {

	@Override
	public boolean receive(MsgTCP msg,Ltcp ltcp) {
		if( msg.getMsg().startsWith("DESCONECTAR")){		
			System.out.println("Cliente desconectado");			
					
			ltcp.setProc((float) (ltcp.getProc() - ( Math.random()*10 )));	
			if( ltcp.getProc() < 0 ){
				ltcp.setProc(0);			
			}
			
			ltcp.setNclientes(ltcp.getNclientes() - 1);
			
			if(ltcp.getNclientes()==0){
				ltcp.setProc(0);
			}
			System.out.println("processamento de: "+ ltcp.getProc() + " numero de cliente: "+ ltcp.getNclientes());
			ltcp.atualizarServidor();
			
	return true;
		}
		else {
			return nextSuccessor(msg,ltcp);
		}


}
}
