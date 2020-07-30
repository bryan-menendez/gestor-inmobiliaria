/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dao;

import cl.inacap.inmobiliaria.dto.Propiedad;
import cl.inacap.inmobiliaria.tools.Connector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 

/**
 *
 * @author Ita
 */
public class PropiedadDAO extends Connector
{
    /**
     * Constructor en blanco
     */
    public PropiedadDAO()
    {
        //blank constructor
    }
    
    /**
     * Devuelve una lista de propiedades, segun un criterio de busqueda
     * @param tipo refiere al tipo de propiedad que se quiere buscar. Referencia a tabla. -1 traera todas las propiedades 
     * @return list lista de propiedades
     */
    public ArrayList<Propiedad> getPropiedades(int tipo)
    {
        ArrayList<Propiedad> list = new ArrayList<>();
        String sql;
        
        //parseo de solicitud
        //si se quiere buscar un tipo de propiedad en especifico, se indica en el parametro tipo
        if (tipo != -1)
            sql="SELECT * FROM propiedades WHERE tipoPropiedad = " + tipo + " and estado = 1";
        //si el parametro es -1, hara un query a todas las propiedades
        else
            sql="SELECT * FROM propiedades";
        
        try
        {
            this.connect();
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                /*
                public Propiedad(int idPropiedad, String fechaPublicacion, boolean tipoPropiedad, int tamaño, int cantidadConstruido, String calle, 
            String poblacion, int sector, String comuna, int pisos, int baños, int dormitorios, boolean patioDel, boolean patioTras, 
            boolean estacionamiento, int tasacion,  String comentario)
    
                */
                    
                list.add(new Propiedad( rs.getInt("idPropiedad"), rs.getString("fechaPublicacion"),
                        rs.getBoolean("tipoPropiedad"), rs.getInt("tamaño"), rs.getInt("cantidadConstruido"),
                        rs.getString("calle"), rs.getString("poblacion"), 
                        rs.getInt("sector"), rs.getString("comuna"), rs.getInt("pisos"), rs.getInt("baños"), rs.getInt("dormitorios"),
                        rs.getBoolean("patioDel"), rs.getBoolean("patioTras"), rs.getBoolean("estacionamiento"),
                        rs.getInt("tasacion"), rs.getString("comentario")
                ));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error PropiedadDAO::getPropiedades(): " + ex.toString());
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
     * Añade una propiedad a la base de datos
     * @param propiedad instancia de la clase propiedad
    */
    public void addPropiedad(Propiedad propiedad)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        
        try
        {
            this.connect();
            String sql="INSERT INTO propiedades"
                    + "(fechaPublicacion, estadoVenta, tipoPropiedad, tamaño, cantidadConstruido, calle, poblacion, "
                    + "sector, comuna, tasacion, pisos, estacionamiento, baños, dormitorios, "
                    + "patiodel, patiotras, comentario)" 
                    + " VALUES ('" + dtf.format(LocalDateTime.now()) + "', " + false + ", "
                    + propiedad.getTipoPropiedad() + ", " + propiedad.getTamaño() + ", "
                    + propiedad.getCantidadConstruido() + ", \"" + propiedad.getCalle() + "\", \""
                    + propiedad.getPoblacion() + "\", "
                    + propiedad.getSector() + ", \"" + propiedad.getComuna() + "\", " 
                    + propiedad.getTasacion() + ", " + propiedad.getPisos() + ", "
                    + propiedad.isEstacionamiento() + ", " + propiedad.getBaños() + ", "
                    + propiedad.getDormitorios() + ", " + propiedad.isPatioDel() + ", "
                    + propiedad.isPatioTras() + ", \"" + propiedad.getComentario()
                    + "\")";
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }

        catch (Exception ex)
        {
           System.out.println("Error PropiedadDAO::addPropiedad(Propiedad propiedad): " + ex.toString());
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
    

    public Propiedad getPropiedad (int id)
    {
        Propiedad result;
        
        try
        {
            this.connect();
            String sql = "SELECT * FROM propiedades WHERE idPropiedad = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            result = new Propiedad(
                        rs.getInt("idPropiedad"), rs.getString("fechaPublicacion"), rs.getBoolean("tipoPropiedad"), rs.getInt("tamaño"), rs.getInt("cantidadConstruido"),
                        rs.getString("calle"), rs.getString("poblacion"), rs.getInt("sector"), rs.getString("comuna"),
                        rs.getInt("pisos"), rs.getInt("baños"), rs.getInt("dormitorios"),
                        rs.getBoolean("patioDel"), rs.getBoolean("patioTras"), rs.getBoolean("estacionamiento"), 
                        rs.getInt("tasacion"), rs.getString("comentario")
                );
        }
        catch (Exception ex)
        {
            System.out.println("Error PropiedadDAO::getPropiedad(): " + ex.toString());
            System.out.println("CREANDO PROPIEDAD EN BLANCO..." + ex.toString());
            result = new Propiedad();
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
    
    public int getAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'propiedades'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error PropiedadDAO::getAutoincrement(): " + ex.toString());
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
    
    public void updatePropiedad(Propiedad prop)
    {
        try
        {
            this.connect();
            String sql="UPDATE propiedades SET "
                    + "tipoPropiedad = " + prop.getTipoPropiedad() + ", tamaño = " + prop.getTamaño() + ", cantidadConstruido = " + prop.getCantidadConstruido() + ", "
                    + "calle = \"" + prop.getCalle() + "\", poblacion = \"" + prop.getPoblacion() + "\", sector = " + prop.getSector() + ", comuna = \"" + prop.getComuna() +"\", " 
                    + "pisos = " + prop.getPisos() + ", baños = " + prop.getBaños() + ", dormitorios = " + prop.getDormitorios() + ", "
                    + "patioDel = " + prop.isPatioDel() + ", patioTras = " + prop.isPatioTras() + ", estacionamiento = " + prop.isEstacionamiento() + ", "
                    + "tasacion = " + prop.getTasacion() + ", comentario = \"" + prop.getComentario() + "\" "
                    + "WHERE idPropiedad = " + prop.getIdPropiedad();
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error PropiedadDAO::updatePropiedad(Propiedad prop): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error PropiedadDAO::updatePropiedad(Propiedad prop):try:disconnect " + e.toString());
            }
        }
    }
    
    public void deletePropiedad(int id)
    {
        try
        {
            this.connect();

            String sql="UPDATE propiedades SET "
                    + "estado = " + false + " "
                    + "WHERE idPropiedad = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error PropiedadDAO::deletePropiedad(int id): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error PropiedadDAO::deletePropiedad(int id):try:disconnect " + e.toString());
            }
        }
    }
}
