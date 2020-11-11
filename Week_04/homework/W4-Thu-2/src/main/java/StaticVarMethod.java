import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: W4-Thu-2
 * @description: 使用静态变量记录计算结果
 * @author: xiaoboji
 * @create: 2020-11-11 23:24
 **/
public class StaticVarMethod {
  public static int result = 0;

  public static void main(String[] args) throws InterruptedException {

    long start=System.currentTimeMillis();
    // 在这里创建一个线程或线程池，异步执行下面方法

    Thread thread  = new Thread(new Runnable(){
      public void run() {
        result = sum();
      }
    });
    thread.start();
    thread.join();
    // 确保拿到result 并输出
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
