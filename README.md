# README

+ 虽然JAVASE看完了，但仔细一想，好像有很多似懂非懂的知识点，好像会，但仔细一想，却不会。
+ 因此重修JAVA
+ 着重看集合，IO，多线程
---
### 泛型例子-->来自java编程思想--P397
+ Holder.java 
+ CovariantArrays.java
+ Wildcards.java
---

## Java7文件是来自《Java 7 并发编程实战手册》的里面代码练习

---

## LintCOde文件夹是在[LintCode](http://www.lintcode.com/zh-cn/problem/ "LintCode")刷刷的题目，练练大脑。

---

看ArrayList.java源码是发现的有一个fast-fail;

|快速失败迭代器|安全失败迭代器|
|-------|------|
|在迭代时不允许修改集合|在迭代时允许修改集合|
|迭代时被修改抛出ConcurrentModificationException异常|迭代时集合被修改不抛出异常|
|使用原集合遍历集合元素|使用原集合的副本遍历集合元素|
|迭代器不要求额外的内存|迭代器需要额外的内存克隆集合对象|
|示例：ArrayList, Vector, HashMap|示例：ConcurrentHashMap|

---



## ExecutorService exec=Executors.newCachedThreadPool();

+ exec对象既有submit（）方法也有execute()方法
+ execute()方法无返回值
## 二者区别在于:

|返回值|方法名|解释|
|-----|------|----|
| void | execute(Runnable command)|在未来某个时间执行给定的命令。|
| \<T> Future \<T>|submit(Callable<T> task)| 提交一个返回值的任务用于执行，返回一个表示任务的未决结果的Future。 |
|Future<?>|submit(Runnable task)|提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。|
|\<T> Future\<T>|submit(Runnable task, T result)|提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。|

+ 在处理Future时需要处理异常



---


## 创建对象

+ 当实例化子类对象时，首先要加载父类的class文件进内存，静态代码块是随着类的创建而执行，所以父类静态代码块最先被执行，子类class文件再被加载，同理静态代码块被先执行；实例化子类对象要先调用父类的构造方法，而调用父类构造方法前会先执行父类的非静态代码块

---

## HashMap与Hashtable

+ HashMap不能保证元素的顺序,HashMap能够将键设为null，也可以将值设为null，与之对应的是Hashtable,(注意大小写：不是HashTable)，Hashtable不能将键和值设为null，否则运行时会报空指针异常错误；
HashMap线程不安全，Hashtable线程安全




