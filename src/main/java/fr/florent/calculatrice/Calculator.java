package fr.florent.calculatrice;

public class Calculator {

    public static boolean debug = false;

    public static double calculate(Operation operation) {

        if (debug) {
            System.out.println(operation);
        }

        for (int i = EnumOperator.getMaxPriority(); i >= 0; i--) {

            Operation currentOperation = operation;

            do {
                if (currentOperation.getSubValue() != null) {
                    currentOperation.setValue(calculate(currentOperation.getSubValue()));
                    currentOperation.setSubValue(null);
                }

                if (i == currentOperation.getOperator().priority) {

                    switch (currentOperation.getOperator()) {

                        case FACTORIAL:
                            operateFactorial(currentOperation);
                            break;
                        case POWER:
                            operatePower(currentOperation);
                            break;
                        case MULTIPLICATION:
                            operateMultiplication(currentOperation);
                            break;
                        case DIVISION:
                            operateDivision(currentOperation);
                            break;
                        case ADDITION:
                            operateAddition(currentOperation);
                            break;
                        case SUBSTRACTION:
                            operateSubstraction(currentOperation);
                            break;
                        case VALUE:
                            return currentOperation.getValue();
                    }
                    if (debug) {
                        System.out.println(operation);
                    }

                }

            } while ((currentOperation = currentOperation.getRightOperation()) != null);


        }

        return 0;

    }

    private static void operateSubstraction(Operation currentOperation) {
        Operation rightOperation = currentOperation.getRightOperation();
        affectValueToRigthOperation(currentOperation,
                currentOperation.getValue() - rightOperation.getValue());
    }

    private static void operateAddition(Operation currentOperation) {
        Operation rightOperation = currentOperation.getRightOperation();
        affectValueToRigthOperation(currentOperation,
                currentOperation.getValue() + rightOperation.getValue());
    }

    private static void operateDivision(Operation currentOperation) {
        Operation rightOperation = currentOperation.getRightOperation();
        affectValueToRigthOperation(currentOperation,
                currentOperation.getValue() / rightOperation.getValue());
    }

    private static void operateMultiplication(Operation currentOperation) {
        Operation rightOperation = currentOperation.getRightOperation();
        affectValueToRigthOperation(currentOperation,
                currentOperation.getValue() * rightOperation.getValue());
    }

    private static void operatePower(Operation currentOperation) {
        Operation rightOperation = currentOperation.getRightOperation();

        double value = currentOperation.getValue();

        double total = 1;

        for (int j = 1; j <= rightOperation.getValue(); j++) {
            total *= value;
        }

        affectValueToRigthOperation(currentOperation, total);
    }

    private static void affectValueToRigthOperation(Operation currentOperation, double total) {

        Operation rightOperation = currentOperation.getRightOperation();

        rightOperation.setValue(total);
        rightOperation.setLeftOperation(currentOperation.getLeftOperation());
        if (currentOperation.getLeftOperation() != null) {
            currentOperation.getLeftOperation().setRightOperation(rightOperation);
        }
    }

    private static void operateFactorial(Operation currentOperation) {
        double total = 0;
        if (currentOperation.getValue() > 0) {
            total = 1;
            for (int j = 1; j < currentOperation.getValue() + 1; j++) {
                total *= j;
            }
        }
        currentOperation.setOperator(EnumOperator.VALUE);
        currentOperation.setValue(total);
    }
}
