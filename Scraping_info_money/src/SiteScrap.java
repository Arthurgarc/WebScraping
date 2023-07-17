import java.text.SimpleDateFormat;
import java.util.Date;

public class SiteScrap {
	private static boolean primeiraConexao = false;

	public static void buscarScrap(String tituloLink) {
		Scrap.buscar(tituloLink, primeiraConexao);
	}

	public static void passarPorPaginacoes() {
		for (int passarPorPaginacoes = 1; passarPorPaginacoes < 3; passarPorPaginacoes++) {

			Date dataHoraAtual = new Date();
			String data = new SimpleDateFormat("dd.MM.yyyy").format(dataHoraAtual);

			String pagina;
			if (passarPorPaginacoes < 2) {
				pagina = "2";
			} else {
				pagina = "3";
			}
			ApiRequester.montarBody(pagina, data);
		}
	}

}
