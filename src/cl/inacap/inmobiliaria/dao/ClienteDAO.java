/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dao;

import cl.inacap.inmobiliaria.dto.Cliente;
import cl.inacap.inmobiliaria.tools.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author Ita
 */

public class ClienteDAO extends Connector
{
    /**
     * Devuelve la lista completa de clientes en la base de datos
     * @return lista de clientes en Arraylist
     */
    public ArrayList<Cliente> getClientes()
    {
        ArrayList<Cliente> list = new ArrayList<>();
        
        try
        {
            this.connect();
            String sql="SELECT * FROM clientes" + " WHERE estado = 1";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                list.add(new Cliente( rs.getInt("idCliente"), rs.getBoolean("comprador"),
                    rs.getString("nombre"), rs.getString("apellidoPat"), rs.getString("apellidoMat"), rs.getString("run"),
                    rs.getString("fechaNac"), rs.getInt("sexo"), rs.getInt("ocupacion"), rs.getInt("nivelEscolar"), 
                    rs.getInt("estadoCivil"), rs.getString("comuna"), rs.getBoolean("viviendaPropia"),
                    rs.getInt("creditoBancario"), rs.getInt("subsidio"), rs.getInt("rentaBruta"), rs.getInt("porcFichaReg")
                    ));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getClientes(): " + ex.toString());
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
    
    /**
     * Añade un cliente a la base de datos
     * @param cliente instancia de la clase cliente
     */
    public void addCliente(Cliente cliente)
    {
        try
        {
            this.connect();

            String sql="INSERT INTO clientes"
                    + "(comprador, nombre,  apellidoPat, apellidoMat, run, fechaNac, ocupacion, nivelEscolar, sexo, estadoCivil,"
                    + " comuna, viviendaPropia, creditoBancario, subsidio, rentaBruta, porcFichaReg)"
                    + " VALUES (" + cliente.isComprador() + ", \"" 
                    + cliente.getNombre() + "\", \"" + cliente.getApellidoPat() + "\", \"" 
                    + cliente.getApellidoMat() + "\", \"" + cliente.getRun() + "\", '" 
                    + cliente.getFechaNac() + "', " + cliente.getOcupacion() + ", "  
                    + cliente.getNivelEscolar() + ", " + cliente.getSexo() + ", " 
                    + cliente.getEstadoCivil() + ", \"" + cliente.getComuna() + "\", "
                    + cliente.getViviendaPropia() + ", " + cliente.getCreditoBancario() + ", " 
                    + cliente.getSubsidio() + ", " + cliente.getRentaBruta() + ", " 
                    + cliente.getPorcFichaReg() + ")";
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::addCliente(Cliente cliente): " + ex.toString());
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
    
    public int getAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'clientes'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getAutoincrement(): " + ex.toString());
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
    
    public Cliente getCliente(int id)
    {
        Cliente result;
        
        try
        {
            this.connect();
            String sql="SELECT * FROM clientes WHERE idCliente = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            result = new Cliente( rs.getInt("idCliente"), rs.getBoolean("comprador"),
                    rs.getString("nombre"), rs.getString("apellidoPat"), rs.getString("apellidoMat"), rs.getString("run"),
                    rs.getString("fechaNac"), rs.getInt("sexo"), rs.getInt("ocupacion"), rs.getInt("nivelEscolar"), 
                    rs.getInt("estadoCivil"), rs.getString("comuna"), rs.getBoolean("viviendaPropia"),
                    rs.getInt("creditoBancario"), rs.getInt("subsidio"), rs.getInt("rentaBruta"), rs.getInt("porcFichaReg")
                    );
            /*
            public Cliente(int idCliente, boolean comprador, String nombre, String apellidoPat, String apellidoMat, String run, 
            String fechaNac, int sexo, int ocupacion, int nivelEscolar, int estadoCivil, 
            String comuna, boolean viviendaPropia, int creditoBancario, 
            int subsidio, int rentaBruta, int porcFichaReg)
    
            */
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getCliente(): " + ex.toString());
            System.out.println("CREANDO CLIENTE EN BLANCO..." + ex.toString());
            result = new Cliente();
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
    
    public String[] getListaSexo()
    {
        String[] list = new String[getSexoAutoincrement()];

        try
        {
            this.connect();
            String sql="SELECT descripcion FROM sexo";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            for(int i = 0; rs.next(); i++)
                list[i] = rs.getString("descripcion");
                
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getListaSexo(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getListaSexo():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public int getSexoAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'sexo'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getSexAutoincrement(): " + ex.toString());
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
    
    public String[] getListaOcupacion()
    {
        String[] list = new String[getOcupacionAutoincrement()];

        try
        {
            this.connect();
            String sql="SELECT descripcion FROM ocupacion";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            for(int i = 0; rs.next(); i++)
                list[i] = rs.getString("descripcion");
                
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getListaOcupacion(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getListaOcupacion():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public int getOcupacionAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'ocupacion'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getOcupacionAutoincrement(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getOcupacionAutoincrement():disconnect " + ex.toString());
            }
        }
        
        return res;
    }
    
    public String[] getListaNivelEscolar()
    {
        String[] list = new String[getNivelEscolarAutoincrement()];

        try
        {
            this.connect();
            String sql="SELECT descripcion FROM nivelEscolar";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            for(int i = 0; rs.next(); i++)
                list[i] = rs.getString("descripcion");
                
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getListaNivelEscolar(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getListaNivelEscolar():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public int getNivelEscolarAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'nivelEscolar'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getNivelEscolarAutoincrement(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getOcupacionAutoincrement():disconnect " + ex.toString());
            }
        }
        
        return res;
    }
    
    public String[] getListaEstadoCivil()
    {
        String[] list = new String[getEstadoCivilAutoincrement()];

        try
        {
            this.connect();
            String sql="SELECT descripcion FROM estadoCivil";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            for(int i = 0; rs.next(); i++)
                list[i] = rs.getString("descripcion");
                
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getEstadoCivilOcupacion(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getListaEstadoCivil():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public int getEstadoCivilAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'estadoCivil'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::getEstadoCivilAutoincrement(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error ClienteDAO::getEstadoCivilAutoincrement():disconnect " + ex.toString());
            }
        }
        
        return res;
    }
        
    public void updateCliente(Cliente cliente)
    {
        try
        {
            this.connect();

            String sql="UPDATE clientes SET "
                    + "comprador = " + cliente.isComprador() + ", nombre = \"" + cliente.getNombre() + "\", apellidoPat = \"" + cliente.getApellidoPat() + "\", "
                    + "apellidoMat = \"" + cliente.getApellidoMat() + "\", run = \"" + cliente.getRun() + "\", fechaNac = '" + cliente.getFechaNac() + "', sexo = " + cliente.getSexo() +", " 
                    + "ocupacion = " + cliente.getOcupacion() + ", nivelEscolar = " + cliente.getNivelEscolar() + ", estadoCivil = " + cliente.getEstadoCivil() + ", "
                    + "comuna = \"" + cliente.getComuna() + "\", viviendaPropia = " + cliente.getViviendaPropia() + ", creditoBancario = " + cliente.getCreditoBancario()+ ", "
                    + "subsidio = "  + cliente.getSubsidio() + ", rentaBruta = " + cliente.getRentaBruta() + ", porcFichaReg = " + cliente.getPorcFichaReg() + " "
                    + "WHERE idCliente = " + cliente.getIdCliente();
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::updateCliente(Cliente cliente): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error ClienteDAO::updateCliente(Cliente cliente):try:disconnect " + e.toString());
            }
        }
    }
    
    public void deleteCliente(int id)
    {
        try
        {
            this.connect();

            String sql="UPDATE clientes SET "
                    + "estado = " + false + " "
                    + "WHERE idCliente = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error ClienteDAO::deleteCliente(int id): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error ClienteDAO::deleteCliente(int id):try:disconnect " + e.toString());
            }
        }
    }
}

