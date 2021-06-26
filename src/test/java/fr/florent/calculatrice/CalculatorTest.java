package fr.florent.calculatrice;


import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void factoriel() {
        String formula = "10!";

        testFormula(formula, 3628800);
    }

    @Test
    public void multiplication() {
        String formula = "10*5";

        testFormula(formula, 50);
    }

    @Test
    public void division() {
        String formula = "10/4";

        Operation operation = Reader.parseFormula(formula);

        Assert.assertEquals(2.5, Calculator.calculate(operation), 0.1);
    }


    @Test
    public void addition() {
        String formula = "5+2";

        testFormula(formula, 7);
    }

    @Test
    public void soustraction() {
        String formula = "10-4";

        testFormula(formula, 6);
    }

    @Test
    public void soustractionWithNegResult() {
        String formula = "4-10";

        testFormula(formula, -6);
    }

    @Test
    public void negatifNumber() {
        String formula = "-10";

        testFormula(formula, -10);
    }

    @Test
    public void additionAndSoustraction() {
        String formula = "5+4-10";

        testFormula(formula, -1);
    }

    @Test
    public void soustractionAndAddition() {
        String formula = "4-10+5";

        testFormula(formula, -1);
    }

    @Test
    public void additionAndDivision() {
        String formula = "5+2/4";

        testFormula(formula, 5.5);
    }

    @Test
    public void soustractionAndDivision() {
        String formula = "5-2/4";

        testFormula(formula, 4.5);

    }

    @Test
    public void multiplicationAndFactorielAtEnd() {
        String formula = "10*4!";

        testFormula(formula, 240);
    }


    @Test
    public void factorielAndMultiplication() {
        String formula = "5!*2";

        testFormula(formula, 240);
    }


    @Test
    public void additionAndFactorielAndMultiplication() {
        String formula = "2+5!*2";

        testFormula(formula, 242);
    }

    @Test
    public void additionAndSousOperation() {
        String formula = "2+(5!*2)";

        testFormula(formula, 242);
    }

    @Test
    public void sousOperationAndMultiplication() {
        String formula = "(2+5!)*2";

        testFormula(formula, 244);
    }

    @Test
    public void divisionAndSousOperationAndMultiplication() {
        String formula = "2440/(2+5!)*2";

        testFormula(formula, 40);
    }
    @Test
    public void testLazor() {
        String formula = "15 / 3 + (1 + 3) * 2 ^ 3!";

        testFormula(formula, 261);
    }

    @Test
    public void multiLevel() {
        String formula = "(5+3)*(53*2)+100/(5*(15+5^1))-10";

        testFormula(formula, 839);
    }

    //

    private void testFormula(String formula, double expected) {
        Operation operation = Reader.parseFormula(formula);

        double result = Calculator.calculate(operation);

        System.out.println(formula + "=" + result);

        Assert.assertEquals(expected, result, 0.01);
    }


}
