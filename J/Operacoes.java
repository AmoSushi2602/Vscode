

public class Operacoes{
    public static void main(String[] args){
    
    //Declarar variaveis
        int a = 10;
        int b = 30;
        int c = 25;
        int d = 45;
        int e = 41;

    //função de imprimir
        imprimirResultado("Soma", somar(a,b));
        imprimirResultado("Subtação", subtrair(d,e));
        imprimirResultado("Divisão", dividir (b,a));
        imprimirResultado("Multiplicação", multiplicar(c,a));
        imprimirResultado("Porcentagem", porcentual(d,e));
    }

    //gerando va
     public static int somar(int x, int y){
        return x + y;
     } 
     public static int subtrair(int x, int y){
        return x - y;
     } 
     public static int dividir(int x, int y){
        return x / y;
     }
     public static int multiplicar(int x, int y){
        return x * y;
     }
     public static int porcentual(int x, int y){
        return x % y;
     }

     public static void imprimirResultado(String operacao, int Resultado){
        System.err.println(operacao + ": " + Resultado);
     }
}


