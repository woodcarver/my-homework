import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Synchronization {
	public void bankTest()
	{
		final Bank bank=new Bank(200);
		//how to make those threads run in random order?
		//The answer using countdownLatch
		final CountDownLatch latch=new CountDownLatch(1);
		final Lock lock=new ReentrantLock();
		
		class Drawback extends Thread{
			//private Bank bank; //can throw this sentence,because this is an inner class
			private int amount;
//			public Drawback(Bank bank,int amount)
//			{
//				this.bank=bank;
//				this.amount=amount;
//			}
			public Drawback(int amount)
			{
				this.amount=amount;
			}
			public void run(){
				lock.lock();
				try{
					System.out.println("Drawback "+amount+" -- The balance:"+bank.drawback(amount));
					//latch.countDown();
					latch.await();
				}catch(Exception e){
					System.out.println(e.getMessage());
				}finally{
					lock.unlock();
				}
			}
		};
		class Deposit extends Thread{
			private int amount;
			public Deposit(int amount)
			{
				this.amount=amount;
			}
			
			public void run(){
				try {
					lock.lock();
					System.out.println("Deposit "+amount+" -- The balance:"+bank.deposite(amount));
					//latch.countDown();
					latch.await();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}finally{
					lock.unlock();
				}
			}
		}
		
		//start to interface our precious customs.	
		Thread designDrawback=new Drawback(150);
		Thread CEODrawback=new Drawback(200);
		designDrawback.start();
		CEODrawback.start();
		new Drawback(10).start();
		new Drawback(20).start();
		new Drawback(30).start();
		new Deposit(30).start();
		new Deposit(500).start();
	
		//open the gate
		latch.countDown();
	}
	public void bankWithLockTest()
	{
		final BankWithLock bank=new BankWithLock(200);
		class Drawback extends Thread
		{
			private int amount;
			public Drawback(int amount)
			{
				this.amount=amount;
			}
			public void run()
			{
				System.out.println("Drawback "+amount+" -- The balance:"+bank.drawbackWait(amount));
			}
		}
		class Deposite extends Thread
		{
			private int amount;
			public Deposite(int amount)
			{
				this.amount=amount;
			}
			public void run()
			{
				System.out.println("Deposit "+amount+" -- The balance:"+bank.deposite(amount));
			}
		}
		
		new Drawback(300).start();
		new Drawback(10).start();
		new Drawback(20).start();
		new Drawback(30).start();
		new Deposite(50).start();
		new Deposite(500).start();
	}
	public static void main(String[] args)
	{
		Synchronization test=new Synchronization();
		//test.bankTest();
		test.bankWithLockTest();
	}
}
class Bank
{
	private int balance;
	public Bank(int balance)
	{
		this.balance=balance;
		//System.out.println("Init balance "+balance);//Why after adding this sentence, countDownLatch will not be available?
	}
	public int deposite(int amount)
	{
		balance+=amount;
		return balance;
	}
	public int drawback(int amount) throws Exception
	{
		if(balance>=amount)
			balance-=amount;
		else
			throw new Exception("The balance is "+balance+" now. But you want drawback "+amount+".You don't have enough money, Please depositie or drawback less.");
		return balance;
	}
	public int drawbackWait(int amount) throws Exception
	{
		if(balance>=amount)
			balance-=amount;
		else
			throw new Exception("The balance is "+balance+" now. But you want drawback "+amount+".You don't have enough money, Please depositie or drawback less.");
		return balance;
	}
}
class BankWithLock
{
	private int balance;
	private Lock bankLock;
	private Condition sufficientFunds;
	public BankWithLock(int balance)
	{
		this.balance=balance;
		bankLock=new ReentrantLock();
		sufficientFunds=bankLock.newCondition();
	}

	public int deposite(int amount)
	{	
		try{
			bankLock.lock();
			balance+=amount;
		}finally{
			bankLock.unlock();
		}
		return balance;
	}

	public int drawbackWait(int amount)
	{
		try{
			System.out.println("draw--"+amount);
			bankLock.lock();
			while(balance<amount)
				sufficientFunds.await();
			//System.out.println("draw--"+Thread.currentThread());
			balance-=amount;
			
			sufficientFunds.signalAll();
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			bankLock.unlock();
		}
		return balance;
	}
}
class TrainTicket
{
	private int ticket_count;
	public boolean buy()
	{
		if(ticket_count<=0)
			return false;
		
		ticket_count--;
		return true;
	}
	public int getCount(){
		return ticket_count;
	}
}