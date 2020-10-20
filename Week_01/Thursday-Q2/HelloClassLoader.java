package week01;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * @program: java-training-camp week01
 * @description: 自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内 容是一个 Hello.class
 *     文件所有字节（x=255-x）处理后的文件。文件群里提供。
 * @author: xiaoboji
 * @create: 2020-10-20 23:49
 */
public class HelloClassLoader extends ClassLoader {
  public static void main(String[] args) {
    try {
      Class<?> aClass = new HelloClassLoader().findClass("Hello");
      Object obj = aClass.newInstance();
      Method method = aClass.getMethod("hello");
      method.invoke(obj);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected Class<?> findClass(String name) throws ClassNotFoundException {
    // 获取byte[]
    InputStream in = this.getResourceAsStream("Hello.xlass");
    byte[] bytes = new byte[16];
    try {
      bytes = new byte[in.available()];
      in.read(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    // 还原文件
    for (int i = 0; i < bytes.length; i++) {
      bytes[i] = (byte) (255 - bytes[i]);
    }
    return defineClass(name, bytes, 0, bytes.length);
  }
}
