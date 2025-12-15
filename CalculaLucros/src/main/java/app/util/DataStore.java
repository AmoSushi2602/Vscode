package app.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.model.Operacao;

public class DataStore {
    private static final Path DIR = Paths.get("data");
    private static final Path FILE = DIR.resolve("dados.json");

    private final Gson gson;

    public DataStore(){
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }
    public List<Operacao> carregar(){
        try {
            if (Files.notExists(DIR)) Files.createDirectories(DIR);
            if (Files.notExists(FILE)){
                // cria json vazio
                salvar(Collections.emptyList());
                return new ArrayList<>();
            }
            String json = Files.readString(FILE);
            Operacao[] arr = gson.fromJson(json, Operacao[].class);
            if (arr == null) return new ArrayList<>();
            return new ArrayList<>(Arrays.asList(arr));
        
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void salvar(List<Operacao> lista){
        try {
            if (Files.notExists(DIR)) Files.createDirectories(DIR);
            String json = gson.toJson(lista);
            Files.writeString(FILE, json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
