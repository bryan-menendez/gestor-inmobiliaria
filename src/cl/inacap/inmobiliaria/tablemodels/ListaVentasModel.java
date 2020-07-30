/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.tablemodels;

import cl.inacap.inmobiliaria.dao.ClienteDAO;
import cl.inacap.inmobiliaria.dto.Cliente;
import cl.inacap.inmobiliaria.dto.Venta;
import javax.swing.table.AbstractTableModel; 
import java.util.ArrayList; 
/**
 *
 * @author Ita
 */
public class ListaVentasModel extends AbstractTableModel
{
    private String[] columnNames = {"ID Venta", "Fecha de Venta", "Fecha de Publicacion", 
        "Valor Venta", "Tasacion",
        "ID Propiedad", "Tipo de Propiedad", 
        "ID Comprador", "Nombre Comprador", 
        "ID Vendedor", "Nombre Vendedor", "ID Ejecutivo", "Nombre Ejecutivo",
        }; 
    private ArrayList<Venta> list;
    
    public ListaVentasModel (ArrayList<Venta> list)
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
                temp = list.get(row).getIdVenta();
                break;
            case 1:
                temp = list.get(row).getFechaVenta();
                break;
            case 2:
                temp = list.get(row).getFechaPublicacion();
                break;
            case 3:
                temp = list.get(row).getValorVenta();
                break;
            case 4:
                temp = list.get(row).getTasacion();
                break;
            case 5:
                temp = list.get(row).getIdPropiedad();
                break;
            case 6:
                if(list.get(row).isTipoPropiedad())
                    temp = "Inmueble";
                else
                    temp = "Terreno";
                break;
            case 7:
                temp = list.get(row).getIdComprador();
                break;
            case 8:
                temp = list.get(row).getNombreComprador();
                break;
            case 9:
                temp = list.get(row).getIdVendedor();
                break;
            case 10:
                temp = list.get(row).getNombreVendedor();
                break;
            case 11:
                temp = list.get(row).getIdEjecutivo();
                break;
            case 12: 
                temp = list.get(row).getNombreEjecutivo();
                break;
            default:
                temp = "DEBUG";
                break;
        }
        
        return temp; 
    }
}
