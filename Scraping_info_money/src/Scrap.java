import java.io.OutputStreamWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scrap {
	public static void buscar(String link, boolean vazia) {
		Document doc = null;
	
		try {
			doc = Jsoup.connect(link).get();
		}catch (Exception e) {
			System.out.println("Falha na conexão!");
		}
		
		if(!vazia) {
			acharSite(doc);
		}else {
			encontrarNoticia(doc);
		}
	}
		
		
		public static void acharSite(Element doc) {
			Elements elemento = doc.select("section.articlespack-list");
			Elements element = elemento.select("article.article-card");
			Elements titulo = element.select("div.article-card__asset");
			Elements tituloDaNoticia = titulo.select("a[href]");
		
			String tituloLink = tituloDaNoticia.attr("href");
			System.out.println("URL: " + tituloLink);
		
			SiteScrap siteScrap = new SiteScrap(tituloLink);
		}
		
		public static void encontrarNoticia(Element doc) {
			Elements elementoTitulo = doc.select("div.layout-container");
			Elements cabecalho = elementoTitulo.select("h1.typography__display--2");
			System.out.println("Título: " + cabecalho.text());
			
			
			Elements subtitulo = elementoTitulo.select("div.single__excerpt typography__body--2 spacing--mb4");
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
			
			System.out.println("");
			System.out.println("---------------------");
			System.out.println("");
			
		}

	}
