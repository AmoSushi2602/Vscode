package exerciciosJ;

import java.util.Scanner;

// Criar uma função que retorna o triplo de um número
public class exercicio_12{
    public static int triplo(int n){
        return n * 3;
   } 
    public static void main(String[] args) {
    int n;
        try(Scanner Input = new Scanner(System.in)){
            System.out.println("Qual o número deseja triplicar: ");
            n = Input.nextInt();
    }
    
    System.out.println("O triplo é: " + triplo(n));

}
}
