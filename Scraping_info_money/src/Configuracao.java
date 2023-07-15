import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Configuracao {
	private String caminho = "C:\\Users\\AMD\\eclipse-workspace\\Scraping_info_money";
	private String link = "https://www.infomoney.com.br/mercados/";
	private boolean vazia = false;
	
	public Configuracao() {
		//while(true)
		executar();
	}
	
	public void executar() {
			Scrap.buscar(link, vazia);
		
		
	}

}
