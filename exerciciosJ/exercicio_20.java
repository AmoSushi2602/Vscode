package exerciciosJ;

//Criar uma calculadora simples (funções + switch)

import java.util.Scanner;


public class exercicio_20 {
    public static double calcular(double a, double b, char op){
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b != 0) {
                    yield a / b;
                } else {
                    yield 0;
                }
            }
            default -> 0;
        };
    }
    public static void main(String[] args){
        try(Scanner input = new Scanner(System.in)){
            System.out.println("A: ");
            Double a = input.nextDouble();

            System.out.println("Operação (+ - * /): ");
            char op = input.next().charAt(0);

            System.out.println("b: ");
            Double b = input.nextDouble();
        
        
            System.out.println("Resultado" + calcular(a, b, op));
        }
    }
}
