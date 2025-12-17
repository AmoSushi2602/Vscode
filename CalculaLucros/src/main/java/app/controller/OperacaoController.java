package app.controller;

import java.time.LocalDate;
import java.util.List;

import app.model.Operacao;
import app.util.DataStore;

public class OperacaoController {
    private final DataStore store = new DataStore();
    private final List<Operacao> lista; 

    public OperacaoController() {
        //inicia o json
        this.lista = store.carregar();
    }

    //getter para view
    
    //tablemodel
    public List<Operacao> getTodas() {
        return lista;
    }

      public List<Operacao> getLista() {
        return lista;
    }

    //Operacao

    public void adicionarOperacao(double deposito, double saque, double bau) {
        Operacao op = new Operacao(LocalDate.now(), deposito, saque, bau);
        lista.add(op);
        store.salvar(lista); // salvar automaticamente
    }
    //REMOVER
    public void removerOperacao(int index) {
        if (index >= 0 && index < lista.size()) {
            lista.remove(index);
            store.salvar(lista);
        }
    }
    //limpar
    
    public void limpar() {
        lista.clear();
        store.salvar(lista);
    }

    // contas

    public double getTotalLucro(){
        return lista.stream().mapToDouble(Operacao::getLucro).sum();
    }

    public double getTotalBruto(){
        return lista.stream().mapToDouble(Operacao::getBruto).sum();
    }

    public List<Operacao> buscarPorData(LocalDate data) {
        return lista.stream()
                .filter(o -> o.getData().equals(data))
                .toList();
    }
}