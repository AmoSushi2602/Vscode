package exerciciosJ;

import java.util.Scanner;

//Contar quantas letras uma palavra tem
public class exercicio_19 {
    public static void main(String[] args) {
        try(Scanner input = new Scanner(System.in)){
            System.out.println("Defina a palavra: ");
            String palavra = input.nextLine();
        
             System.out.println("O número de letras é: " + palavra.length());
        }   
    }
}
