package test_6;

import java.util.concurrent.atomic.AtomicLong;


/*
 * �˻���
 * ��ԭ������������
 */


public class Account {
	private AtomicLong balance;//���˻��������Ϊԭ������
	
	public Account(){
		balance=new AtomicLong();
	}
	
	//��ȡ���
	public long getBalance(){
		return balance.get();
	}
	
	//�������
	public void setBalance(long balance){
		this.balance.set(balance);
	}
	//�������
	public void addAmount(long amount){
		this.balance.getAndAdd(amount);
	}
	
	//�����
	public void subtractAmount(long amount){
		this.balance.getAndAdd(-amount);
	}


}
