/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.dto;

/**
 *
 * @author Ita
 */
public class Venta 
{
    private int idVenta;
    private String fechaVenta;
    private String fechaPublicacion;
    private int valorVenta;
    private int tasacion;
    
    private int idPropiedad; //FK PROPIEDAD
    private boolean tipoPropiedad;
    
    private int idComprador; //FK CLIENTE
    private String nombreComprador;
    
    private int idVendedor; //FK CLIENTE
    private String nombreVendedor;
    
    private int idEjecutivo; //FK EMPLEADO
    private String nombreEjecutivo;
    /*
    private String[] columnNames = {"ID Venta", "Fecha de Venta", "Fecha de Publicacion", 
        "Valor Venta", "Tasacion",    
        "ID Propiedad", "Tipo de Propiedad", 
        "ID Comprador", "Nombre Comprador", 
        "ID Vendedor", "Nombre Vendedor", "ID Ejecutivo", "Nombre Ejecutivo",
        }; 
    */
    /**
     * Constructor vacio
     */
    public Venta()
    {
        
    }

    public Venta(int idVenta, String fechaVenta,  
            int valorVenta, int idPropiedad, 
            int comprador, int vendedor, int ejecutivo)
    {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.valorVenta = valorVenta;
        this.idPropiedad = idPropiedad;
        this.idComprador = comprador;
        this.idVendedor = vendedor;
        this.idEjecutivo = ejecutivo;
    }

    public Venta(int idVenta, String fechaVenta, String fechaPublicacion, 
            int valorVenta, int tasacion, 
            int idPropiedad, boolean tipoPropiedad, 
            int idComprador, String nombreComprador, 
            int idVendedor, String nombreVendedor, 
            int idEjecutivo, String nombreEjecutivo)
    {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.fechaPublicacion = fechaPublicacion;
        this.valorVenta = valorVenta;
        this.tasacion = tasacion;
        this.idPropiedad = idPropiedad;
        this.tipoPropiedad = tipoPropiedad;
        this.idComprador = idComprador;
        this.nombreComprador = nombreComprador;
        this.idVendedor = idVendedor;
        this.nombreVendedor = nombreVendedor;
        this.idEjecutivo = idEjecutivo;
        this.nombreEjecutivo = nombreEjecutivo;
    }

    
    
    public String getFechaPublicacion()
    {
        return fechaPublicacion;
    }

    public int getTasacion()
    {
        return tasacion;
    }

    public boolean isTipoPropiedad()
    {
        return tipoPropiedad;
    }

    public String getNombreComprador()
    {
        return nombreComprador;
    }

    public String getNombreVendedor()
    {
        return nombreVendedor;
    }

    public String getNombreEjecutivo()
    {
        return nombreEjecutivo;
    }

    
    
    public int getIdVenta()
    {
        return idVenta;
    }

    public void setIdVenta(int idVenta)
    {
        this.idVenta = idVenta;
    }

    public String getFechaVenta()
    {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta)
    {
        this.fechaVenta = fechaVenta;
    }


    public int getValorVenta()
    {
        return valorVenta;
    }

    public void setValorVenta(int valorVenta)
    {
        this.valorVenta = valorVenta;
    }

    public int getIdPropiedad()
    {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad)
    {
        this.idPropiedad = idPropiedad;
    }

    public int getIdComprador()
    {
        return idComprador;
    }

    public void setIdComprador(int idComprador)
    {
        this.idComprador = idComprador;
    }

    public int getIdVendedor()
    {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor)
    {
        this.idVendedor = idVendedor;
    }

    public int getIdEjecutivo()
    {
        return idEjecutivo;
    }

    public void setIdEjecutivo(int idEjecutivo)
    {
        this.idEjecutivo = idEjecutivo;
    }
    
}
