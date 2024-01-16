import java.util.Stack;
public class yh {
    private static double evaluateExpression(String expression) {
    char[] tokens = expression.toCharArray();

    Stack<Double> values = new Stack<>();
    Stack<Character> operators = new Stack<>();

    for (int i = 0; i < tokens.length; i++) {
        if (tokens[i] == ' ')
            continue;

        if (Character.isDigit(tokens[i]) || (tokens[i] == '-' && (i == 0 || !Character.isDigit(tokens[i - 1])))) {
            StringBuilder sbuf = new StringBuilder();
            while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.' || (tokens[i] == '-' && sbuf.length() == 0))) {
                sbuf.append(tokens[i++]);
            }
            values.push(Double.parseDouble(sbuf.toString()));
            i--;
        } else if (tokens[i] == '(') {
            operators.push(tokens[i]);
        } else if (tokens[i] == ')') {
            while (operators.peek() != '(') {
                values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
            }
            operators.pop(); // Pop the '('
        } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
            while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())) {
                values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
            }
            operators.push(tokens[i]);
        }
    }

    while (!operators.empty()) {
        values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
    }

    return values.pop();
}

    private static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static double applyOperator(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

}
