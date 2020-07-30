package cl.inacap.inmobiliaria.dto;

/**
 * @author Ita
 */
public class Cuenta 
{
    private int idCuenta = -1;
    private String username;
    private String password;
    private String fechaConexion;
    private int idEmpleado; //FK
    private boolean estado; 
    
    public Cuenta()
    {
        //blank constructor
    }
    
    public Cuenta(int idCuenta, String username, String password, String fechaConexion, int idEmpleado, boolean estado)
    {
        this.idCuenta = idCuenta;
        this.username = username;
        this.password = password;
        this.fechaConexion = fechaConexion;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
    }
    
    
    
    public int getIdCuenta()
    {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta)
    {
        this.idCuenta = idCuenta;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFechaConexion()
    {
        return fechaConexion;
    }

    public void setFechaConexion(String fechaConexion)
    {
        this.fechaConexion = fechaConexion;
    }

    public int getIdEmpleado()
    {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado)
    {
        this.idEmpleado = idEmpleado;
    }

    public boolean isEstado()
    {
        return estado;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }
}
