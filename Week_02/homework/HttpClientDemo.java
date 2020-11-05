
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 使用HttpClient访问http://localhost:8801
 *
 * @author xiaoboji
 */
public class HttpClientDemo {
  public static void main(String[] args) {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet("http://localhost:8801");
    CloseableHttpResponse response = null;
    try {
      // 执行http请求
      response = httpClient.execute(httpGet);
      HttpEntity httpEntity = response.getEntity();
      if (httpEntity != null) {
        System.out.println("body内容:" + EntityUtils.toString(response.getEntity(), "UTF-8"));
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
        httpClient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
