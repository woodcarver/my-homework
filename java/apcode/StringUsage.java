
public class StringUsage {
	public static void main(String[] args)
	{
		String str="抽风了";
		for(int i=0;i<str.length();i++){
			System.out.println(i+":"+str.charAt(i));
			System.out.println(i+":"+str.codePointAt(i));
		}
		
		int index=str.offsetByCodePoints(0, 1);
		System.out.println(index);
		System.out.println(str.codePointAt(index));
	}
}
