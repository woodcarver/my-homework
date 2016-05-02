package algorithms;

import java.util.Stack;

public class Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
       // 这个方法貌似解决了queue在数组实现的时候头部浪费的情况呀~
       // 需要考虑容量的问题吗？比如queue是full？
       // 如何处理这些异常情况？？java编程风格
       // java 的stack结构怎么判断栈满的呢？
       stack1 = new Stack<Integer>();
       stack2 = new Stack<Integer>();
    }
    
    public void push(int element) {
        stack1.push(element);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            dumpStack();
        }
        return stack2.pop();
    }
    private void dumpStack() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    public int top() {
        if (stack2.isEmpty()) {
            dumpStack();
        }
        return stack2.peek();
    }
}
