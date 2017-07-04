# java.lang.System.arrayCopy()与java.util.Arrays.copyOf()

```java
public static native void arraycopy(Object src,int srcPos, Object dest, int destPos,int length);
```

 - src - 源数组。
 - srcPos - 源数组中的起始位置。
 - dest - 目标数组。
 - destPos - 目标数据中的起始位置。
 - length - 要复制的数组元素的数量。 
 
**该方法用了native关键字，调用底层代码实现的高效。**

---

```java
public static <T> T[] copyOf(T[] original, int newLength) {
        return (T[]) copyOf(original, newLength, original.getClass());
    }

    public static <T,U> T[] copyOf(U[] original, int newLength, Class<? extends T[]> newType) {
        T[] copy = ((Object)newType == (Object)Object[].class)
            ? (T[]) new Object[newLength]  // 类型相同，则重新生成一个大小为newLength的数组实例
            : (T[]) Array.newInstance(newType.getComponentType(), newLength);  // 类型不同，重新生成一个大小为newLength的新类型数组实例
        System.arraycopy(original, 0, copy, 0, Math.min(original.length, newLength)); //将原数组内容拷贝到新数组中,新数组取最小的数组长度
        return copy;  // 返回新数组的引用
    }

```

 1. original - 要复制的数组
 2. newLength - 要返回的副本的长度
 3. newType - 要返回的副本的类

**arraycopy 方法中，如果新数组比原数组的length小则会抛出java.lang.ArrayIndexOutOfBoundsException异常.**

Arrays.copyOf()本质也是用System.arrayCopy();





