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
        return List.copyOf(lista);
    }

      public List<Operacao> getLista() {
        return lista;
    }

    //Operacao

    public void adicionarOperacao(LocalDate data, double deposito, double saque, double bau) {
        Operacao op = new Operacao(data, deposito, saque, bau);
        lista.add(op);
        store.salvar(lista); // salvar automaticamente
    }
    //REMOVER
    public void removerOperacao(Operacao op) {
        if (op != null) {
            lista.remove(op);
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
    //Lucro do dia selecionado
    public double getLucroPorData(LocalDate data) {
        if(data == null) return 0.0; 
        
        return lista.stream()
                .filter(o -> data.equals(o.getData()))
                .mapToDouble(Operacao::getLucro)
                .sum();
    }
    //lucro total dos dias
    public double getLucroTotal(){
        return getTotalLucro();
    }

    public List<Operacao> buscarPorData(LocalDate data) {
        if (data == null) return List.of();
        return lista.stream()
                .filter(o -> data.equals(o.getData()))
                .toList();
    }
}