import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.*;

public class CollectionUsage {
	public <T extends Comparable> T max(Collection<T> c)
	{
		if(c.isEmpty())
			throw new NoSuchElementException();
		Iterator<T> iter=c.iterator();
		T largest=iter.next();
		while(iter.hasNext())
		{
			T next=iter.next();
			if(largest.compareTo(next)<0)
				largest=next;
		}
		return largest;
	}
	public void main(String args)
	{
		CollectionUsage col=new CollectionUsage();
		Collection1<String> colimp=new Collection1<>();
		colimp.add((Object)"one");
		colimp.add((Object)"two");
		colimp.add((Object)"three");
		System.out.println(col.max(colimp));
		ArrayList arr=new ArrayList();
		LinkedList arr2=new LinkedList();
		ArrayDeque arr3=new ArrayDeque();
	}
}
//What's the different Collection1<T> and Collection<T>
class Collection1<T> implements Collection,Comparable
{
	private ArrayList<T> items;
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		T new_o=(T)o;
//		if(new_o.equals("one"))
//			return 0;
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		items.add((T)e);
		return true;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}