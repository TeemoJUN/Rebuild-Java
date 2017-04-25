package test_1;


/*
 * synchronized 
 * 
 * 如果线程A正在执行一个同步方法syncMethodA(),线程B要执行这个对象的其它同步方法syncMethodB(),线程B将会被阻塞直到线程A访问完。
 * 但如果线程B访问的是同一个类的不同对象，那么就不会阻塞
 * 
 */


/*
 * 设置账户，有增加和减少的功能
 * */

public class Account {
	private double balance;
	
	//设置balance
	public double getBalance(){
		return balance;
	}
	//获取balance
	public void setBalance(double balance){
		this.balance=balance;
	}
	
	//增加balance
	public synchronized void addAmount(double amount){
		double temp=balance;
		
		try{
			Thread.sleep(10);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		temp+=amount;
		balance=temp;
	}
	
	//减少balance
	public synchronized void subtractAmount(double amount){
		double temp=balance;
		try{
			Thread.sleep(10);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}		
		temp-=amount;
		balance=temp;
	}
	
	
	public static void main(String[] args){
		Account account=new Account();
		account.setBalance(1000);
		
		Company company=new Company(account);
		
		Thread companyThread=new Thread(company);
		
		Bank bank=new Bank(account);
		
		Thread bankThread=new Thread(bank);
		
		System.out.printf("Account : Initial Balance: %f\n", account.getBalance());
		
		companyThread.start();
		bankThread.start();
		
		try{
			companyThread.join();
			bankThread.join();
			
			System.out.printf("Account : finial Balance: %f\n",account.getBalance());
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
	
	
	
}


/*
 * Bank专门给account减少balance;
 */

class Bank implements Runnable{

	private Account account;
	
	public Bank(Account account){
		this.account=account;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.subtractAmount(1000);
		}
	}
	
}



/*
 * Company专门给account增加balance
 */

class Company implements Runnable{
	
	private Account account;
	
	public Company(Account account){
		this.account=account;
	}
	
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			account.addAmount(1000);
		}
	}
	
}





















