/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dao;

import cl.inacap.inmobiliaria.dto.Empleado;
import cl.inacap.inmobiliaria.dto.Propiedad;
import cl.inacap.inmobiliaria.tools.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Ita
 */
public class EmpleadoDAO extends Connector
{
    /**
     * Obtiene una lista de empleados segun criterio. 
     * El criterio refiere al nivel de permiso o tipo del empleado
     * 
     * 1.- Vendedor / Ejecutivo en ventas
     * 2.- Gerente
     * 3.- Administrador
     * 
     * Ingresar -1 devolvera todos los empleados
     * 
     * @param tipo tipo de empleado a buscar
     * @return 
     */
    public ArrayList<Empleado> getEmpleados(int tipo)
    {
        ArrayList<Empleado> list = new ArrayList<>();
        String sql;
        
         if (tipo != -1)
            sql="SELECT * FROM empleados WHERE tipoEmpleado = " + tipo + " and estado = 1";
        //si el parametro es -1, hara un query a todos los empleados
        else
            sql="SELECT * FROM empleados WHERE estado = 1";
        try
        {
            this.connect();
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                list.add(new Empleado(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getString("apellidoPat")
                                        , rs.getString("apellidoMat"), rs.getString("run"), rs.getInt("identificador")
                                        , rs.getInt("tipoEmpleado"), rs.getString("foto")));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::getEmpleados(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error:disconnect");
                ex.printStackTrace();
            }
        }
        
        return list;
    }
    
    public void addEmpleado(Empleado empleado)
    {
        try
        {
            this.connect();
            String sql="INSERT INTO empleados"
                    + "(nombre, apellidoPat, apellidoMat, run, tipoEmpleado, identificador, foto)" 
                    + " VALUES (\"" 
                    + empleado.getNombre() + "\", \"" + empleado.getApellidoPat() + "\", \""
                    + empleado.getApellidoMat() + "\", \"" + empleado.getRun() + "\", " 
                    + empleado.getTipoEmpleado() + ", " + empleado.getIdentificador() + ", \"" 
                    + empleado.getFoto() + "\")";
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }

        catch (Exception ex)
        {
           System.out.println("Error EmpleadoDAO::addEmpleado(Empleado empleado): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error:disconnect");
                ex.printStackTrace();
            }
        }
        
    }
    
    public int getEmpleadoAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'empleados'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::getEmpleadoAutoincrement(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error EmpleadoDAO::getEmpleadoAutoincrement():disconnect " + ex.toString());
            }
        }
        
        
        return res;
    }
    
    public String[] getListaTipoEmpleado()
    {
        String[] list = new String[getEmpleadoAutoincrement()];
        
        try
        {
            this.connect();
            String sql="SELECT descripcion FROM tipoEmpleado";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            for(int i = 0; rs.next(); i++)
                list[i] = rs.getString("descripcion");
                
        }
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::getListaTipoEmpleado(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error EmpleadoDAO::getListaTipoEmpleado():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public Empleado getEmpleado(int id)
    {
        Empleado result;
        
        try
        {
            this.connect();
            String sql="SELECT * FROM empleados WHERE idEmpleado = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            result = new Empleado(rs.getInt("idEmpleado"), rs.getString("nombre"), rs.getString("apellidoPat"),
                        rs.getString("apellidoMat"), rs.getString("run"), rs.getInt("identificador"), 
                        rs.getInt("tipoEmpleado"), rs.getString("foto"));
            /*
            public Empleado(int idEmpleado, String nombre, String apellidoPat, String apellidoMat, String run,
            int identificador, int tipoEmpleado, String foto)
            */
        }
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::getEmpleado(): " + ex.toString());
            System.out.println("CREANDO EMPLEADO EN BLANCO..." + ex.toString());
            result = new Empleado();
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error:disconnect");
                ex.printStackTrace();
            }
        }
        
        
        return result;
    }
    
    
    public void updateEmpleado(Empleado emp)
    {
        try
        {
            this.connect();
            /*
            public Empleado(int idEmpleado, String nombre, String apellidoPat, String apellidoMat, String run,
            int identificador, int tipoEmpleado, String foto)
            */
            String sql="UPDATE empleados SET "
                    + "nombre = \"" + emp.getNombre() + "\", apellidoPat = \"" + emp.getApellidoPat() + "\", apellidoMat = \"" + emp.getApellidoMat()
                    + "\", run = \"" + emp.getRun() + "\", identificador = " + emp.getIdentificador() + ", tipoEmpleado = " + emp.getTipoEmpleado() + ", foto = \"" + emp.getFoto()
                    + "\" WHERE idEmpleado = " + emp.getIdEmpleado();
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::updateEmpelado(Empleado emp): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error EmpleadoDAO::updateEmpelado(Empleado emp):try:disconnect " + e.toString());
            }
        }
    }
    
    public void deleteEmpleado(int id)
    {
        try
        {
            this.connect();

            String sql="UPDATE empleados SET "
                    + "estado = " + false + " "
                    + "WHERE idEmpleado = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error EmpleadoDAO::deleteEmpleado(int id): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error EmpleadoDAO::deleteEmpleado(int id):try:disconnect " + e.toString());
            }
        }
    }
}
