package fr.florent.calculatrice;

public enum EnumOperator {


    VALUE('\0', 0),
    ADDITION('+', 1), SUBSTRACTION('-', 2), MULTIPLICATION('*', 3),
    DIVISION('/', 3), POWER('^', 4), FACTORIAL('!', 5);

    public char sign;
    public int priority;


    EnumOperator(char sign, int priority) {
        this.sign = sign;
        this.priority = priority;
    }

    public static int getMaxPriority() {
        int max = -1;
        for (EnumOperator operator : EnumOperator.values()) {
            if (max < operator.priority) {
                max = operator.priority;
            }
        }

        return max;
    }

    public static EnumOperator getOperatorBySign(char car) {
        for (EnumOperator operator : EnumOperator.values()) {
            if (car == operator.sign)
                return operator;
        }
        throw new ArithmeticException(String.format("Sign : %c not implemented", car));
    }

}
