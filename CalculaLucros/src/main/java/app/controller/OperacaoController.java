package app.controller;

import app.model.Operacao;
import app.persistence.DataStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperacaoController {
    private final List<Operacao> lista = new ArrayList<>();

    public void adicionarOperacao(double dep, double saq, double bau){
        lista.add(new Operacao(dep, saq, bau));
    }

    // usado pelo TableModel
    public List<Operacao> getTodas(){
        return lista;
    }

    // === MÉTODOS QUE ESTAVAM FALTANDO ===

    // Alias de getTodas(), caso a View chame getLista()
    public List<Operacao> getLista() {
        return lista;
    }

    // usado para remover linha da tabela
    public void removerOperacao(int index) {
        if (index >= 0 && index < lista.size()) {
            lista.remove(index);
        }
    }

    // ====================================

    public double getTotalLucro(){
        return lista.stream().mapToDouble(Operacao::getLucro).sum();
    }

    public double getTotalBruto(){
        return lista.stream().mapToDouble(Operacao::getBruto).sum();
    }

    public void limpar(){
        lista.clear();
    }
}
