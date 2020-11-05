
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 使用OkHttp3访问http://localhost:8801
 *
 * @author xiaoboji
 */
public class OkHttpDemo {
  public static void main(String[] args) throws IOException {
    OkHttpClient client = new OkHttpClient();
    try {
      // 执行http请求
      Request request = new Request.Builder().url("http://localhost:8801").build();
      Response response = client.newCall(request).execute();
      System.out.println("body内容:" + response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      client.clone();
    }
  }
}
