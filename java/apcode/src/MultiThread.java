import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MultiThread {
	public static void borningYou() throws Exception
	{
		Scanner in=new Scanner(System.in);
		Runnable r=new Suprise();
		Thread t=new Thread(r);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
		
		Thread t2=new Suprise2();
		t2.start();
		
		Callable<Integer> t3=new Suprise3();
		System.out.println("Suprise 3:"+t3.call());
		
		FutureTask<Integer> task=new FutureTask<>(t3);
		Thread ft1=new Thread(task);
		ft1.start();
		
		System.out.println("Future task:"+task.get());
		
		//thread pool:Excutor
		ExecutorService pool=Executors.newCachedThreadPool();
		Future<Integer> tp=pool.submit(t3);
		System.out.println("Thread pool:"+tp.get());
		
		Future<String> tp2=pool.submit(new Callable<String>(){
			@Override
			public String call() throws Exception{
				return "temp callable for thread pool.";
			}
		});
		
		pool.shutdown();
		System.out.println("Thread pool:"+tp2.get());
		
		
		System.exit(0);
		
		for(int i=1; i<=10; i++){
			System.out.println("Suprise thread:"+t.getId()+"-"+t.getState());
			System.out.println(in.nextLine());
			System.out.println("You need to do it "+(10-i)+" times still.");
		}
		in.close();
	}
	
	public static void excTest() throws InterruptedException, ExecutionException
	{
		final String s[]={"Wendy","Alice","Bob","Jim"};

		List<Callable<String>> tasks=new ArrayList<Callable<String>>();
		tasks.add(new Callable<String>(){public String call(){
				return s[0]+",it's you!";
			}});
		tasks.add(new Callable<String>(){public String call(){
			return s[1]+",it's you!";
		}});
		tasks.add(new Callable<String>(){public String call(){
			return s[2]+",it's you!";
		}});
		tasks.add(new Callable<String>(){public String call(){
			return s[3]+",it's you!";
		}});
		
		//ScheduledExecutorService executor=Executors.newScheduledThreadPool(3);
		//ScheduledFuture<String> sfc=executor.schedule(call, 5000, TimeUnit.MILLISECONDS);
		//System.out.println(sfc.get());
		
		ExecutorService executor=Executors.newFixedThreadPool(3);
		List<Future<String>> results=executor.invokeAll(tasks);
		for(Future<String> result:results){
			System.out.println(result.get());
		}
		
		String any=executor.invokeAny(tasks);
		System.out.println("--------"+any);
	}
	
	public Map<String,Integer> forkJoin()
	{
		//count words
		Map<String,Integer> hmap=new HashMap<String,Integer>();
		return hmap;
	}
	
	public static void main(String[] args) throws Exception
	{
		//MultiThread.borningYou();
		MultiThread.excTest();
		System.exit( 0);
	}
}
class Suprise implements Runnable
{
	public void run()
	{
		for(int i=0; i<5; i++){
			System.out.println("Suprise you with an other borning thing!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class Suprise2 extends Thread
{
	public void run()
	{
		for(int i=0; i<5; i++){
			System.out.println("Suprise you with an other borning thing through another basic implementation!");
		}
	}
}
class Suprise3 implements Callable<Integer>
{
	public Integer call()
	{
		int i=3;
		System.out.println("You just have called this function which is named Suprise3.");
		//Thread.dumpStack();
		return i;
	}
}
