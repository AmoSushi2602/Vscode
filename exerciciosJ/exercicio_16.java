package exerciciosJ;
//Ler 5 números e somar todos eles

import java.util.Scanner;


public class exercicio_16 {
    public static void main(String[] args) {
        int n = 0;
        try(Scanner input = new Scanner(System.in)){
            for(int i = 1; i<= 5; i++){
                System.out.println("Digite o "+i+"° número: " );
                n += input.nextInt();
            }
            System.out.println(n);
        }
    }    
}
