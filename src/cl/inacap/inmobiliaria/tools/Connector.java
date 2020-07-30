package cl.inacap.inmobiliaria.tools;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connector 
{
    private final String USER="admin";
    private final String DATABASE="inmobiliaria";
    private final String PASSWORD="admin";
    private final String SERVIDOR="localhost";
    protected Connection connection;
    
    public Connection getConnection(){
        return connection;
    }
    
    public boolean connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String str="jdbc:mysql://" + SERVIDOR + ":3306/" + DATABASE;
            this.connection=DriverManager.getConnection(str,USER,PASSWORD);
            return true;
        }catch(Exception ex){
            System.out.println("Error al conectar");
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public void disconnect(){
        try{
            this.connection.close();
        }catch(Exception ex){
            System.out.println("Error al desconectar");
            System.out.println(ex.toString());

        }
    }
}