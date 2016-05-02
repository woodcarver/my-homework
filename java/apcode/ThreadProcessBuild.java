import java.io.IOException;
import java.util.Scanner;

public class ThreadProcessBuild {
	public static void testProcess() throws IOException {
//		ProcessBuilder pb = new ProcessBuilder("/bin/bash","/Users/xiedandan/Documents/workspace/Homework/bin/test.sh");
//		Process process = pb.start();
//		Scanner scanner = new Scanner(process.getInputStream());
		
        String cmd = "/bin/bash /Users/xiedandan/Documents/workspace/Homework/bin/test.sh";
        Process process = Runtime.getRuntime().exec(cmd);
        Scanner scanner = new Scanner(process.getInputStream());
		
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
	public static void testThread() {
		//使用匿名类
		final int num = 2;// 这里为什么一定需要是final？？？
		Thread thread = new Thread(){
			 public void run() {
			        System.out.println("主动创建"+num+"的线程");
			    }
		};
		thread.start();
		
		Runnable runnable = new Runnable() {
			 public void run() {
			        System.out.println("主动创建runnable的线程");
			    }
		};
	}
	public static void main(String[] args) throws IOException  {
		ThreadProcessBuild.testProcess();
		ThreadProcessBuild.testThread();
	}
}
