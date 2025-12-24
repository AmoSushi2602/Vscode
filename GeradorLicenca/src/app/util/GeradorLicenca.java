package app.util;

public class GeradorLicenca {

    public static void main(String[] args){
        String hwid = "A9D2-4C88-91D0";
        String cliente = "Empresa XPTO";

        String licenca = CryptoUtil.encrypt(
            hwid + "|" + cliente
        );
        System.out.println(licenca);
    }
}