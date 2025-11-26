package calculo_lucros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


class Operacao{

    double deposito;
    double saque;
    double bau;
    int filha;
    
    public Operacao(int filha, double deposito, double saque, double bau){
        this.filha = filha; 
        this.deposito = deposito;
        this.saque = saque;
        this.bau = bau;
          
    }//lucro real
    public double lucro(){
        return saque + bau - deposito;
    }
    //Bruto
    public double bruto(){
        return saque + bau;
    }    
}

public class Main extends JFrame{
    private JTextField txtDeposito, txtSaque, txtBau, txtFilha;
    private JLabel lblLucro;
    private ArrayList<Operacao> lista = new ArrayList<>();

    public Main(){
        super("Caluladora de lucros");

        setLayout(new GridLayout(6,2,10,10));
        
        //Campos
        add(new JLabel("Filha:"));
        txtFilha = new JTextField();
        add(txtFilha);

        add(new JLabel("Depósito:"));
        txtDeposito = new JTextField();
        add(txtDeposito);

        add(new JLabel("Saque:"));
        txtSaque = new JTextField();
        add(txtSaque);

        add(new JLabel("Bau:"));
        txtBau = new JTextField();
        add(txtBau);

        //Botoes
        JButton btnAdd = new JButton("Adicionar operação");
        add(btnAdd);

        JButton btnSomar = new JButton("Somar tudo");
        add(btnSomar);

        //resultado
        add(new JLabel("Total:"));
        lblLucro = new JLabel("0");
        add(lblLucro);

        //Açoes

        btnAdd.addActionListener(e -> adicionarOperacoes());

        btnSomar.addActionListener(e -> somarOperacoes());

        // conf janela
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private void adicionarOperacoes(){
        try{
            int filha = Integer.parseInt(txtFilha.getText());
            double deposito = Double.parseDouble(txtDeposito.getText());
            double saque = Double.parseDouble(txtSaque.getText());
            double bau = Double.parseDouble(txtBau.getText());

            lista.add(new Operacao(filha, deposito, saque, bau));

            JOptionPane.showMessageDialog(this, "Operação adicionada!");


            txtFilha.setText("");
            txtDeposito.setText("");
            txtSaque.setText("");
            txtBau.setText("");
    } catch (Exception e){
    JOptionPane.showMessageDialog(this, "Digite valores válidos!");
}
}

private void somarOperacoes(){
    double totalLucro = 0;
    double totalBruto = 0;
    
    for(Operacao o : lista){
        totalLucro += o.lucro();
        totalBruto += o.bruto();
    }
    lblLucro.setText(
        "Lucro: " + String.format("%.2f", totalLucro) + 
        "| Bruto: " + String.format("%.2f", totalBruto)
    );
    

}
public static void main(String[] args) {
    new Main();
}
}