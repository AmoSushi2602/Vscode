package app.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.TableColumnModel;

import com.formdev.flatlaf.FlatLightLaf;

import app.controller.OperacaoController;

public class TelaPrincipal extends JFrame {


    private final OperacaoController controller = new OperacaoController();

    private final JTextField txtDep = new JTextField();
    private final JTextField txtSaq = new JTextField();
    private final JTextField txtBau = new JTextField();

    private final JButton btnAdicionar = new JButton("Adicionar");
    private final JButton btnExcluir = new JButton("Excluir Selecionado");

    private OperacaoTableModel tableModel = new OperacaoTableModel(controller.getTodas());
    private JTable tabela = new JTable(tableModel);

    public TelaPrincipal() {
        super("Calculadora de Lucros");

        setIconImage(new ImageIcon(getClass().getResource("/icons/logo.png")).getImage());

        configurarTela();
        configurarEventos();

        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void configurarTela() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;
    //lINHAS
        // Linha 1
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Depósito:"), gbc);

        gbc.gridx = 1;
        txtDep.setHorizontalAlignment(JTextField.RIGHT);
        painel.add(txtDep, gbc);

        gbc.gridx = 2;
        painel.add(new JLabel("Saque:"), gbc);

        gbc.gridx = 3;
        txtSaq.setHorizontalAlignment(JTextField.RIGHT);
        painel.add(txtSaq, gbc);

        gbc.gridx = 4;
        painel.add(new JLabel("Baú:"), gbc);

        gbc.gridx = 5;
        txtBau.setHorizontalAlignment(JTextField.RIGHT);
        painel.add(txtBau, gbc);

        // Botão adicionar
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(btnAdicionar, gbc);

        // Tabela
        gbc.gridy = 2;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, gbc);

        // Botão excluir
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        painel.add(btnExcluir, gbc);

        add(painel);

        configurarTabela();
    }

    private void configurarEventos() {
        btnAdicionar.addActionListener(this::adicionar);
        btnExcluir.addActionListener(e -> excluirSelecionado());
    }

    private void configurarTabela() {
        tabela.setRowHeight(25);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel col = tabela.getColumnModel();
        col.getColumn(0).setPreferredWidth(100);
        col.getColumn(1).setPreferredWidth(100);
        col.getColumn(2).setPreferredWidth(100);
        col.getColumn(3).setPreferredWidth(100);
        col.getColumn(4).setPreferredWidth(100);
    }

    private void adicionar(ActionEvent e) {
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
            JOptionPane.showMessageDialog(this, "Preencha números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void excluirSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            controller.removerOperacao(linha);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    private void atualizarTabela() {
        tableModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao inicializar o FlatLaf.");
        }

        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
