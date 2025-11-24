package exerciciosJ;
//Criar função que retorna o maior entre 3 números

import java.util.Scanner;

public class exercicio_18 {
    public static int maior(int a, int b, int c){
        int m = a;

        if(b > m) m = b;
        if(c > m) m = c;
        return m;
    } 
    public static void main(String[] args) {
        try(Scanner Input = new Scanner(System.in)){
        int[] numeros = new int[3];
        for(int i = 0; i < numeros.length; i++){
            System.out.println((i+1) + "° Número:");
            numeros[i] = Input.nextInt();
        }
        System.out.println("\nNúmeros digitados:");
        for(int numero :numeros){
            System.out.println(numero);
        }
        int resultado = maior(numeros[0], numeros[1], numeros[2]);
            System.out.println("\nO maior número é " + resultado + "!");
         

        }
    }    
}
