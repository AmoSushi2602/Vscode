package app.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;

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
import com.toedter.calendar.JCalendar;

import app.controller.OperacaoController;
import app.model.Operacao;

public class TelaPrincipal extends JFrame {


    private JCalendar calendario;
    private LocalDate dataSelecionada = LocalDate.now();

    private final JLabel lblLucroDia = new JLabel("Lucro do Dia: R$ 0,00");
    private final JLabel lblLucroTotal = new JLabel("Lucro Total: R$ 0,00");

    private final OperacaoController controller = new OperacaoController();

    private final JTextField txtDep = new JTextField();
    private final JTextField txtSaq = new JTextField();
    private final JTextField txtBau = new JTextField();

    private final JButton btnAdicionar = new JButton("Adicionar");
    private final JButton btnExcluir = new JButton("Excluir Selecionado");

    private final java.util.List<Operacao> listaTabela = new java.util.ArrayList<>();

    private final OperacaoTableModel tableModel = new OperacaoTableModel(listaTabela);
    private final JTable tabela = new JTable(tableModel);

    public TelaPrincipal() {
        super("Calculadora de Lucros");

        setIconImage(new ImageIcon(getClass().getResource("/icons/logo.png")).getImage());

        configurarTela();
        configurarEventos();

        atualizarTabelaPorData();
        atualizarLucros();

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

        txtDep.setColumns(12);
        txtSaq.setColumns(12);
        txtBau.setColumns(12);

        //Calendário

        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.fill = GridBagConstraints.NONE;

        calendario = new JCalendar();
        calendario.setDate(java.sql.Date.valueOf(dataSelecionada));
        painel.add(calendario, gbc);

        gbc.gridheight = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        calendario.addPropertyChangeListener("calendar", evt -> {
            dataSelecionada = calendario.getDate()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            atualizarTabelaPorData();
            atualizarLucros();
        });
        
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;


    //lINHAS
        gbc.gridy = 1;

        // Depósito label
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Depósito:"), gbc);

        // Depósito campo
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(txtDep, gbc);

        // Saque label
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Saque:"), gbc);

        // Saque campo
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(txtSaq, gbc);

        // LINHA 2 - Baú

        gbc.gridy = 1;
        gbc.gridx = 4;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Baú:"), gbc);

        gbc.gridx = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(txtBau, gbc);

        // =====================
        // LINHA 3 - Botão Adicionar
        // =====================
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(btnAdicionar, gbc);

        // =====================
        // LINHA 4 - Tabela
        // =====================
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 1;
        gbc.weighty = 1; 
        gbc.fill = GridBagConstraints.BOTH;
        painel.add(new JScrollPane(tabela), gbc);

        // =====================
        // LINHA 5 - Botão Excluir
        // =====================
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(btnExcluir, gbc);

        // =====================
        // LINHA 6 - Rodapé
        // =====================
        JPanel rodape = new JPanel();
        rodape.add(lblLucroDia);
        rodape.add(lblLucroTotal);

        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 6;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        painel.add(rodape, gbc);

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
            double dep = parseCampo(txtDep);
            double saq = parseCampo(txtSaq);
            double bau = parseCampo(txtBau);

            controller.adicionarOperacao(dataSelecionada, dep, saq, bau);
            atualizarTabelaPorData();
            atualizarLucros();

            txtDep.setText("");
            txtSaq.setText("");
            txtBau.setText("");
            txtDep.requestFocus();
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Preencha números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseCampo(JTextField campo) {
        String txt = campo.getText().trim().replace(",", ".");
        return txt.isEmpty() ? 0.0 : Double.parseDouble(txt);
    }
    //excluir selecionado

    private void excluirSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha >= 0) {
            Operacao op  = tableModel.getOperacao(linha);
            controller.removerOperacao(op);
            atualizarTabelaPorData();
            atualizarLucros();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
        }
    }

    //atualizar tabela por data
    private void atualizarTabelaPorData() {
    listaTabela.clear();
    listaTabela.addAll(controller.buscarPorData(dataSelecionada));
    tableModel.fireTableDataChanged();
}

    //atual lucros
    private void atualizarLucros() {
        lblLucroDia.setText(
            String.format("Lucro do dia: R$ %.2f", 
                controller.getLucroPorData(dataSelecionada))
        );

        lblLucroTotal.setText(
            String.format("Lucro Total: R$ %.2f", 
                controller.getTotalLucro())
        );
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
