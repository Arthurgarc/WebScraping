
public class Configuracao {
	private static final String link = "https://www.infomoney.com.br/mercados/";
	private boolean primeiraConexao = true;

	public Configuracao() {
		executar();
	}

	public void executar() {
		Scrap.buscar(link, primeiraConexao);

	}

}
