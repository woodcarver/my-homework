public class SimpleLanguage {
	public static void main(String[] args)
	{
		int val;
		val=new Reduce(new Add(new Item(100),new Item(5)),new Item(5)).value();
		System.out.println(val);
	}
}

class Add extends Expr
{
	private Expr expr1;
	private Expr expr2;
	private Item val;

	public Add(Expr expr1,Expr expr2)
	{
		this.expr1=expr1;
		this.expr2=expr2;
		this.val=null;
	}
	public Expr eval()
	{
		if(expr1.isReducable())
			expr1=expr1.eval();
		if(expr2.isReducable())
			expr2=expr2.eval();
		return add(expr1.value(),expr2.value());
	}
	public int value()
	{
		if(val == null)
			val=new Item(eval().value());
		return val.value();
	}
	@Override
	public boolean isReducable() {
		return false;
	}
}
class Reduce extends Expr
{
	private Expr expr1;
	private Expr expr2;
	private Item val;

	public Reduce(Expr expr1,Expr expr2)
	{
		this.expr1=expr1;
		this.expr2=expr2;
		this.val=null;
	}
	public Expr eval()
	{
		if(expr1.isReducable())
			expr1=expr1.eval();
		if(expr2.isReducable())
			expr2=expr2.eval();
		return reduce(expr1.value(),expr2.value());
	}
	public int value()
	{
		if(val == null)
			val=new Item(eval().value());
		return val.value();
	}
	public boolean isReducable()
	{
		return true;
	}
}
class Item extends Expr
{
	private int val;
	public Item(int val)
	{
		this.val=val;
	}
	public static boolean isItem(int i)
	{
		if(i>=0 || i<=9)
			return true;
		return false;
	}
	public Expr eval()
	{
		return this;
	}
	public int value()
	{
		return val;
	}
	public boolean isReducable()
	{
		return false;
	}
}
abstract class Expr
{
	public abstract Expr eval();
	public abstract int value();
	public abstract boolean isReducable();
	public Item reduce(int left,int right){
		return new Item(left-right);
	}
	public Item add(int left,int right){
		return new Item(left+right);
	}
}

//class Prediction
//{
//	static boolean isReducable(Object obj) throws Exception
//	{
//		if(obj instanceof Add)
//			return true;
//		else if(obj instanceof Reduce)
//			return true;
//		else if(Item.isItem((int)obj))
//			return false;
//		else
//			throw new Exception("Can not recongnize this expression.");
//	}
//}