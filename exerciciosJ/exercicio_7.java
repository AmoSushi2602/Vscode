package exerciciosJ;
//Contar quantos números são maiores que 10

//Enunciado:
//Dado um array de 5 posições, conte quantos números são maiores que 10.

public class exercicio_7 {
    public static void main(String[] args) {
        int[] valores = {5, 12, 8, 30, 11};
        int contador = 0;

        for(int v : valores){
            if(v > 10){
                System.out.println("Eles são = " + v);
                contador++;
            }
        }
        System.out.println("Quantidade > 10 = " + contador);
    }    
}
