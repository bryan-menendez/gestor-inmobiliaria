/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.tablemodels;

import cl.inacap.inmobiliaria.dao.CuentaDAO;
import cl.inacap.inmobiliaria.dao.EmpleadoDAO;
import cl.inacap.inmobiliaria.dto.Cuenta;
import cl.inacap.inmobiliaria.dto.Empleado;
import javax.swing.table.AbstractTableModel; 
import java.util.ArrayList; 
/**
 *
 * @author Ita
 */
public class ListaCuentasModel extends AbstractTableModel
{
    private String[] columnNames = {"ID","Username", "Password", "Fecha ultima conexion", "ID Empleado", "Nombre empleado", "Tipo de empleado"}; 
    private ArrayList<Cuenta> list;
    private CuentaDAO cuentas = new CuentaDAO();
    private EmpleadoDAO empleados = new EmpleadoDAO();
    private Empleado emp = new Empleado();
    
    public ListaCuentasModel (ArrayList<Cuenta> list)
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
         
        this.emp = empleados.getEmpleado(list.get(row).getIdEmpleado());
        /*
        private String[] columnNames = {"ID","Username", "Password", "Fecha ultima conexion", "ID Empleado", "Nombre empleado", "Tipo de cuenta"}; 
        */        
        switch (col) {
            case 0:
                temp = list.get(row).getIdCuenta();
                break;
            case 1:
                temp = list.get(row).getUsername();
                break;
            case 2:
                temp = list.get(row).getPassword();
                break;
            case 3:
                temp = list.get(row).getFechaConexion();
                break;
            case 4:
                temp = list.get(row).getIdEmpleado();
                break;
            case 5:
                temp = emp.getNombre() + " " + emp.getApellidoPat() + " " + emp.getApellidoMat();
                break;
            case 6:
                    if (emp.getTipoEmpleado() == 1)
                        temp = "Vendedor";
                    if (emp.getTipoEmpleado() == 2)
                        temp = "Gerente";
                    if (emp.getTipoEmpleado() == 3)
                        temp = "Administrador";
                break;
            default:
                temp = "DEBUG";
                break;
        }
        
        return temp; 
    }
    
}
