package test_1;


/*
 * synchronized 
 * 
 * ����߳�A����ִ��һ��ͬ������syncMethodA(),�߳�BҪִ��������������ͬ������syncMethodB(),�߳�B���ᱻ����ֱ���߳�A�����ꡣ
 * ������߳�B���ʵ���ͬһ����Ĳ�ͬ������ô�Ͳ�������
 * 
 */


/*
 * �����˻��������Ӻͼ��ٵĹ���
 * */

public class Account {
	private double balance;
	
	//����balance
	public double getBalance(){
		return balance;
	}
	//��ȡbalance
	public void setBalance(double balance){
		this.balance=balance;
	}
	
	//����balance
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
	
	//����balance
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
 * Bankר�Ÿ�account����balance;
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
 * Companyר�Ÿ�account����balance
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





















