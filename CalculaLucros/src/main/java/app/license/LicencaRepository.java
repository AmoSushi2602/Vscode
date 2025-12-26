package app.license;

import java.nio.file.Files;
import java.nio.file.Path;

public class LicencaRepository {

    private static final Path ARQUIVO =
        Path.of(System.getProperty("user.home"),
                "CalculaLucros", "licenca.key");

    public String carregar() {
        try {
            if (!Files.exists(ARQUIVO)) return null;
            return Files.readString(ARQUIVO).trim();
        } catch (Exception e) {
            return null;
        }
    }
}