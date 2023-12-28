package Stacks;

public class PostFix {
    String exp;
    public PostFix(){
        exp = "";
    }
    public PostFix(String exp) {
        this.exp = exp;
    }

    static int prec(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;

    }
    public String infToPost(){
        return infToPost(this.exp);
    }
    public String infToPost(String exp) {
        this.exp = exp;
        String res = "";
        StackL<Character> stack = new StackL<>();
        for (char c : this.exp.toCharArray()) {
            if (Character.isLetterOrDigit(c))
                res += c;
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    res += stack.pop();
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            } else {
                while (!stack.isEmpty() && prec(c) <= prec(stack.peek())) {
                    if (stack.peek() == '(')
                        return "Invalid Expression";
                    res += stack.pop();
                }
                stack.push(c);
            }
        }
        while (stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
    public Integer Evaluate(){
        return Evaluate(this.exp);
    }
    public Integer Evaluate(String exp) {
        StackL<Integer> stack = new StackL<>();
        for (char c : exp.toCharArray()) {
            if (Character.isDigit(c))
                stack.push(c - '0');
            else {
                int v1 = stack.pop();
                int v2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(v2 + v1);
                        break;
                    case '-':
                        stack.push(v2 - v1);
                        break;
                    case '/':
                        stack.push(v2 / v1);
                        break;
                    case '*':
                        stack.push(v2 * v1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public String toString() {
        return this.infToPost(exp);
    }
}
