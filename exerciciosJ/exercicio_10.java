package exerciciosJ;


import java.util.Scanner;

//Verificar se uma senha está correta

//Enunciado:
//A senha correta é "1234". Crie uma variável com uma senha digitada e verifique.

public class exercicio_10{
    public static void main(String[] args) {
        String senha = "1234";
        try(Scanner input = new Scanner(System.in)){
            System.out.println("Qual a senha:");
            String senhau = input.nextLine();
        if(senhau.equals( senha)){
            System.out.println("Acertou!");
        }else{
            System.out.println("Errouuuu!");
            }
        }
    }
}