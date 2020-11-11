import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: W4-Thu-2
 * @description: Callable接口异步获取计算结果
 * @author: xiaoboji
 * @create: 2020-11-11 23:21
 **/
public class CallableMethod {
  public static void main(String[] args) throws ExecutionException, InterruptedException {

    long start=System.currentTimeMillis();
    // 在这里创建一个线程或线程池，
    // 异步执行 下面方法
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<Integer> f = executorService.submit(
        ()->{return sum();}
    );
    executorService.shutdown();

    //这是得到的返回值
    int result = 0;
    result = f.get();
    // 确保  拿到result 并输出
    System.out.println("异步计算结果为："+result);

    System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    // 然后退出main线程
  }

  private static int sum() {
    return fibo(36);
  }

  private static int fibo(int a) {
    if ( a < 2)
      return 1;
    return fibo(a-1) + fibo(a-2);
  }
}
