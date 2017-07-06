package test_6;


/*
 * 公司类主要实现对账户余额的增加
 */

public class Company implements Runnable{
	private Account account;

	public Company(Account account){
		this.account=account;
	}
	
	
	@Override
	public void run() {
		for(int i=0;i<10;i++){
			account.addAmount(1000);
		}
	}
}
