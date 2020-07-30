/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.tablemodels;

import cl.inacap.inmobiliaria.dao.EmpleadoDAO;
import cl.inacap.inmobiliaria.dto.Empleado;
import javax.swing.table.AbstractTableModel; 
import java.util.ArrayList; 
/**
 *
 * @author Ita
 */
public class ListaEmpleadosModel extends AbstractTableModel
{
    private String[] columnNames = {"ID","Nombre", "Apellido Paterno", "Apellido Materno", "RUN", 
            "Identificador", "Tipo de empleado", "Ruta Fotografia"}; 
    private ArrayList<Empleado> list;
    private EmpleadoDAO empelados = new EmpleadoDAO();
    /*
    public Empleado(int idEmpleado, String nombre, String apellidoPat, String apellidoMat, String run,
            int identificador, int tipoEmpleado, String foto)
    
    */
    public ListaEmpleadosModel (ArrayList<Empleado> list)
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
        /*
        {"ID","Nombre", "Apellido Paterno", "Apellido Materno", "RUN", 
            "Identificador", "Tipo de empleado", "Ruta Fotografia"}; 
        */
        switch (col) {
            case 0:
                temp = list.get(row).getIdEmpleado();
                break;
            case 1:
                temp = list.get(row).getNombre();
                break;
            case 2:
                temp = list.get(row).getApellidoPat();
                break;
            case 3:
                temp = list.get(row).getApellidoMat();
                break;
            case 4:
                temp = list.get(row).getRun();
                break;
            case 5:
                temp = list.get(row).getIdentificador();
                break;
            case 6:
                    if (list.get(row).getTipoEmpleado() == 1)
                        temp = "Vendedor";
                    if (list.get(row).getTipoEmpleado() == 2)
                        temp = "Gerente";
                    if (list.get(row).getTipoEmpleado() == 3)
                        temp = "Administrador";
                break;
            case 7:
                temp = list.get(row).getFoto();
                break;
            default:
                temp = "DEBUG";
                break;
        }
        
        return temp; 
    }
    
}
