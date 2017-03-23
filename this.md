# this&interfase&CovariantReturn

---
## this

+ 尽管可以用this调用this调用构造器，但却不能调用两个，却必须将构造器调用位置放在最起始处。

## interface

+ Java通过interface来实现多重继承。
+ 还有就是面向接口编程
+ 接口中的常量自动static&final

## CovariantReturn

+ 导出类中的被覆盖方法可以返回基类方法的返回类型的某种导出类型

```java
package test_8;
/*
 * 协变类型
 *
 * */

class Grain{
	public String toString(){
		return "Grain";
	}
}

class Wheat extends Grain{
	public String toString(){
		return "Wheat";
	}
}
/*
 * WheatMill 继承Mill覆盖了process方法
 * 
 * Grain process（）
 * 
 * Wheat process（）
 * 
 * */

class Mill{
	Grain process(){
		return new Grain();
	}
}

class WheatMill extends Mill{
	Wheat process(){
		return new Wheat();
	}
}

public class CovariantReturn {
	public static void main(String[] args){
		Mill m=new Mill();
		Grain g=m.process();
		System.out.println(g);
		
		m=new WheatMill();
		g=m.process();
		
		System.out.println(g);
	}
}
/*
 * Grain
 * Wheat
 * */

```




