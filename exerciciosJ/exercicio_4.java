package exerciciosJ;
//Média de três notas

//Enunciado:
//Calcule a média de 3 notas e diga se o aluno foi aprovado (média ≥ 6).

public class exercicio_4 {
    public static void main(String[] args) {
    
        double a = 6.8, b = 8.05, c = 2.5;
        double media = (a + b + c) / 3;

        if(media >= 6){
            System.out.println("Passou!");
        }else{
            System.out.println("Não Passou!");
        }
    }
}
