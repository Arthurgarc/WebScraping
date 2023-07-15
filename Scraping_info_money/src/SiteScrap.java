import java.io.OutputStreamWriter;

public class SiteScrap {
	private boolean vazia = true;
	
	public SiteScrap(String tituloLink) {
		Scrap.buscar(tituloLink, vazia);
	}

}
