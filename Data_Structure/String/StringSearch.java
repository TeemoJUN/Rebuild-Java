package five;

/**
 * 
 * 
 * ���ַ���������ʾ���ˣ��ȽϾ���
 * @author lin
 *
 */
public class StringSearch {
	public static int serach(String pat,String txt){
		int M=pat.length();
		int N=txt.length();
		int i=0;
		int j=0;
		for(;i<N&&j<M;i++){
			if(pat.charAt(j)==txt.charAt(i)){
				j++;
			}
			else{
				i=i-j;
				j=0;
			}
		}
		if(j==M){
			return i-M;
		}
		else{
			return N;
		}
	}
}
