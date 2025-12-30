package app.util;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaGeradorLicenca extends JFrame {

    private JTextField txtHwid;
    private JTextField txtCliente;
    private JTextArea txtLicenca;

    public TelaGeradorLicenca() {
        setTitle("Gerador de Licenças");
        setSize(480, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 5, 5));

        panelCampos.add(new JLabel("HWID:"));
        txtHwid = new JTextField();
        panelCampos.add(txtHwid);

        panelCampos.add(new JLabel("Cliente:"));
        txtCliente = new JTextField();
        panelCampos.add(txtCliente);

        add(panelCampos, BorderLayout.NORTH);

        txtLicenca = new JTextArea();
        txtLicenca.setLineWrap(true);
        txtLicenca.setWrapStyleWord(true);
        txtLicenca.setEditable(false);
        txtLicenca.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(txtLicenca, BorderLayout.CENTER);

        JButton btnGerar = new JButton("Gerar Licença");
        btnGerar.addActionListener(e -> gerar());

        add(btnGerar, BorderLayout.SOUTH);
    }

    private void gerar() {
        String hwid = txtHwid.getText().trim();
        String cliente = txtCliente.getText().trim();

        if (hwid.isEmpty() || cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o HWID e o nome do cliente",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dados = hwid + "|" + cliente;
        String licenca = CryptoUtil.encrypt(dados);

        txtLicenca.setText(licenca);

        // copia automaticamente para a área de transferência
        Toolkit.getDefaultToolkit()
               .getSystemClipboard()
               .setContents(new StringSelection(licenca), null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new TelaGeradorLicenca().setVisible(true);
        });
    }
}
