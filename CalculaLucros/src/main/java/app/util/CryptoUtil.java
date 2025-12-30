package app.util;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class CryptoUtil{
    private static final String ALGO = "AES";
    private static final String CHAVE = "LICENCA_AES_2025"; 

    private CryptoUtil() {}

    public static String encrypt(String texto) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec key = new SecretKeySpec(CHAVE.getBytes(), ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder()
                .encodeToString(cipher.doFinal(texto.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String decrypt (String texto){
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec key = new SecretKeySpec(CHAVE.getBytes(), ALGO);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(
                Base64.getDecoder().decode(texto)));
        } catch (Exception e) {
            throw new RuntimeException("Licença Inválida");
        }
    }
}