package app.license;

import java.nio.file.Files;
import java.nio.file.Path;

public class LicencaRepository {

    private static final Path DIRETORIO =
        Path.of(System.getProperty("user.home"),
                "CalculaLucros");

    private static final Path ARQUIVO =
        DIRETORIO.resolve("license.key");

    public void salvar(String licenca) {
        try {
            if(!Files.exists(DIRETORIO)) {
                Files.createDirectories(DIRETORIO);
            }
            Files.writeString(ARQUIVO, licenca.trim());
        } catch (Exception e) {
            throw new LicencaException("Erro ao salvar a licença.");
        }
    }

    public String carregar() {
        try {
            if (!Files.exists(ARQUIVO)) return null;
            return Files.readString(ARQUIVO).trim();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existe() {
        return Files.exists(ARQUIVO);
    }   
}