# 泛型

+ 因为fruit本质还是一个Apple数组
```
Fruit[] fruit=new Apple[10];

fruit[0]=new Apple();//OK
fruit[1]=new Jonathan();//Jonathan为Apple的子类 OK


fruit[0]=new Fruit();/错误

```


----

+ 不能吧一个涉及Apple的泛型赋给一个涉及Fruit的泛型
```
List<Fruit> flist=new ArrayList<Apple>()//错误


```
----
+ flist是一个没有指定的Fruit导出类的确切类型，因此只能取不能存 extends

```java
List<? ectends Fruit> flist=new ArrayList<Apple>();

flist.add(new Apple());//错误
flist.add(new Fruit());//错误

flist.add(new Object());//错误

flist.add(null)//leagl but unintererting

Fruit f=flist.get(0);//可以

```
----
+ apples为Apple的某个父类的具体类，所以能调用add方法添加Apple的子类 super


```java

public class SuperTypeWildcards{
     static void writeTo(List<? super Apple> apples){
         apples.add(new Apple());
         apples.add(new Fruit()); //错误
     }

}

```

----

## 泛型中创建数组推荐 Array.newInstance(); 因为其中创建本质上还是Object类型的数组
## ArrayList<? extends Fruit> 中add()的参数是“？ extends Fruit” 从描述中可以知道“编译器并不能了解这里需要的Fruit的哪个具体类型的子类”
contains()和indexOf（）可以；因为参数是Object

----

## List实际上表示的“持有任何Object类型的原生List”，而List<？>表示“具有某种特定类型的非原生List，只是我们不知道那种类型是什么”

```java
public clss Holder<T>{
     //.............
}
```
+ 原生Holder将持有任何类型的组合，而Holder<?> 将持有某种具体类型的同构集合，因此不能只向其中传递Object

## <?> 可以被认为是一种装饰，但是它仍旧是很有价值的，因为，实际上，它是声明：“我想用java的泛型类型来编写这段代码，我在这里并不是要用原生类型，但是在当前情况下，泛型参数可以持有任何类型。”







