package exerciciosJ;
//Tabuada de um número

//Enunciado:
//Mostre a tabuada do número 7 (de 1 a 10).
public class exercicio_5 {
    public static void main(String[] args) {
        int tabuada = 7;
        for(int i = 1; i <= 10; i ++){
            System.out.println("O valor de " + tabuada + 'x' + i + " = " + (tabuada * i));
        }

    }    
}
