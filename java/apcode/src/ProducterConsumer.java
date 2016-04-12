import java.util.ArrayList;

public class ProducterConsumer {
	//Just for making a interesting toll. Should use BlockQueue instead in real work
	private final ArrayList<Task> queue;//can not be queue directly,because it is hard to know the last element when multi-producters get it simultaneously.
	public ProducterConsumer(){
		this.queue=new ArrayList<Task>();
	}
	
	public void product(Task task)
	{
		queue.add(task);
		System.out.println("=====Put a task on work queue=====");
	}
	public void consume()
	{
		while(queue.size()<=0){
			System.out.println("=====There is no work todo now=====");
		}
		//very poor design
		Task task=queue.remove(Task.reduceMaxId());
		System.out.println("=====consume task:No. "+task.getId()+",Message "+task.getMessage()+"=====");
	}
	
	public static void main(String []args)
	{
		ProducterConsumer pc=new ProducterConsumer();
		pc.product(new Task("p1"));
		pc.product(new Task("p2"));
		pc.consume();
		pc.consume();
	}
}
class Task{
	private static int MAX_ID=0;
	private int id;
	private String message;
	public Task(String message)
	{
		this.id=Task.MAX_ID++;
		this.message=message;
	}
	public String getMessage()
	{
		return message;
	}
	public int getId()
	{
		return id;
	}
	//It is not reasonable to use MAX_ID to record the last position of the outer queue
	public static int reduceMaxId()
	{
		return --MAX_ID;
	}
}
