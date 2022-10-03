package swordfinger_offer.queue_stack.用两个栈实现队列;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    // 此题之前的经典解法是入队列是O(1),出队列是O(N),即每次出队列的时候都要把stack1的元素全部移动到stack2，出队列之后再把stack2中的数据全部移动回stack1，但是要求出队列也是O(1)的话，就不可以这样了
    // 其实仔细一想，完全没有必要每次出队列都把数据全部移动过去再移动回来；我们把数据移动过去之后不需要移动回来，对于功能没有影响
    // 如此一来，只有在stack2为空的时候才去进行一次全量移动，此时也将stack1清空（有点渐进式复制的意思），均摊时间复杂度确实为O(1)

    // 也就是，常规时间复杂度不满足要求时可以考虑用均摊时间复杂度去考虑
    // 这种数据结构可以考虑做成线程安全的
    // 使用synchronized，最重量级，而且不可控不灵活，完全由操作系统的mutex来控制并发，直接在方法或者代码块使用synchronized
    //
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void pushLock(int node){
        Lock l = new ReentrantLock();
        l.lock();
        try{
            stack1.push(node);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
    }

    public int popLock() {
        Lock l = new ReentrantLock();
        l.lock();
        try{
            if(stack2.size() > 0){
                return stack2.pop();
            }
            while(stack1.size() > 0){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
        return 0;
    }

    public synchronized void pushSync1(int node){
        stack1.push(node);
    }

    public synchronized int popSync1() {
        if(stack2.size() > 0){
            return stack2.pop();
        }
        while(stack1.size() > 0){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public void pushSync2(int node){
        synchronized(stack1){
            stack1.push(node);
        }
    }

    public int popSync2() {
        synchronized(stack2){
            if(stack2.size() > 0){
                return stack2.pop();
            }
            while(stack1.size() > 0){
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }
    }
}