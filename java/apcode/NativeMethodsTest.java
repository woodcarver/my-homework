 
public class NativeMethodsTest {
//	public static native void greeting();
	public static void main(String[] args){
		System.out.println("------Native Method Test--------");
		System.out.println(System.getProperty("java.library.path"));
		NativeMethods.greeting();
	}
	static
	{
		System.loadLibrary("NativeMethods");
	}
}
