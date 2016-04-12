package algorithms;

public class StackTest {
	public static void main(String[] args){
		Stack<String> stack=new StackResizingArray<String>();
		stack.push("to");
		stack.push("be");
		stack.push("or");
		stack.push("not");
		stack.push("to");
		System.out.println(stack.pop());
		stack.push("be");
		System.out.println(stack.pop());
		stack.push("that");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push("is");
		System.out.println(stack);
	}
}
