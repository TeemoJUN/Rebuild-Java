package test_6;

public class Main {
	public static void main(String[] args){
		Account account=new Account();//创建账户
		account.setBalance(1000);//设置账户初始值
		
		Company company=new Company(account);//创建公司
		
		Bank bank=new Bank(account);//创建银行
		
		Thread bankThread=new Thread(bank);
		
		Thread companyThread=new Thread(company);
		
		
		System.out.printf("Account : Initial Balance : %d\n",account.getBalance());
		
		bankThread.start();
		
		companyThread.start();
		
		try{
			bankThread.join();//等待bankThread执行完
			companyThread.join();//等待companyThread执行完
			System.out.printf("Account : final Balance : %d\n",account.getBalance());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
