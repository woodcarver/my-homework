import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.AccessibleObject;
import java.util.Scanner;
import java.util.ArrayList;

public class Reflection {
	public void testEnum()
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Please enter size:(SMALL, MEDIUM, LARGE, EXTRA_LARGE)");
		String input=in.next().toUpperCase();
		in.close();
		Size size=Enum.valueOf(Size.class, input);
		System.out.println("size=" + size);
		System.out.println("abbreviation="+size.getAbbreviation());
		if(size==Size.EXTRA_LARGE){
			System.out.println("Good job:You chose extra-large!");
		}
	}
	public void testReflection()
	{
		String className="java.util.Date";
		try {
			Class cl=Class.forName(className);
			System.out.println("Test reflection: cl="+cl.newInstance());
			Class sc=cl.getSuperclass();
			if(sc!=null && sc!=Object.class){
				System.out.println(className+"is extented from "+sc.getName());
			}
			System.out.println("Modifier id:"+cl.getModifiers()+";Modifier string:"+Modifier.toString(cl.getModifiers()));
			printConstructors(cl);
			printMethods(cl);
			printFields(cl);
			System.out.println("-------");
			ArrayList<Integer> squares=new ArrayList<>();
			for(int i=1;i<=5;i++){
				squares.add(i*i);
			}
			System.out.println("squares:"+squares);
			System.out.println("squares:"+toString(squares));
			
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@SuppressWarnings("rawtypes")
	private void printConstructors(Class cl)
	{
		Constructor[] ccs=cl.getDeclaredConstructors();
		for(Constructor cc:ccs){
			System.out.print(Modifier.toString(cc.getModifiers())+" ");
			System.out.print(cl.getName()+"(");
			Class[] paramTypes=cc.getParameterTypes();
			for(Class p:paramTypes){
				System.out.print(p.getName()+",");
			}
			System.out.println(");");
		}
	}
	@SuppressWarnings("rawtypes")
	private void printMethods(Class cl)
	{
		Method[] mts=cl.getMethods();
		for(Method mt:mts){
			System.out.print(Modifier.toString(mt.getModifiers())+" ");
			System.out.print(mt.getName()+"(");
			Class[] paramTypes=mt.getParameterTypes();
			for(Class p:paramTypes){
				System.out.print(p.getName()+",");
			}
			System.out.println(");");
		}
	}
	@SuppressWarnings("rawtypes")
	private void printFields(Class cl)
	{
		Field[] fls=cl.getDeclaredFields();
		for(Field fl:fls){
			System.out.print(Modifier.toString(fl.getModifiers())+" ");
			System.out.print(fl.getType().getName()+" ");
			System.out.print(fl.getName());
			System.out.println(";");
		}
	}
	@SuppressWarnings("rawtypes")
	private String toString(Object obj)
	{
		Class cl=obj.getClass();
		String str=cl.getName();
		while(cl!=null){
			str+="[";
			Field[] fields=cl.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true);
			for(Field f:fields){
				if(!Modifier.isStatic(f.getModifiers())){
					if(!str.endsWith("[")){
						str+=",";
					}
					str+=f.getName()+"=";
					try{
						Object val=f.get(obj);
						str+=toString(val);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			str+="]";
			cl = cl.getSuperclass();
		}
		return str;
	}
	public String toString2(Object obj)
	{
		int a=1;
		do{
			System.out.println("int a:"+a);
		}
		while(a>10);
		return ((Integer)a).toString();
	}
	
	public static void main(String[] args)
	{
		Reflection re=new Reflection();
		//re.testEnum();
		re.testReflection();
	}
}
enum Size
{
	SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
	private String abbreviation;
	private Size(String abbreviation)
	{
		this.abbreviation=abbreviation;
	}
	public String getAbbreviation()
	{
		return abbreviation;
	}
}