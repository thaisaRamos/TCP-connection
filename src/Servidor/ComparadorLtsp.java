package Servidor;





import java.util.Comparator;

import Ltcp.Ltcp;




public class ComparadorLtsp implements  Comparator<Ltcp> {

	public int compare(Ltcp l1, Ltcp l2) {
		
		return (int) ( (l1.getProc() - l2.getProc()) );
		}

	

	
}

	

