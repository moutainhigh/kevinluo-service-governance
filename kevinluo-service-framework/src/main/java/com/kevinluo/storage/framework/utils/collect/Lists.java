package com.kevinluo.storage.framework.utils.collect;

import com.kevinluo.storage.framework.json.FastJsonHelper;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * 静态的List工具类
 *
 * @author wyz
 * @since 1.8
 */
public final class Lists {

  private Lists() {
  }

  /**
   * 升序，从大到小
   */
  public static final int ASC = 0;

  /**
   * 降序，从大到小
   */
  public static final int DESC = 1;

//  /**
//   * 创建元素列表不可变的List, 元素列表已经是固定了的, 是不可变的。
//   * 如果我们已经初始化了2个元素，那么我们再添加一个元素就会抛出一个叫做
//   * {@link UnsupportedOperationException}的异常。
//   *
//   * @param elements List元素
//   * @param <E> 元素类型
//   *
//   * @return 返回一个列表不可变的List
//   */
//  public static <E> List<E> newImmutableList(E... elements){
//    return List.of(elements);
//  }
//
//  /**
//   * 复制一个List中的元素创建出一个列表不可变的List
//   */
//  public static <E> List<E> newImmutableList(List<E> collections){
//    return List.copyOf(collections);
//  }

  /**
   * 创建一个可变的 {@code ArrayList}
   *
   * @return 空的 {@code ArrayList} 实例
   */
  public static <E> ArrayList<E> newArrayList() {
    return new ArrayList<>();
  }

  /**
   * 创建一个可变的 {@code ArrayList} 并初始化容量
   *
   * @return 空的 {@code ArrayList} 实例
   */
  public static <E> ArrayList<E> newArrayList(int capacity) {
    return new ArrayList<>(capacity);
  }

  /**
   * 创建一个可变的 {@code ArrayList}
   * 复制一个{@link List}中的数据到新的{@code List}中
   *
   * @return 新的List且带有传入List数据的实例
   */
  public static <E> ArrayList<E> newArrayList(Collection<? extends E> collection) {
    return new ArrayList<>(collection);
  }

  /**
   * 创建一个可变的 {@code ArrayList}
   * 复制数组中的内容到新创建的{@code ArrayList}中
   *
   * 为了避免堆污染，传入的参数应该是同一类型的。
   *
   * @return 新的List且带有传入List数据的实例
   */
  @SafeVarargs
  public static <E> ArrayList<E> newArrayList(E... e) {
    return new ArrayList<>(ofList(e));
  }

  /**
   * 创建一个可变的 {@code LinkedList}
   *
   * @return 空的 {@code LinkedList} 实例
   */
  public static <E> LinkedList<E> newLinkedList() {
    return new LinkedList<>();
  }

  /**
   * 创建一个可变的 {@code LinkedList}
   * 复制一个{@link List}中的数据到新的{@code LinkedList}中
   *
   * @return 新的List且带有传入List数据的实例
   */
  public static <E> LinkedList<E> newLinkedList(Collection<? extends E> collection) {
    return new LinkedList<>(collection);
  }

  /**
   * 创建一个可变的 {@code LinkedList}
   * 复制数组中的内容到新创建的{@code LinkedList}中
   *
   * @return 新的{@code List}且带有传入数组的数据的实例
   */
  public static <E> LinkedList<E> newLinkedList(E[] es) {
    return new LinkedList<>(ofList(es));
  }

  /**
   * 创建一个可变的 {@code Vector}
   *
   * @return 空的 {@code Vector} 实例.
   */
  public static <E> Vector<E> newVector() {
    return new Vector<>();
  }

  /**
   * 创建一个可变的 {@code Vector} 并初始化容量
   *
   * @return 空的 {@code Vector} 实例.
   */
  public static <E> Vector<E> newVector(int capacity) {
    return new Vector<>(capacity);
  }

  /**
   * 创建一个可变的 {@code Vector}
   * 复制一个{@link Vector}中的数据到新的{@code Vector}中
   *
   * @return 新的{@code Vector}且带有传入{@code Vector}数据的实例
   */
  public static <E> Vector<E> newVector(Collection<? extends E> collection) {
    return new Vector<>(collection);
  }

  /**
   * 创建一个可变的 {@code Vector}
   * 复制一个数组中的数据到新的{@code Vector}中
   *
   * @return 新的{@code Vector}且带有传入的数组数据的实例
   */
  public static <E> Vector<E> newVector(E[] es) {
    return new Vector<>(ofList(es));
  }

  /**
   * 对{@code List}进行简单的排序
   *
   * @param collection 集合实例
   * @param type       根据哪种类型进行排序，有以下几种
   *                   {@link Lists#ASC} ASC为升序
   * @link DESC} DESC则为降序
   * <p>
   * 如果List中的元素是对象的话，如果要根据对象的某个值进行排序的话请实现{@link Comparable}接口。例如：<code>
   * // 假设需求是需要对年龄进行降序，可以这样使用
   * List<LocalUser> users = Lists.newArrayList();
   * // 省略查询users代码...
   * users.sort((e1,e2) -> e2.getAge().compareTo(e1.getAge()));
   * </code>
   */
  public static <E extends Comparable<E>> void sort(List<E> collection, int type) {
    switch (type) {
      case ASC:
        Collections.sort(collection);
        break;
      case DESC:
        Collections.reverse(collection);
        break;
      default:
    }
  }

  /**
   * 数组转List
   *
   * @param e 元素列表
   * @return List对象
   */
  @SafeVarargs
  public static <E> List<E> ofList(E... e) {
    return new CopyArrayList<>(e);
  }

  /**
   * 自定义数组
   */
  private static class CopyArrayList<E> extends AbstractList<E>
          implements RandomAccess, java.io.Serializable {

    private final E[] a;

    CopyArrayList(E[] array) {
      a = Objects.requireNonNull(array);
    }

    @Override
    public int size() {
      return a.length;
    }

    @Override
    public Object[] toArray() {
      return a.clone();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
      int size = size();
      if (a.length < size)
        return java.util.Arrays.copyOf(this.a, size,
                (Class<? extends T[]>) a.getClass());
      System.arraycopy(this.a, 0, a, 0, size);
      if (a.length > size)
        a[size] = null;
      return a;
    }

    @Override
    public E get(int index) {
      return a[index];
    }

    @Override
    public E set(int index, E element) {
      E oldValue = a[index];
      a[index] = element;
      return oldValue;
    }

    @Override
    public int indexOf(Object o) {
      E[] a = this.a;
      if (o == null) {
        for (int i = 0; i < a.length; i++)
          if (a[i] == null)
            return i;
      } else {
        for (int i = 0; i < a.length; i++)
          if (o.equals(a[i]))
            return i;
      }
      return -1;
    }

    @Override
    public boolean contains(Object o) {
      return indexOf(o) != -1;
    }

    @Override
    public Spliterator<E> spliterator() {
      return Spliterators.spliterator(a, Spliterator.ORDERED);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
      Objects.requireNonNull(action);
      for (E e : a) {
        action.accept(e);
      }
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
      Objects.requireNonNull(operator);
      E[] a = this.a;
      for (int i = 0; i < a.length; i++) {
        a[i] = operator.apply(a[i]);
      }
    }

    @Override
    public void sort(Comparator<? super E> c) {
      java.util.Arrays.sort(a, c);
    }

  }

  /**
   * 转JSONString对象
   *
   * @param list 集合对象
   * @return JSON字符串
   */
  public static String toJSONString(List<?> list) {
    return FastJsonHelper.toJSONString(list);
  }

}
