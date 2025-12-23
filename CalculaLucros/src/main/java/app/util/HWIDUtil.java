package app.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HWIDUtil {

    private HWIDUtil() {}


    public static String gerarHWID(){
        try {
            String dados =
                System.getenv("COMPUTERNAME") +
                System.getProperty("user.name") +
                System.getProperty("os.name") +
                System.getProperty("os.arch") +
                System.getenv("PROCESSOR_IDENTIFIER");
            
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(dados.getBytes(StandardCharsets.UTF_8));
            
            return formatar(hash);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar HWID", e);
        }
    }
    private static String formatar(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < 6; i++) {
            sb.append(String.format("%02X", hash[i]));
            if (i % 2 == 1 && i < 5)sb.append("-");
        }

        return sb.toString();
    }
}
