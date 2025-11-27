package CalculaLucros1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


class Operacao{

    double deposito;
    double saque;
    double bau;
    
    public Operacao(double deposito, double saque, double bau){
       
        this.deposito = deposito;
        this.saque = saque;
        this.bau = bau;
          
    }//Bruto
    public double bruto(){
        return saque + bau;
    
    }//Lucro   
    public double lucro(){
        return saque + bau - deposito;
    }
}

public class Main extends JFrame{

    private JTabbedPane tabs;
    private ArrayList<Operacao> lista = new ArrayList<>();

    //lbl 1 aba
    private JLabel lblTotalBruto;
    private JLabel lblTotalLucro;

    public Main(){
        super("Calculadora de lucros por filha");

        tabs = new JTabbedPane();
        
        // Aba mãe
        criarAbaMae();

        //filhas
        JPanel painelCriacao = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtDeposito = new JTextField();
        JTextField txtSaque = new JTextField();
        JTextField txtBau = new JTextField();
        JTextField txtFilha = new JTextField();

        //criacao

        painelCriacao.add(new JLabel("Filha número:"));
        painelCriacao.add(txtFilha);

        
        painelCriacao.add(new JLabel("Depósito:"));
        painelCriacao.add(txtDeposito);

        painelCriacao.add(new JLabel("Saque:"));
        painelCriacao.add(txtSaque);
        
        painelCriacao.add(new JLabel("Baú:"));
        painelCriacao.add(txtBau);

        //botao
        JButton btnAdd = new JButton("Adicionar Filha");
        painelCriacao.add(btnAdd);

        btnAdd.addActionListener(e -> {
            try{
                int filhaNum = Integer.parseInt(txtFilha.getText());
                double dep = Double.parseDouble(txtDeposito.getText());
                double saq = Double.parseDouble(txtSaque.getText());
                double bau = Double.parseDouble(txtBau.getText());           
                
                Operacao op = new Operacao(dep, saq, bau);
                lista.add(op);

                criarAbaFilha(filhaNum, op);
                atualizarTotais();

                    
                txtFilha.setText("");
                txtDeposito.setText("");
                txtSaque.setText("");
                txtBau.setText("");

                JOptionPane.showMessageDialog(this, "Filha adicionada!");
            
            }catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Valores invalidos!");
            }
        });

        add(painelCriacao, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        //conf da janela
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //criar a mae
    private void criarAbaMae(){
        JPanel mae = new JPanel(new GridLayout(2, 2, 10, 10));

        lblTotalBruto = new JLabel("0");
        lblTotalLucro = new JLabel("0");
        
        mae.add(new JLabel("TOTAL BRUTO:"));
        mae.add(lblTotalBruto);

        mae.add(new JLabel("TOTAL LUCRO:"));
        mae.add(lblTotalLucro);
        
        tabs.add("Geral", mae);
    }
    //criar a filha
    private void criarAbaFilha(int num, Operacao op){

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));

        painel.add(new JLabel("Depósito:"));
        painel.add(new JLabel(String.valueOf(op.deposito)));

        painel.add(new JLabel("Saque:"));
        painel.add(new JLabel(String.valueOf(op.saque)));

        painel.add(new JLabel("Baú:"));
        painel.add(new JLabel(String.valueOf(op.bau)));

        painel.add(new JLabel("Bruto:"));
        painel.add(new JLabel(String.valueOf(op.bruto())));

        painel.add(new JLabel("Lucro:"));
        painel.add(new JLabel(String.valueOf(op.lucro())));

        tabs.add("Filha " + num, painel);

    }
    // atualiza os totais
    private void atualizarTotais(){

        double totalBruto = 0;
        double totalLucro = 0;

        for (Operacao o : lista) {
            totalBruto += o.bruto();
            totalLucro += o.lucro();
            
        }
        lblTotalBruto.setText(String.format("%.2f", totalBruto));
        lblTotalLucro.setText(String.format("%.2f", totalLucro));
        
        
    }
        
    public static void main(String[] args) {
        new Main();
    }
}