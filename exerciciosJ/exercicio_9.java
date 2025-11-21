package exerciciosJ;


import java.util.Scanner;


//Função para calcular o dobro de um número

//Enunciado:
//Crie um método que recebe um número e retorna o dobro dele.

public class exercicio_9{   
    public static int dobro(int n){
            return n * 2;   
        }
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)){
        System.out.println("Qual o numero você deseja dobrar:");    
        String numero = input.nextLine();

        int valor = Integer.parseInt(numero);
        
        System.out.println(dobro(valor));      

        }
    }
}