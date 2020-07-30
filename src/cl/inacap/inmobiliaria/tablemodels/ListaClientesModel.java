/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.tablemodels;

import cl.inacap.inmobiliaria.dao.ClienteDAO;
import cl.inacap.inmobiliaria.dto.Cliente;
import javax.swing.table.AbstractTableModel; 
import java.util.ArrayList; 
/**
 *
 * @author Ita
 */
public class ListaClientesModel extends AbstractTableModel
{
    private String[] columnNames = {"ID","Comprador","Nombre", "Apellido Paterno", "Apellido Materno",
            "RUN", "Fecha de Nacimiento", "Sexo", "Ocupacion", "Nivel Escolar", "Estado Civil", "Comuna", "Vivienda Propia",
            "Credito Bancario", "Subsidio", "Renta Bruta", "Porcentaje en Ficha Registro Social"}; 
    private ArrayList<Cliente> list;
    private ClienteDAO clientes = new ClienteDAO();
    
    public ListaClientesModel (ArrayList<Cliente> list)
    {
        this.list = list;
    }
    
    @Override
    public int getRowCount()
    {
        return list.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) { 
      return columnNames[col]; 
   } 

    @Override
    public Object getValueAt(int row, int col)
    {        
        
        Object temp = null; 
        
        switch (col) {
            case 0:
                temp = list.get(row).getIdCliente();
                break;
            case 1:
                if(list.get(row).isComprador())
                    temp = "SI";
                else
                    temp = "NO";
                break;
            case 2:
                temp = list.get(row).getNombre();
                break;
            case 3:
                temp = list.get(row).getApellidoPat();
                break;
            case 4:
                temp = list.get(row).getApellidoMat();
                break;
            case 5:
                temp = list.get(row).getRun();
                break;
            case 6:
                temp = list.get(row).getFechaNac();
                break;
            case 7:
                String[] sexo = clientes.getListaSexo();
                temp = sexo[list.get(row).getSexo()];
                break;
            case 8:
                String[] oc = clientes.getListaOcupacion();
                temp = oc[list.get(row).getOcupacion()];
                break;
            case 9:
                String[] ne = clientes.getListaNivelEscolar();
                temp = ne[list.get(row).getNivelEscolar()];
                break;
            case 10:
                String[] ec = clientes.getListaEstadoCivil();
                temp = ec[list.get(row).getEstadoCivil()];
                break;
            case 11:
                temp = list.get(row).getComuna();
                break;
            case 12:
                if(!list.get(row).isComprador())
                {
                    temp = "N/A";
                    break;
                }
                if(list.get(row).getViviendaPropia())
                    temp = "SI";
                else
                    temp = "NO";
                break;
            case 13:
                temp = list.get(row).getCreditoBancario();
                break;
            case 14:
                temp = list.get(row).getSubsidio();
                break;
            case 15:
                temp = list.get(row).getRentaBruta();
                break;
            case 16: 
                temp = list.get(row).getPorcFichaReg();
                break;
            default:
                temp = "DEBUG";
                break;
        }
        
        return temp; 
    }
    
}
