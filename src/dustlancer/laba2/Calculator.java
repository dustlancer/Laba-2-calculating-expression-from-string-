package dustlancer.laba2;

public class Calculator {

    private int pos;    // Индекс текущего символа
    private String[] symbols;    // Массив символов

    // Конструктор класса
    public Calculator(String exp) {
        this.symbols = exp.split(" ");
        this.pos = 0;
    }

    // Обработка скобок
    public double factor() {
        String next = symbols[pos];
        double result;
        if (next.equals("(")) {
            pos++;
            result = calculate();
            String closingBracket;
            if (pos < symbols.length) {
                closingBracket = symbols[pos];
            } else {
                throw new IllegalArgumentException("Bad expression!");
            }
            if (closingBracket.equals(")")) {
                pos++;
                return result;
            }
            throw new IllegalArgumentException("Needed ')', got" + closingBracket);
        }
        pos++;
        return Double.parseDouble(next);
    }

    // Выполнение умножения и деления
    public double multiply() {
        double first = factor();

        while (pos < symbols.length) {
            String operator = symbols[pos];
            if (!operator.equals("*") && !operator.equals("/")) {
                break;
            } else {
                pos++;
            }

            double second = factor();
            if (operator.equals("*")) {
                first *= second;
            } else {
                first /= second;
            }
        }
        return first;
    }

    // Выполнение сложения и вычитания
    private double calculate() {
        double first = multiply();

        while (pos < symbols.length) {
            String operator = symbols[pos];
            if (!operator.equals("+") && !operator.equals("-")) {
                break;
            } else {
                pos++;
            }

            double second = multiply();
            if (operator.equals("+")) {
                first += second;
            } else {
                first -= second;
            }
        }
        return first;
    }


    public static void main(String[] args) {
        String exp = "4.5 * ( 6 / 2 + ( 8 - 4 ) * 2 ) / 3 + 5";
        Calculator calculator = new Calculator(exp);
        System.out.println(exp + " = " + calculator.calculate());
        System.out.println("Проверка: " + (4.5 * (6.0 / 2 + (8 - 4) * 2) / 3 + 5));
    }

}