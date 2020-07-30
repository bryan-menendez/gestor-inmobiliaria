/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dao;

import cl.inacap.inmobiliaria.dto.Venta;
import cl.inacap.inmobiliaria.tools.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ita
 */
public class VentaDAO extends Connector
{
    
    /**
     * Constructor en blanco
     */
    public VentaDAO()
    {
        
    }
    
    public ArrayList<Venta> getVentas(int idEmpleado, String fechaInic, String fechaTerm)
    {
        ArrayList<Venta> list = new ArrayList<>();
        String sql = "SELECT V.idVenta, V.fechaVenta, P.fechaPublicacion, P.idPropiedad, P.tipoPropiedad, V.valorVenta, P.tasacion," +
"        Comprador.idcliente AS idComprador, CONCAT(Comprador.nombre,\" \" ,Comprador.apellidoPat) AS nombreCOMPRADOR," +
"        Vendedor.idcliente AS idVendedor, CONCAT(Vendedor.nombre,\" \" ,Vendedor.apellidoPat) AS nombreVENDEDOR, " +
"        E.idEmpleado AS idEjecutivo, CONCAT(E.nombre,\" \" ,E.apellidoPat) AS nombreEJECUTIVO " +
"" +
"        FROM Ventas AS V INNER JOIN Clientes AS Vendedor " +
"        ON V.vendedor = Vendedor.idCliente " +
"        INNER JOIN Clientes AS Comprador " +
"        ON V.comprador = comprador.idCliente " +
"        INNER JOIN Propiedades AS P " +
"        ON P.idPropiedad = V.idPropiedad " +
"        INNER JOIN Empleados AS E " +
"        ON E.idEmpleado = V.ejecutivo";
        
        if (idEmpleado != 0 && fechaInic != null && fechaTerm != null)
        {
            sql+=" WHERE v.ejecutivo = " + idEmpleado + " AND v.fechaVenta > '" 
                + fechaInic + "' AND v.fechaVenta < '" + fechaTerm +"'";
        }
        else if (idEmpleado != 0)
        {
            sql+=" WHERE v.ejecutivo = " + idEmpleado;
        }
        else if (fechaInic != null && fechaTerm != null)
        {
            sql+=" WHERE v.fechaVenta > '" 
                + fechaInic + "' AND v.fechaVenta < '" + fechaTerm +" 23:59:59'";
        }
        else
        {
            
        }
        
        try
        {
            this.connect();
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
           
            while(rs.next())
            {
                list.add(new Venta(rs.getInt("idVenta"), rs.getString("fechaVenta"), rs.getString("fechaPublicacion"), 
                        rs.getInt("valorVenta"), rs.getInt("tasacion"),
                        rs.getInt("idPropiedad"), rs.getBoolean("tipoPropiedad"), 
                        rs.getInt("idComprador"), rs.getString("nombreComprador"),
                        rs.getInt("idVendedor"), rs.getString("nombreVendedor"),
                        rs.getInt("idEjecutivo"), rs.getString("nombreEjecutivo")
                ));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error VentaDAO::getVentas(): " + ex.toString());
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
            
    public void addVenta(Venta venta)
    {
        try
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String date = dtf.format(now);  
            
            this.connect();
            String sql="INSERT INTO ventas"
                    + "(fechaVenta, valorVenta, idPropiedad, comprador, vendedor, ejecutivo)" 
                    + " VALUES ('" + date + "', " 
                                    + venta.getValorVenta() + ", " 
                                    + venta.getIdPropiedad() + ", " + venta.getIdComprador() + ", "
                                    + venta.getIdVendedor() + ", " + venta.getIdEjecutivo() +
                                    ")";
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }

        catch (Exception ex)
        {
           System.out.println("Error VentaDAO::addVenta(Venta venta): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error VentaDAO::addVenta():disconnect " + ex.toString());
            }
        }
    }
    
    public int getAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'ventas'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error VentaDAO::getAutoincrement(): " + ex.toString());
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
        
        return res;
    }
    
}

