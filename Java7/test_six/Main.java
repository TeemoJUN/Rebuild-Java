package test_6;

public class Main {
	public static void main(String[] args){
		Account account=new Account();//�����˻�
		account.setBalance(1000);//�����˻���ʼֵ
		
		Company company=new Company(account);//������˾
		
		Bank bank=new Bank(account);//��������
		
		Thread bankThread=new Thread(bank);
		
		Thread companyThread=new Thread(company);
		
		
		System.out.printf("Account : Initial Balance : %d\n",account.getBalance());
		
		bankThread.start();
		
		companyThread.start();
		
		try{
			bankThread.join();//�ȴ�bankThreadִ����
			companyThread.join();//�ȴ�companyThreadִ����
			System.out.printf("Account : final Balance : %d\n",account.getBalance());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
	}
}
