package fr.florent.calculatrice;

public class Main {

    public static void main(String[] args) {
        Calculator.debug = true;


        String formula = "2/2*5*2^3!";
        //String formula = "2/2*5*2^3!";

        Operation operation = Reader.parseFormula(formula);
        System.out.println(operation);

        System.out.println(formula + "=" + Calculator.calculate(operation));

    }


}
