package fr.florent.calculatrice;

public class Reader {

    public static Operation parseFormula(String formula) {

        if (formula == null) {
            throw new NullPointerException("formula cannot be null");
        }

        // On retire tout les espaces
        formula = formula.replaceAll(" ", "");

        Operation subOperation = null;
        Operation operation = new Operation();
        operation.setOperator(EnumOperator.VALUE);

        StringBuilder number = new StringBuilder();
        for (int i = formula.length() - 1; i >= 0; i--) {
            char car = formula.charAt(i);

            if (car >= '0' && car <= '9') {
                number.insert(0, car);
            } else if (car == '.' || car == ',') {
                number.insert(0, '.');
            } else if (car == '!') {

                subOperation = new Operation();
                subOperation.setOperator(EnumOperator.FACTORIAL);
            } else if (car == ')') {
                int closeBracket = 1;
                StringBuilder subFormula = new StringBuilder();

                for (int j = i - 1; j >= 0; j--) {
                    char subCar = formula.charAt(j);
                    if (subCar == '(') {
                        closeBracket--;
                    } else if (subCar == ')') {
                        closeBracket++;
                    }
                    if (closeBracket == 0) {
                        subOperation = parseFormula(subFormula.toString());
                        i = j;
                        break;
                    }

                    subFormula.insert(0, subCar);
                }
            } else {

                subOperation = afecteSubOperation(subOperation, operation, number.toString());

                EnumOperator enumOperator = EnumOperator.getOperatorBySign(car);

                Operation currentOperation = new Operation();
                currentOperation.setOperator(enumOperator);
                operation.setLeftOperation(currentOperation);

                currentOperation.setRightOperation(operation);
                operation = currentOperation;

                number = new StringBuilder();
            }
        }
        afecteSubOperation(subOperation, operation, number.toString());

        return operation;

    }

    private static Operation afecteSubOperation(Operation subOperation, Operation operation, String number) {
        double value = !number.isEmpty() ? Double.parseDouble(number) : 0;
        if (subOperation != null) {
            if (subOperation.getValue() == -1) {
                subOperation.setValue(value);
            }
            operation.setSubValue(subOperation);
            subOperation = null;
        } else {
            operation.setValue(value);
        }
        return subOperation;
    }

    private static Operation addOperation(Operation operation, String number, char car) {
        operation.setValue(Double.parseDouble(number));

        EnumOperator enumOperator = EnumOperator.getOperatorBySign(car);

        Operation currentOperation = new Operation();
        currentOperation.setOperator(enumOperator);
        operation.setLeftOperation(currentOperation);

        currentOperation.setRightOperation(operation);
        operation = currentOperation;
        return operation;
    }
}
