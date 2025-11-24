package exerciciosJ;


import java.util.Scanner;


//Ler 5 nomes e mostrar todos
public class exercicio_17{
    public static void main(String[] args) {
        
    try(Scanner input = new Scanner(System.in)){
        String[] nomes = new String[5];
        for(int i = 0; i < nomes.length; i++){
            System.out.println("Nome" + (i+1) + ":");
            nomes[i] = input.nextLine();
        }
        System.out.println("\n Nomes digitados:");
        for (String nome : nomes){//for each declara var dentro dela temporaria lembrar disso
            System.out.println(nome);
        }
    }    
}
}