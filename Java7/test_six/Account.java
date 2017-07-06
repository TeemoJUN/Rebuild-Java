package test_6;

import java.util.concurrent.atomic.AtomicLong;


/*
 * 账户类
 * 用原子类型来操作
 */


public class Account {
	private AtomicLong balance;//把账户余额设置为原子类型
	
	public Account(){
		balance=new AtomicLong();
	}
	
	//获取余额
	public long getBalance(){
		return balance.get();
	}
	
	//设置余额
	public void setBalance(long balance){
		this.balance.set(balance);
	}
	//增加余额
	public void addAmount(long amount){
		this.balance.getAndAdd(amount);
	}
	
	//减余额
	public void subtractAmount(long amount){
		this.balance.getAndAdd(-amount);
	}


}
