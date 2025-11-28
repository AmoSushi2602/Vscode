package app.view;

import app.controller.OperacaoController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;


public class TelaPrincipal extends JFrame{

    private final OperacaoController controller = new OperacaoController();

    //texts

    private final JTextField txtDep = new JTextField();
    private final JTextField txtSaq = new JTextField();
    private final JTextField txtBau = new JTextField();
    private final JLabel lblTotal = new JLabel("Total lucro: 0.00");

    private final DefaultTableModel tableModel = new DefaultTableModel(
        new Object[]{"Depósito","Saque","Baú","Bruto","Lucro"},0
    );

    public TelaPrincipal(){
        super("Calculadora de lucros");

        //icone top
        setIconImage(
            Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/app/view/icons/Logo.png")
            )
        );

        initComponents();

    }
    private void initComponents(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8,8));

        //formulario
        JPanel form = new JPanel(new GridLayout(4,2,5,5));
        form.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        form.add(new JLabel("Depósito: ")); form.add(txtDep);
        form.add(new JLabel("Saque: ")); form.add(txtSaq);
        form.add(new JLabel("Baú: ")); form.add(txtBau);

        //botoes

        JButton btnAdd = new JButton("Adicionar");
        btnAdd.addActionListener(this::adicionar);
        form.add(btnAdd);

        JButton btnLimpar = new JButton("Limpar tudo");
        btnLimpar.addActionListener(e -> {
            controller.limpar();
            atualizarTabela();
        });
        form.add(btnLimpar);

        add(form, BorderLayout.NORTH);

        //tabelas
        JTable tabela = new JTable(tableModel);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        //rodape
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footer.add(lblTotal);
        add(footer, BorderLayout.SOUTH);

        //tamanho aba
        setSize(560,360);
        setLocationRelativeTo(null);
    }
    private void adicionar(ActionEvent e){
        try {
            double dep = Double.parseDouble(txtDep.getText().trim());
            double saq = Double.parseDouble(txtSaq.getText().trim());
            double bau = Double.parseDouble(txtBau.getText().trim());

            controller.adicionarOperacao(dep, saq, bau);
            atualizarTabela();

            txtDep.setText("");
            txtSaq.setText("");
            txtBau.setText("");
            txtDep.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Preencha números válidos.","Erro",JOptionPane.ERROR_MESSAGE);
        }
    }
    private void atualizarTabela(){
        //Limpar tela
        tableModel.setRowCount(0);
        for(app.model.Operacao op : controller.getTodas()){
            tableModel.addRow(new Object[]{
                op.getDeposito(), op.getSaque(), op.getBau(), op.getBruto(), op.getLucro()
            });
        }
        lblTotal.setText(String.format("Total lucro: %.2f", controller.getTotalLucro()));
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new TelaPrincipal().setVisible(true);
        });

    }


}


