package app.model;

import java.time.LocalDate;

public class Operacao {

    private LocalDate data;
    private double deposito;
    private double saque;
    private double bau;
    
    //constructor vazio
    public Operacao(){}

    //constructor cheio
    public Operacao(LocalDate data, double deposito, double saque, double bau){
       
        this.data = data;
        this.deposito = deposito;
        this.saque = saque;
        this.bau = bau;
          
    }
    
    //Getters e Setters
    public  LocalDate getData(){
        return data;
    }

    public void setData(LocalDate data){
        this.data = data;
    } 

    public double getDeposito(){return deposito;}
    public double getSaque(){return saque;}
    public double getBau(){return bau;}
    
    //Bruto
    public double getBruto(){
        return saque + bau;
    
    }//Lucro   
    public double getLucro(){
        return getBruto() - deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    public void setSaque(double saque) {
        this.saque = saque;
    }

    public void setBau(double bau) {
        this.bau = bau;
    }

    @Override

    public String toString() {
        return String.format(
            "Data: %s | Dep: %.2f | Saq: %.2f | Bau: %.2f | Bruto: %.2f | Lucro: %.2f",
            data, deposito, saque, bau, getBruto(), getLucro()
        );
    }
}