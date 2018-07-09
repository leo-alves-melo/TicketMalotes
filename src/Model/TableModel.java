/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Business.DatabaseServices;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author leonardoalvesdemelo
 */
public class TableModel extends AbstractTableModel {
    
    DatabaseServices databaseServices;
    String[] columnsNames = {"Prefixo", "Nome da Agência", "Município", "Nome do Banco", "UF", "Malha", "Frequência", "Horário A", "Roteiro A", "Horário B", "Roteiro B", "Transportadora"};
    
    public TableModel(DatabaseServices databaseServices) {
        this.databaseServices = databaseServices;
    }

    @Override
    public int getRowCount() {
        System.out.println("AAAAAAAAAAAA");
        System.out.println(this.databaseServices.getDatabase().size());
        return this.databaseServices.getDatabase().size();
    }

    @Override
    public int getColumnCount() {
        return this.columnsNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        /* Devolve o nome que sera colocado na devida posicao */
        String retorno = this.databaseServices.getDatabase().get(rowIndex)[columnIndex];
        System.out.println(retorno);

        return retorno;
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnsNames[column];
    }

}
