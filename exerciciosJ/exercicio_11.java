package exerciciosJ;
//Verificar se um número é positivo, negativo ou zero

import java.util.Scanner;


//Enunciado:
//Leia um número e informe se ele é positivo, negativo ou zero.

public class exercicio_11 {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            System.out.println("Qual o número: ");
            int num = input.nextInt();

            if(num > 0){
                System.out.println("Ele é positivo");
            } else if(num == 0){
                System.out.println("Ele é 0");
            } else{
                System.out.println("Ele é negativo");
            }
        }
    }
}
