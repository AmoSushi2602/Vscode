package app.license;

import app.util.CryptoUtil;
import app.util.HWIDUtil;

public class LicencaService {
    
    public Licenca validar(String licencaCriptografada){
        
        String conteudo = CryptoUtil.decrypt(licencaCriptografada);
        String[] partes =  conteudo.split("\\|");

        if (partes.length != 2) {
            throw new LicencaException("Licença corrompida");
        }

        String hwidLicenca = partes[0];
        String cliente = partes[1];

        String hwidAtual = HWIDUtil.gerarHWID();
        
        if (!hwidLicenca.equals(hwidAtual)) {
            throw new LicencaException(
                "Licença não pertence a esta máquina.");
        }

        return new Licenca(hwidLicenca, cliente);
    }
}
