package Cliente;



import Servidor.Servidor;

public class ServicosCliente {
	Cliente cliente;
	
	public ServicosCliente() {
	
	cliente = new Cliente();

	
	}
	
	public void ListaArquivos (){
			cliente.listarArquivos();
	}

	public void DownloadArquivos(){
		
		
			cliente.DownloadAquivo();
		
	}
	
	public void desconectar(){
	
			cliente.desconect();
		
	}
	
}
