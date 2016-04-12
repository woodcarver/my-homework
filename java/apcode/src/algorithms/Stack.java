package algorithms;

public interface Stack <T> {
	public T pop();
	public void push(T item);
	public boolean isEmpty();
	// int or long?
	public long size();
}
