package Stacks;
/* 
 * I Implemented Stacks Using Arrays
 * @author Manoj-Rayi
 */
public class StackA<T>{
    T stack[];
    int length;
    int top;
    public StackA(T[] stack){
        this.stack = stack;
        this.length = stack.length;
        this.top = -1;
    }
    public void push(T data){
        if(top < length-1){
            top++;
            stack[top] = data;
        }else{
            System.out.println("Stack OverFLow");
        }
    }
    public T pop(){
        if(top > -1){
            return stack[top--];
        }
        System.out.println("Stack UnderFlow");
        return null;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public void printStack(){
        if(top==-1){
            System.out.println("Stack UnderFlow");
        }else{
            for(int i=top;i>=0;i--){
                String print = "| " + stack[i] + " |";
                System.out.println(print);
                for(int j=0;j<print.length();j++)
                    System.out.print("-");
                System.out.println();
            }
            System.out.println();
        }
    }
}
