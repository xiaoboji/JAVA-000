# 作业

## 一、根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 的总结，提交到 Github。
### 1.1 GCLogAnalysis分析

| GC/MEM             | 128M | 1G    | 4G      |
| ------------------ | ---- | ---- | ---- |
| UseSerialGC        | OOM  | 3857 | 4652 |
| UseParallelGC      | OOM  | 3759 | 5165 |
| UseConcMarkSweepGC | OOM  | 3899 | 5023 |
| UseG1GC            | OOM  | 4012 | 5357 |

128M,512M,2G分别进行串行、并行、CMS、GC分析和记录
1. 128M测试情况下，都OOM了，老年代都满了。
2. 1G内存足够，随机性比较大，吞吐量都在3800-4000，没有数量级上的差异
3. 4G内存足够，随机性比较大，吞吐量都在4600-5400，没有数量级上的差异，不过G1感觉有点发力的趋势了

### 1.2 gateway-server-0.0.1-SNAPSHOT.jar压测
待实测后补充

### 1.3 总结

| 收集器            | 串/并发/并行 |新/老生代    |算法      | 目标      | 适用场景 |
| ------------------ | ---- | ---- | ---- | ---- | ---- |
| Serial        | 串行  | 新生代 | 复制算法 | 响应速度优先 | 单核环境Client模式 |
| Serial Old     | 串行  | 老年代 | 标记-整理算法 | 响应速度优先 | 单核环境Client模式，CMS的后备预案 |
| PerNew | 并行  | 新生代 | 复制算法 | 响应速度优先 | 多CPU环境时的Server模式下和CMS配合使用 |
| Parallel Scavenge           | 并行  | 新生代  | 复制算法 | 吞吐量优先 | 在后台运算而不需要太多交互的任务 |
| Parallel old           | 并行  | 老年代 | 标记-整理 | 吞吐量优先 | 在后台运算而不需要太多交互的任务 |
| CMS           | 并发 | 老年代 | 标记-清除算法 | 响应速度优先 | 集中在互联网站或者B/S系统服务器上的java应用 |
| G1           | 并发 | all | 标记-整理-复制算法 | 响应速度优先 | 面向服务端的应用，将来取代CMS |


## 二、写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 Github
### 2.1 HttpClient 或 OkHttp简介

1. OKHttp是一个处理网络请求的开源项目，Android 当前最火热网络框架，由移动支付Square公司贡献。

使用HttpClient发送请求主要分为一下几步骤：

- 创建 CloseableHttpClient对象或CloseableHttpAsyncClient对象，前者同步，后者为异步
- 创建Http请求对象
- 调用execute方法执行请求，如果是异步请求在执行之前需调用start方法

2. HttpClient 是 Apache Jakarta Common 下的子项目，可以用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包

使用OkHttp发送请求主要分为一下几步骤：

- 创建OkHttpClient对象
- 创建Request对象
- 将Request 对象封装为Call
- 通过Call 来执行同步或异步请求，调用execute方法同步执行，调用enqueue方法异步执行

### 2.2 HttpClient版本实现

添加maven依赖

>
    <dependency>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpcore</artifactId>
      <version>4.4.10</version>
    </dependency>

    <dependency>
      <groupId>org.apache.httpcomponents</groupId>
      <artifactId>httpclient</artifactId>
      <version>4.5.6</version>
    </dependency>

代码实现如下：

```
package week02;

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

```

### 2.3 OkHttp版本实现
添加maven依赖

>
    <dependency>
      <groupId>com.squareup.okhttp3</groupId>
      <artifactId>okhttp</artifactId>
      <version>4.7.0</version>
    </dependency>


代码实现如下：


```
package week02;

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
```

### 2.4 二者的差异
1. 性能上

根据以往的使用经验来看，两个差异不大，大部分业务上看个人和项目熟练度使用即可。

2. 使用上

OkHttp使用build模式创建对象来的更简洁一些，并且使用.post/.delete/.put/.get方法表示请求类型，不需要像HttpClient创建HttpGet、HttpPost等这些方法来创建请求类型。

3. 终端上

OkHttp android开发上用的多一些，web开发上二者应用都挺广泛
