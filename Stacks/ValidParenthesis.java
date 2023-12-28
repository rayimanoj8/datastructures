package Stacks;

public class ValidParenthesis {
    static char[] stack;
    static int top =-1;
    public static void push(char i){
        top++;
        stack[top] = i;
    }
    public static void pop(){
        top--;
    }
    public static Boolean valid(String exp){
        try{
            stack = new char[exp.length()];
        for(char i: exp.toCharArray()){
            if(i=='('){
                push(i);
            }
            else{
                pop();
            }
        } 
        return top==-1;
        }
        catch(Exception e){
            return false;
        }
    }
}
