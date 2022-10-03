package swordfinger_offer.queue_stack.包含min函数的栈;

import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Solution {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> min = new Stack<>();

    public void push(int node) {
        Lock l = new ReentrantLock();
        l.lock();
        try{
            stack.push(node);
            if(min.size() == 0){
                min.push(node);
                return;
            }
            if(node <= min.peek()){
                min.push(node);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
    }

    public void pop() {
        Lock l = new ReentrantLock();
        l.lock();
        try{
            int popVal = stack.pop();
            if( min.size() > 0 && min.peek() == popVal){
                min.pop();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
    }

    public int top() {
        int ret = 0;
        Lock l = new ReentrantLock();
        l.lock();
        try{
            ret = stack.peek();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
        return ret;
    }

    public int min() {
        int ret = 0;
        Lock l = new ReentrantLock();
        l.lock();
        try{
            ret = min.peek();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            l.unlock();
        }
        return ret;
    }
}