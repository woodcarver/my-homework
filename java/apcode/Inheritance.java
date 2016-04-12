import com.sun.org.apache.xalan.internal.utils.Objects;


public class Inheritance {
	public boolean equals(Object otherObject){
		if(this==otherObject){
			return true;
		}
		if(otherObject==null){
			return false;
		}
		if(getClass()!=otherObject.getClass()){
			return false;
		}
			
		return true;
	}
	public int hashCode()
	{
		return super.hashCode();
	}
	public static void main(String[] args)
	{
		Object o1=new Object();
		Object o2=null;
		System.out.println(o1.equals(o2)+":"+Objects.equals(o1, o2));
		
		//
		Inheritance in=new Inheritance();
		System.out.println(in.equals(o1));
		System.out.println(in.hashCode());
		System.out.println(o1.hashCode());
	}
}
