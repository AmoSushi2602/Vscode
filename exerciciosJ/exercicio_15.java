package exerciciosJ;
//Criar uma função que retorna se um número é par

import java.util.Scanner;

public class exercicio_15 {
    public static boolean  retorno(double v){
        return  v % 2 == 0;
    }
    public static void main(String[] args) {
        double v;
        try(Scanner input = new Scanner(System.in)){
            System.out.println("Escreva o número para ver se é par ou ímpar: ");
            v = input.nextDouble(); 
        }
        if(retorno(v)){
            System.out.println("É par!");
        }else{
            System.out.println("É ímpar!");
        }
    }
}
