package exerciciosJ;
//Calcular o IMC

import java.util.Scanner;

//Fórmula: peso / (altura * altura)
public class exercicio_14 {
    public static double imc( double peso, double altura) {
        return peso / (altura * altura);
    }
    public static void main(String[] args) {
        double peso; 
        double altura;
        
        try(Scanner input = new Scanner(System.in)){
        System.out.println("Qual seu peso?");
        peso = input.nextDouble();
        System.out.println("E sua altura?");
        altura = input.nextDouble();
        }
        System.out.println("Seu imc é: " + imc(peso, altura));
    }
}
