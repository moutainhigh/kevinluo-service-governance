package com.kevinluo.storage.framework.loader;

/*
 * Creates on 2019/12/12.
 */

import com.kevinluo.storage.framework.utils.reflect.ClassUtils;

import java.lang.reflect.Field;

/**
 * @author tiansheng
 */
public class CopyingClassLoader extends ClassLoader {

  public CopyingClassLoader() {
  }

  /**
   * 根据字节码来加载类
   */
  public Class<?> findClassByBytes(String name, byte[] classBytes) {
    return defineClass(name, classBytes, 0, classBytes.length);
  }

  /**
   * 复制对象的所有属性并返回一个新的对象（可以理解为对一个对象的深拷贝）
   *
   * @param target 类对象
   * @param src    target的实例对象

   * @return 新复制的对象
   */
  public Object getObject(Class<?> target, Object src) {
    try {
      Object instance = ClassUtils.newInstance(target);
      Field[] fields = src.getClass().getDeclaredFields();
      for (Field oldField : fields) {
        String fieldName = oldField.getName();
        oldField.setAccessible(true);
        Field newInstanceField = instance.getClass().getDeclaredField(fieldName);
        newInstanceField.setAccessible(true);
        newInstanceField.set(instance, oldField.get(src));
      }
      return instance;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
