package algorithms;

public class StackResizingArray<T> implements Stack<T>{
	private int top;
	private int capacity;
	private T[] items;
	
	public StackResizingArray(){
		top=0;
		capacity=1;//initialized to 1
		items=(T[])new Object[capacity];
	}
	@Override
	public T pop() {
		if(isEmpty())
			return null;
		T item=items[--top];
		items[top]=null;
		resize(items);
		return item;
	}

	@Override
	public void push(T item) {
		resize(items);
		items[top++]=item;
	}

	@Override
	public boolean isEmpty() {
		return top==0;
	}

	@Override
	public long size() {
		return capacity;
	}
	private void resize(T[] arrary){
		int oldcapacity=capacity;
//		System.out.println("top:"+top+"capacity:"+capacity);
		if(top>=capacity-1){
			//growing
			capacity=capacity*2;
		}
		else if(top<capacity/4){//记住这里的参数是4，如果是2的话，有可能占有率接近50%，这样刚缩减可能马上又要增长
			//reducing
			capacity=capacity/2;
		}
		
		if(oldcapacity!=capacity){
			System.out.println("resize");
			T[] newItems=(T[])new Object[capacity];
			for(int i=0; i<oldcapacity; i++){//这里复制数组，特别注意大小一定是小数组的，不然会发生溢出
				newItems[i]=items[i];
			}
			items=newItems;
		}
	}
	public String toString(){
		String str= "";
		for(int i=0; i<capacity; i++){
			str+=items[i]+",";
		}
		return str;
	}
}
