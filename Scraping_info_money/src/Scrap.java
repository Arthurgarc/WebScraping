import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
	public static void buscar(String link, boolean primeiraConexao) {

		try {
			Document doc = Jsoup.connect(link).get();

			if (primeiraConexao) {
				acharSite(doc);
			}
			encontrarNoticia(doc);

		} catch (Exception e) {
			System.out.println("Falha na conexão!");
		}
	}

	public static void acharSite(Element doc) {

		for (Element elemento : doc.select("section.articlespack-list")) {
			for (Element element : elemento.select("article.article-card")) {
				Elements url = element.select("div.article-card__asset");
				Elements urlNoticia = url.select("a[href]");

				String urlNoticiaSite = urlNoticia.attr("href");
				System.out.println("URL: " + urlNoticiaSite);

				SiteScrap.buscarScrap(urlNoticiaSite);
			}
		}

		SiteScrap.passarPorPaginacoes();
	}

	public static void encontrarNoticia(Element doc) {
		Elements elementoTitulo = doc.select("div.layout-container");
		Elements cabecalho = elementoTitulo.select("h1.typography__display--2");
		System.out.println("Título: " + cabecalho.text());

		Elements subtituloAjustes = elementoTitulo.select("p");
		System.out.println("Subtítulo: " + subtituloAjustes.first().text());

		Elements autor = elementoTitulo.select("span.typography__body--5");
		Elements autorNoticia = autor.select("a");
		System.out.println("Autor: " + autorNoticia.text());

		Elements data = elementoTitulo.select("span.typography__body--6");
		Elements dataFormatada = data.select("time");
		System.out.println("Data e hora: " + dataFormatada.first().text());

		Elements conteudo = elementoTitulo.select("div.single__content");
		Elements conteudoNoticia = conteudo.select("p");
		System.out.println("Conteúdo da notícia: " + conteudoNoticia.text());

		System.out.println("\n---------------------\n");

	}

	public static void acharSiteNoticiasInfiniteScroll(String html) {
		String htmlSemCaractereEscape = html.replaceAll("\\\\", "");
		Document document = Jsoup.parse(htmlSemCaractereEscape);

		for (Element elementoNoticias : document.select("article.article-card")) {
			Elements elementoDaNoticia = elementoNoticias.select("div.article-card__asset");
			Elements urlDaNoticia = elementoDaNoticia.select("a[href]");
			String tituloDoLink = urlDaNoticia.attr("href");
			System.out.println("URL: " + tituloDoLink);
			SiteScrap.buscarScrap(tituloDoLink);
		}
	}

}
