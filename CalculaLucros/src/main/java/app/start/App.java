package app.start;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import app.license.LicencaException;
import app.license.LicencaRepository;
import app.license.LicencaService;
import app.util.HWIDUtil;
import app.view.TelaPrincipal;

public class App {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            System.err.println("Falha ao iniciar tema");
        }

        SwingUtilities.invokeLater(() -> {

            try {
                LicencaRepository repo = new LicencaRepository();
                String licenca = repo.carregar();

                // NÃO existe licença
                if (licenca == null) {
                    mostrarHWID();
                    System.exit(0);
                }

                // valida HWID
                new LicencaService().validar(licenca);

                // licença OK → abre o sistema
                new TelaPrincipal().setVisible(true);

            } catch (LicencaException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage(),
                        "Licença inválida",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        });
    }

    private static void mostrarHWID() {

        String hwid = HWIDUtil.gerarHWID();

        JTextArea area = new JTextArea(hwid);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.BOLD, 14));
        area.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JButton btnCopiar = new JButton("Copiar código");
        btnCopiar.addActionListener(e -> {
            Toolkit.getDefaultToolkit()
                   .getSystemClipboard()
                   .setContents(new StringSelection(hwid), null);

            JOptionPane.showMessageDialog(null, "Código copiado!");
        });

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.add(new JLabel("Envie este código para ativação:"), BorderLayout.NORTH);
        panel.add(area, BorderLayout.CENTER);
        panel.add(btnCopiar, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(null, panel, "Ativação", JOptionPane.INFORMATION_MESSAGE);
    }
}
