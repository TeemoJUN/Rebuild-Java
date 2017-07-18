
# 注意理解

```java
class A{
	void draw(){
		System.out.println("A.draw()");
	}
	A(){
		System.out.println("A.before");
		draw();
		System.out.println("A.after");
	}
}


class B extends A{
	 void draw(){
		System.out.println("B.draw()");
	}
	B(){
		System.out.println("B.before");
		draw();
		System.out.println("B.after");
	}
}


public class Test{
	public static void main(String[] args){
		new B();
	}
}

/*
A.before
B.draw()//注意
A.after
A.before
B.draw()
A.after
*/
```




```java
class A{
	private void draw(){
		System.out.println("A.draw()");
	}
	A(){
		System.out.println("A.before");
		draw();
		System.out.println("A.after");
	}
}


class B extends A{
	 void draw(){
		System.out.println("B.draw()");
	}
	B(){
		System.out.println("B.before");
		draw();
		System.out.println("B.after");
	}
}


public class Test{
	public static void main(String[] args){
		new B();
	}
}

/*
A.before
A.draw()//注意这个调用啦A.draw.因为私有的方法不存在覆盖
A.after
B.before
B.draw()
B.after
*/
```


```java
class A{
	int a=4;
	void draw(){
		System.out.println("A.draw()");
	}
	A(){
		System.out.println("A.before");
		draw();
		System.out.println(a);
		System.out.println("A.after");
	}
}


class B extends A{
	private int a=5;
	 public void draw(){
		System.out.println("B.draw()");
	}
	B(){
		System.out.println("B.before");
		draw();
		System.out.println(a);
		System.out.println("B.after");
	}
}


public class Test{
	public static void main(String[] args){
		new B();
	}
}
/*
 域是属于类的不存在覆盖
A.before
B.draw()
4
A.after
B.before
B.draw()
5
B.after 
*/
```


---

## 可变参数

```java
public class Test{
	static void f(float f,Character... args){
		System.out.println("first");
	}
	static void f(Character... args){
		System.out.println("second");
	}
	
	public static void main(String[] args){
		f(1,'a');
		Character[] a={'a','c'};
		f(a);
		//f('a','b','c');报错
	}
}

/*
因为字符‘a’可以被转型为float，所以f(float f,Character... args)与void f(Character... args)都可以
*/


```




```java

public class Test {
	public static void main(String args[]) {
		int x,y;
		x=5>>2;
		y=x>>>2;
		System.out.println(y);//5 >> 2 相当于 5除于2的平方，等于1 ，>>> 表示无符号 右移，高位用0 填充，0001 右移两位 0000，所以答案选 A 
		
	}
}

```

## 改变字符串的前后两个位置的字符
```java
package test_18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

public class UsingBuffers {
	private static void symmetricScramble(CharBuffer buffer){
		while(buffer.hasRemaining()){
			buffer.mark();
			char c1=buffer.get();
			char c2=buffer.get();
			buffer.reset();
			buffer.put(c2).put(c1);
		}
	}
	
	public static void main(String[] args){
		char[] data="UsingBuffers".toCharArray();
		
		ByteBuffer bb=ByteBuffer.allocate(data.length*2);
		
		CharBuffer cb=bb.asCharBuffer();
		
		cb.put(data);
		
		System.out.println(cb.rewind());//UsingBuffers
		
		symmetricScramble(cb);
		
		System.out.println(cb.rewind());//sUniBgfuefsr
		
		symmetricScramble(cb);
		
		System.out.println(cb.rewind());//UsingBuffers
		
	}
	
	
}

```













