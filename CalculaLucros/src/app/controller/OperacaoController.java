package app.controller;

import app.model.Operacao;
import java.util.ArrayList;
import java.util.List;

public class OperacaoController {
    private final List<Operacao> lista = new ArrayList<>();

    public void adicionarOperacao(double dep, double saq, double bau){
        lista.add(new Operacao(dep, saq, bau));
    }

    public List<Operacao> getTodas(){
        return new ArrayList<>(lista);
    }
    
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
