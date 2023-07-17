import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ApiRequester {
	private static final String urlNovasNoticias = "https://www.infomoney.com.br/mercados/?infinity=scrolling";

	public static void montarBody(String pagina, String data) {
		try {

			String body = "action=" + URLEncoder.encode("infinite_scroll", StandardCharsets.UTF_8) + "&page="
					+ URLEncoder.encode(pagina, StandardCharsets.UTF_8) + "&currentday="
					+ URLEncoder.encode(data, StandardCharsets.UTF_8) + "&order="
					+ URLEncoder.encode("DESC", StandardCharsets.UTF_8);

			fazerRequisicaoPost(urlNovasNoticias, body);

		} catch (IOException e) {
			System.out.println("Falha ao montar o body!");
		}
	}

	private static void fazerRequisicaoPost(String url, String body) throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(body, ContentType.APPLICATION_FORM_URLENCODED);
			httpPost.setEntity(entity);

			try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
				HttpEntity responseEntity = response.getEntity();
				String html = EntityUtils.toString(responseEntity);
				Scrap.acharSiteNoticiasInfiniteScroll(html);
			}
		}
	}
}
