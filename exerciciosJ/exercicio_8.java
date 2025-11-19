package exerciciosJ;
//Somar todos os valores de um array

//Enunciado:
//Some todos os valores de um array de inteiros.
public class exercicio_8{
    public static void main(String[] args) {
        
        int [] numeros = {4, 7, 1, 9};
        int soma = 0;

        for(int i : numeros){
           soma += i;
        //vai imprimindo e somando
        System.out.println(soma);
        }
        //ja sai o total
        System.out.println(soma);
    }
}