package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.Operacao;

public class OperacaoTableModel extends AbstractTableModel {
    
    private final List<Operacao> lista;
    private final String[] colunas = {"Depósito","Saque","Baú","Bruto","Lucro"};

    public OperacaoTableModel(List<Operacao> lista){
        this.lista = lista;
    };
    @Override
    public int getRowCount(){
        return lista.size();
    }
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    @Override
    public String getColumnName(int column){
        return colunas[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1, 2, 3, 4 -> Double.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return col < 3; // editavel ate a coluna 3 que seria o bau
    }

    @Override
    public Object getValueAt(int row, int col){
        Operacao op = lista.get(row);

        return switch(col){
            case 0 -> op.getDeposito();
            case 1 -> op.getSaque();
            case 2 -> op.getBau();
            case 3 -> op.getBruto();
            case 4 -> op.getLucro();
            default -> null;
        };
    }

    
    @Override
    public void setValueAt(Object value, int row, int col){
        try {
            double v = Double.parseDouble(value.toString().replace(",", "."));
            Operacao op = lista.get(row);

            switch (col){
                case 0 -> op.setDeposito(v);
                case 1 -> op.setSaque(v);
                case 2 -> op.setBau(v);
            }

            fireTableRowsUpdated(row, row);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Operacao getOperacao(int row){
        return lista.get(row);
    }
}
