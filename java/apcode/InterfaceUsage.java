import javax.swing.JOptionPane;

import java.io.EOFException;
import java.io.IOException;
import java.util.*;

public class InterfaceUsage {
	class InnerTimePrinter
	{	
	}
	
	public static void timer1() {
		System.out.println("-------start inner 1:--------");
	    Timer timer = new Timer();  
	    /* void java.util.Timer.schedule(TimerTask task, long delay) */  
	    timer.schedule(new TimerTask() {  
	        public void run() {   
	            System.out.println("-------任务执行--------");
	        }  
	    }, 2000);// delay=2000毫秒 后执行该任务  
	} 
	public static void timer2() {  
	    Calendar calendar = new GregorianCalendar();  
	    calendar.add(Calendar.MINUTE, 3);  
	    calendar.set(Calendar.SECOND, 0); // 一分钟后执行  
	    Timer timer = new Timer();
	    System.out.println("-------start inner "+calendar.getTime()+"--------");
	    /* void java.util.Timer.schedule(TimerTask task, Date time) */  
	    timer.schedule(new TimerTask() {  
	        @Override  
	        public void run() {  
	            System.out.println("-------任务执行--------");
	        }  
	    }, calendar.getTime()); 
	}
	public static int factorial(int n)
	{
		/*Throwable t=new Throwable();
		StackTraceElement[] frames=t.getStackTrace();
		for(StackTraceElement frame:frames){
			System.out.println(frame);
		}*/
		System.out.println(n);
		assert n>1:n;
		if(n==1)
			return n;
		else
			return n*factorial(n-1);
	}
	public static void main(String[] args) throws Exception
	{
		//TimerTask listener = new TimePrinter();
		//Timer t = new Timer();//failed to call actionPerformed function and I don't know why. change call java.util.Timer
		//InterfaceUsageA ib=new InterfaceUsageB();
		//ib.printInput();
		//ib.test();
		//t.schedule(listener, 100, 100);
		//InterfaceUsage.timer1();
		//System.out.println(InterfaceUsage.factorial(5));
		GenericTest<Integer,String> pair=new GenericTest<>(0,"Dandan Xie");//can not use fundamental types like int,boolean etc.
		int first=pair.getFirst()+1;
		System.out.println("The first id is:"+first);
		String second=pair.getSecond();
		System.out.println("His/Her name is:"+second);
		InterfaceUsageB ib=new InterfaceUsageB();
		ib.printSomething(first);
		ib.<String>printSomething(second);
		System.out.println("over");
		System.exit(0);
	}
}
class TimePrinter extends TimerTask
{
	@Override
	public void run() {
		Date now=new Date();
		System.out.println("time now is :"+now);
		JOptionPane.showMessageDialog(null, "Quit program?");
		//Toolkit.getDefaultToolkit().beep();
	}
}
class InterfaceUsageA{
	public void test() throws Exception
	{
		throw new IOException();
	}
	public void printInput() throws EOFException
	{
		Scanner in=new Scanner(System.in);
		String line;
		int n;
		while(in.hasNext()){
			//line=in.next();//How to copy a string in java
			System.out.println(in.next());
			throw new EOFException();
		}
		
		//read txt from a file
	}
}
class InterfaceUsageB extends InterfaceUsageA{
	@Override
	public void test() throws EOFException
	{
		//super.test();
		throw new EOFException();
	}
	public void printInput() throws EOFException
	{
		super.printInput();
	}
	public <T> T printSomething(T some)
	{
		T rs;
		if(some instanceof String){
			String r=(String)some;
			r=r+"(String)";
			rs=(T)r;
		}
		else if(some instanceof Integer){
			Integer r=(Integer)some;
			r=r+1;
			rs=(T)r;
		}else{
			rs=null;
		}
			
		System.out.println("Generic Method:"+rs);
		return rs;
	}
}
class GenericTest<T,U>
{
	private T first;
	private U second;
	public GenericTest()
	{
		first=null;
		second=null;
	}
	public GenericTest(T first,U second)
	{
		this.first=first;
		this.second=second;
	}
	T getFirst()
	{
		return first;
	}
	U getSecond()
	{
		return second;
	}
	void setFirst(T first)
	{
		this.first=first;
	}
	void setSecond(U second)
	{
		this.second=second;
	}
}

//end InterfaceUsage