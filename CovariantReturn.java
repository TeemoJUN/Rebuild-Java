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






