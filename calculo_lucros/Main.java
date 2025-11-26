package calculo_lucros;

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

public class Main extends JFrame{

    private JTabbedPane tabs;
    private ArrayList<Operacao> lista = new ArrayList<>();

    //lbl 1 aba
    private JLabel lblTotalBruto;
    private JLabel lblTotalLucro;

    public Main(){
        super("Caluladora de lucros por filha");

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
                double bau = Double.parseDouble(txtBau.getText());            }
                
                Operacao op = new Operacao(dep, saq, bau);
                Operacoes.add(op);

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

    private void criarAbaMae(){
        
    }

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