package app.util;

public class GeradorLicenca {
    public static void main(String[] args) {

        String hwid = "953E-8351-4E6F";
        String cliente = "Empresa XPTO";

        String dados = hwid + "|" + cliente;

        String licenca = CryptoUtil.encrypt(dados);

        System.out.println("DADOS  : " + dados);
        System.out.println("LICENÇA: " + licenca);
        System.out.println("VOLTA  : " + CryptoUtil.decrypt(licenca));
    }
}
