import java.util.*;
import java.io.*;

class Asm1 {

    // method reverse queue
    public Queue<Integer> reverseQueue(Queue<Integer> queue) {
        // reverse queue, O(n) in worst case
        Stack<Integer> stack = new Stack<Integer>();
        // add queue elements respectively to stack, so that we can look from bottom to top of stack is the original queue
        while(!queue.isEmpty()) {
            stack.add(queue.element());
            queue.remove();
        }
        // add stack elements respectively to queue, here we will get the reversed queue
        while(!stack.isEmpty()) {
            queue.add(stack.peek());
            stack.pop();
        }

        return queue;
    }
    
    public static void main(String[] args) {
        // example
        Asm1 asm1 = new Asm1();
        Queue<Integer> originalQueue = new LinkedList<Integer>(); 

        for (int i = 0; i <= 10; i++) {
            originalQueue.add(i);
        }
        System.out.println("Original queue: " + originalQueue);
        Queue<Integer> reversedQueue = asm1.reverseQueue(originalQueue);
        System.out.println("Reversed queue: " + reversedQueue);
    }
}

class Asm2 {
    public boolean checkDuplicate(Stack<Integer> stack1, Stack<Integer> stack2) {
        // return TRUE if two stacks are same, O(n) in worst case
        boolean isDuplicate = true;
        // Queues to rebuild stacks
        Queue<Integer> queue1 = new LinkedList<Integer>();
        Queue<Integer> queue2 = new LinkedList<Integer>();

        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            // get first element of both queues
            int stack1FirstElement = stack1.peek();
            int stack2FirstElement = stack2.peek();

            if(stack1FirstElement != stack2FirstElement) isDuplicate = false;

            if(!stack1.isEmpty()) {
                queue1.add(stack1FirstElement);
                stack1.pop();
            }
            if(!stack2.isEmpty()) {
                queue2.add(stack2FirstElement);
                stack2.pop();
            }
        }
        // rebuild stacks
        // Reverse queues to rebuild stacks to the original order
        Asm1 asm1 = new Asm1();
        queue1 = asm1.reverseQueue(queue1);
        queue2 = asm1.reverseQueue(queue2);

        while(!queue1.isEmpty()) {
            stack1.push(queue1.peek()); 
            queue1.remove();
        }
        while(!queue2.isEmpty()) {
            stack2.push(queue2.peek());
            queue2.remove();
        }

        return isDuplicate;
    }

    public static void main(String[] args) {
        // example
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        Asm2 asm2 = new Asm2();

        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);

        stack2.push(1);
        stack2.push(2);
        stack2.push(100);
        stack2.push(4);
        stack2.push(5);
        
        System.out.println("Stack 1 init: " + stack1);
        System.out.println("Stack 2 init: " + stack2);

        System.out.println(asm2.checkDuplicate(stack1, stack2));
        
        System.out.println("Stack 1 after: " + stack1);
        System.out.println("Stack 2 after: " + stack2);
    }
}