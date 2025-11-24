package exerciciosJ;
//Verificar se a pessoa é maior de idade

import java.util.Scanner;

public class exercicio_13 {
    public static void main(String[] args) {
    int idade;
    
    try(Scanner input = new Scanner(System.in)){
    System.out.println("Qual a sua idade? ");
    idade = input.nextInt();
    }
    
    if(idade >=18){
        System.out.println("Você é de maior!");
    }else{
        System.out.println("Você é de menor!");
    }
  }  
}
