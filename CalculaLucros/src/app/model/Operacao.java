package app.model;

public class Operacao {

    double deposito;
    double saque;
    double bau;
    
    public Operacao(double deposito, double saque, double bau){
       
        this.deposito = deposito;
        this.saque = saque;
        this.bau = bau;
          
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

    public String toString(){
        return String.format("Dep: %.2f Saq: %.2f Bau: %.2f Bruto: %.2f Lucro: %.2f",
            deposito, saque, bau, getBruto(), getLucro());
    }
}
