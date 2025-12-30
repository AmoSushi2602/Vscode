package app.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import app.license.LicencaRepository;
import app.license.LicencaService;
import app.util.HWIDUtil;

public class TelaAtivacao extends JFrame {

    private final JTextField txtLicenca = new JTextField();

    public TelaAtivacao() {
        super("Ativação do Sistema");

        String hwid = HWIDUtil.gerarHWID();

        JTextArea txtHWID = new JTextArea(hwid);
        txtHWID.setEditable(false);

        JButton btnAtivar = new JButton("Ativar");

        btnAtivar.addActionListener(e -> ativar());

        JPanel painel = new JPanel(new GridLayout(0,1,8,8));
        painel.add(new JLabel("Seu código (HWID):"));
        painel.add(txtHWID);
        painel.add(new JLabel("Informe a licença:"));
        painel.add(txtLicenca);
        painel.add(btnAtivar);

        add(painel, BorderLayout.CENTER);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void ativar() {
        try {
            String licenca = txtLicenca.getText().trim();

            LicencaService service = new LicencaService();
            LicencaRepository repo = new LicencaRepository();

            service.validar(licenca);
            repo.salvar(licenca);

            JOptionPane.showMessageDialog(this, "Licença ativada com sucesso!");
            dispose();
            new TelaPrincipal().setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
