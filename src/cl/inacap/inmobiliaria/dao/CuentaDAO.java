/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dao;

import cl.inacap.inmobiliaria.dto.Cuenta;
import cl.inacap.inmobiliaria.dto.Empleado;
import cl.inacap.inmobiliaria.frames.Login;
import cl.inacap.inmobiliaria.tools.Connector;
import cl.inacap.inmobiliaria.tools.Hasher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author Ita
 */
public class CuentaDAO extends Connector
{
    /**
     * Constructor vacio
     */
    public CuentaDAO()
    {
        //blank 
    }
    
    /**
     * Revisa si ciertas credenciales existen en la base de datos y devuelve el valor referente al 
     * nivel de acceso o tipo de empleado. Devuelve 0 si la cuenta esta desactivada.
     * @param username nombre de usuario
     * @param password contraseña en texto plano
     * @return 
     */
    
    
    public ArrayList<Cuenta> getCuentas()
    {
        ArrayList<Cuenta> list = new ArrayList<>();
        
        try
        {
            this.connect();
            String sql="SELECT * FROM cuentas WHERE estado = " + true;
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                list.add(new Cuenta(rs.getInt("idCuenta"), rs.getString("username"), rs.getString("password"), rs.getString("fechaConexion"), 
                    rs.getInt("idEmpleado"), rs.getBoolean("estado")));
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::getCuentas(): " + ex.toString());
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
    
    public int checkCredentials(String username, String password)
    {
        String sql;
        ResultSet rs;
        sql="SELECT * FROM cuentas WHERE username = \"" + username + "\"";
        
        try
        {
            this.connect();
            PreparedStatement st= this.connection.prepareStatement(sql);
            rs = st.executeQuery();
            
            //si existe alguna cuenta con ese username
            if (rs.next())
            {
                //toma la contraseña y hasheala con la salt asociada a la cuenta
                String hashed = Hasher.encrypt_SHA_512(password, rs.getString("salt"));
                //y si ese hash es igual al que esta en la db, logeas
                if (hashed.equals(rs.getString("password")) )
                {
                    System.out.println("LOGGED IN AS " + username);
                    updateFechaCuenta(rs.getInt("idCuenta"));
                    
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
                    LocalDateTime now = LocalDateTime.now();  
                    String date = dtf.format(now);  
   
                    sql="UPDATE cuentas SET fechaConexion = '" + date
                    + "' WHERE idCuenta = " + rs.getInt("idCuenta");
                    st= this.connection.prepareStatement(sql);
                    st.execute();
                    
                    if (rs.getInt("estado") == 0)
                    {
                        System.out.println("CUENTA INHABILITADA");
                        return -1;
                    }
                    System.out.println("DETERMINIG PRIVILEGES...");
                    
                    try
                    {
                        //obteniendo el tipo de empleado asociado a la cuenta
                        ResultSet rs2;
                        sql="SELECT * FROM empleados WHERE idEmpleado = " + rs.getInt("idEmpleado") ;
                        PreparedStatement st2 = this.connection.prepareStatement(sql);
                        rs2 = st2.executeQuery();

                        //accedemos al indice obtenido
                        rs2.next();
                        System.out.println("PRIVILEGE: " + rs2.getInt("tipoEmpleado"));
                        
                        //HACK: USAR UN METODO PARA OBTENER LOS DATOS DE LA CUENTA
                        //Y PASARSELOS AQUI MISMO A TRAVES DE UN METODO

                        //SPAGETTI CODE COF COF
                        Login.setCuenta(new Cuenta(rs.getInt("idCuenta"), rs.getString("username"), rs.getString("password"),
                                                    rs.getString("fechaConexion"), rs.getInt("idEmpleado"), rs.getBoolean("estado")));
                        Login.setEmpleado(new Empleado(rs2.getInt("idEmpleado"), rs2.getString("nombre"), rs2.getString("apellidoPat"), rs2.getString("apellidoMat"), 
                                                        rs2.getString("run"), rs2.getInt("identificador"), rs2.getInt("tipoEmpleado"), rs2.getString("foto")));
                        //TODO
                        
                        return rs2.getInt("tipoEmpleado");
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Error determinando privilegios, " + ex.toString());
                        return 99;
                    }
                }
                
                //System.out.println("NOT THE SAME PASSWORD " + hashed + " vs " + rs.getString("password") );
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::checkCredentials(): " + ex.toString());
            return 99;
        }
        
        //no credentials, no window
        System.out.println("CREDENCIALES INVALIDAS");
        return 0;
    }
    
    public int addCuenta(String username, String password, int idEmpleado)
    {
        //detectar si el nombre de la cuenta esta en uso
        try
        {
            this.connect();
            String sql="SELECT * FROM cuentas WHERE username = \"" + username + "\"";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs= st.executeQuery();
            
            //si existe alguna cuenta con ese username
            //mensaje error 2, username ya existe
            if (rs.next())
            {
                System.out.println("Ya existe una cuenta con ese nombre");
                return 2;
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::addCuenta(Cuenta cuenta) (al detectar nombre de cuenta): " + ex.toString());
        }
        
        //creacion de cuenta
        try
        {
            this.connect();
            //get salt based on username
            String salt = Hasher.encrypt_SHA_512(username);
            //use that salt to encrypt the password
            String hashed = Hasher.encrypt_SHA_512(password, salt);
            String sql="INSERT INTO cuentas"
                    + "(username, password, salt, idEmpleado)" 
                    + " VALUES (\"" 
                    + username + "\", \"" + hashed + "\", \"" + salt + "\", " + idEmpleado
                    +")";
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
            
            System.out.println("CUENTA CREADA");
            //todo en orden
            return 1;
        }

        catch (Exception ex)
        {
           System.out.println("Error CuentaDAO::addCuenta(Cuenta cuenta):(al intentar crear cuenta) " + ex.toString());
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
        
        //error, no se pudo crear cuenta, salida por defecto en false
        return 0;
    }
    
    
    public int getCuentaAutoincrement()
    {
        int res = -1;
        
        try
        {
            this.connect();
            String sql="SELECT `AUTO_INCREMENT`\n" +
                        "FROM  INFORMATION_SCHEMA.TABLES\n" +
                        "WHERE TABLE_SCHEMA = 'inmobiliaria'\n" +
                        "AND   TABLE_NAME   = 'cuentas'";
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            res = rs.getInt(1);
        }
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::getCuentaAutoincrement(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error CuentaDAO::getCuentaAutoincrement():disconnect " + ex.toString());
            }
        }
        
        return res;
    }
    
    public String[] getListaTipoEmpleado()
    {
        String[] list = new String[getCuentaAutoincrement()];
        
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
            System.out.println("Error CuentaDAO::getListaTipoEmpleado(): " + ex.toString());
        }
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception ex)
            {
                System.out.println("Error CuentaDAO::getListaTipoEmpleado():disconnect " + ex.toString());
            }
        }
        
        return list;
    }
    
    public Cuenta getCuenta(int id)
    {
        Cuenta result;
        
        try
        {
            this.connect();
            String sql="SELECT * FROM cuentas WHERE idCuenta = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            rs.next();
            result = new Cuenta(rs.getInt("idCuenta"), rs.getString("username"), rs.getString("password"), rs.getString("fechaConexion"), 
                    rs.getInt("idEmpleado"), rs.getBoolean("estado"));
            /*
        public Cuenta(int idCuenta, String username, String password, String fechaConexion, int idEmpleado, int tipoEmpleado, boolean estado)
         */
        }
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::getCuenta(): " + ex.toString());
            System.out.println("CREANDO CUENTA EN BLANCO..." + ex.toString());
            result = new Cuenta();
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
    
    public void updateCuenta(int idCuenta, String username, String password, int idEmpleado)
    {
        try
        {
            this.connect();
            
            String salt = Hasher.encrypt_SHA_512(username);
            String hashed = Hasher.encrypt_SHA_512(password, salt);
            
            /*
                public Cuenta(int idCuenta, String username, String password, String fechaConexion, int idEmpleado, int tipoEmpleado, boolean estado)
            */
            
            String sql="UPDATE cuentas SET "
                    + "username = \"" + username + "\", password = \"" + hashed + "\", salt = \"" + salt
                    + "\", idEmpleado = " + idEmpleado 
                    + " WHERE idCuenta = " + idCuenta;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::updateCuenta(Cuenta acc): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error CuentaDAO::updateCuenta(Cuenta acc):try:disconnect " + e.toString());
            }
        }
    }
    
    public void updateFechaCuenta(int id)
    {
        try
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
            LocalDateTime now = LocalDateTime.now();  
            String date = dtf.format(now);  
   
            String sql="UPDATE cuentas SET fechaConexion = '" + date
                    + "' WHERE idCuenta = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::updateFechaCuenta(Cuenta acc): " + ex.toString());
        }
    }
    
    public void deleteCuenta(int id)
    {
        try
        {
            this.connect();

            String sql="UPDATE cuentas SET "
                    + "estado = " + false + " "
                    + "WHERE idCuenta = " + id;
            PreparedStatement st= this.connection.prepareStatement(sql);
            st.execute();
        }
        
        catch (Exception ex)
        {
            System.out.println("Error CuentaDAO::deleteCuenta(int id): " + ex.toString());
        }
        
        finally
        {
            try
            {
                this.disconnect();
            }
            catch(Exception e)
            {
                System.out.println("Error CuentaDAO::deleteCuenta(int id):try:disconnect " + e.toString());
            }
        }
    }
}
