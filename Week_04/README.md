# 作业
## W4-Sat-04

详见homework\W4-Sat-4

## W4-Thu-02
作业要求:（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这个方法的返回值后，退出主线程？写出你的方法，越多越好，提交到 Github。

思路:
StaticVarMethod:使用静态变量记录计算结果
CallableMethod:submit中使用Callable接口实例中返回结果，使用Future对象异步获取结果


1. StaticVarMethod
```java
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
```

2. CallableMethod
```java
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
```