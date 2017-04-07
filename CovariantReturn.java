package test_8;
/*
 * Э������
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
 * WheatMill �̳�Mill������process����
 * 
 * Grain process����
 * 
 * Wheat process����
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






