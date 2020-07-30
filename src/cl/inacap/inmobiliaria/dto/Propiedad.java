package cl.inacap.inmobiliaria.dto;

/**
 *
 * @author Ita
 */
public class Propiedad 
{
    private int idPropiedad;
    private String fechaPublicacion;
    private boolean estadoVenta; 
    private boolean tipoPropiedad; //0 - terreno, 1 - vivienda
    private int tamaño;
    private int cantidadConstruido;
    
    //datos direccion
    private String calle;
    private String poblacion;
    private int sector; //0 - centro, 1 - norte, 2 - sur, 3 - este, 4 - oeste 
    private String comuna;
    
    //datos vivienda
    private int tasacion;
    private int pisos;
    private boolean estacionamiento;
    private int baños;
    private int dormitorios;
    private boolean patioDel;
    private boolean patioTras;
    private String comentario;
    
    /**
    *Constructor vacio
    */
    
    public Propiedad()
    {
        //blank constructor
    }
    
    /**
     * Constructor con parametros
     * @param idPropiedad id de la propiedad en la base de datos
     * @param fechaPublicacion fecha de publicacion de la propiedad
     * @param tipoPropiedad tipo de propiedad, referencia a tabla
     * @param tamaño tamaño de la propiedad, en m2
     * @param cantidadConstruido cantidad construida de la propiedad, en m2
     * @param calle calle 
     * @param poblacion poblacion
     * @param sector sector, referencia a tabla
     * @param comuna comuna
     * @param tasacion valor tasacion
     * @param pisos numero de pisos
     * @param estacionamiento booleano, indica si la propiedad posee un estacionamiento o no
     * @param baños numero de baños
     * @param dormitorios numero de dormitorio 
     * @param patioDel booleano, indica si la propiedad posee un patio delantero o no
     * @param patioTras booleano, indica si la propiedad posee un patio trasero o no
     * @param comentario comentario introducido por el usuario
     */
    public Propiedad(int idPropiedad, String fechaPublicacion, boolean tipoPropiedad, int tamaño, int cantidadConstruido, String calle, 
            String poblacion, int sector, String comuna, int pisos, int baños, int dormitorios, boolean patioDel, boolean patioTras, 
            boolean estacionamiento, int tasacion,  String comentario)
    {
        this.idPropiedad = idPropiedad;
        this.fechaPublicacion = fechaPublicacion;
        this.tipoPropiedad = tipoPropiedad;
        this.tamaño = tamaño;
        this.cantidadConstruido = cantidadConstruido;
        this.calle = calle;
        this.poblacion = poblacion;
        this.sector = sector;
        this.comuna = comuna;
        this.tasacion = tasacion;
        this.pisos = pisos;
        this.estacionamiento = estacionamiento;
        this.baños = baños;
        this.dormitorios = dormitorios;
        this.patioDel = patioDel;
        this.patioTras = patioTras;
        this.comentario = comentario;
    }

    public int getIdPropiedad()
    {
        return idPropiedad;
    }

    public void setIdPropiedad(int idPropiedad)
    {
        this.idPropiedad = idPropiedad;
    }

    public String getFechaPublicacion()
    {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion)
    {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isEstadoVenta()
    {
        return estadoVenta;
    }

    public void setEstadoVenta(boolean estadoVenta)
    {
        this.estadoVenta = estadoVenta;
    }
    
    public boolean getTipoPropiedad()
    {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(boolean tipoPropiedad)
    {
        this.tipoPropiedad = tipoPropiedad;
    }

    public int getTamaño()
    {
        return tamaño;
    }

    public void setTamaño(int tamaño)
    {
        this.tamaño = tamaño;
    }

    public int getCantidadConstruido()
    {
        return cantidadConstruido;
    }

    public void setCantidadConstruido(int cantidadConstruido)
    {
        this.cantidadConstruido = cantidadConstruido;
    }

    public String getCalle()
    {
        return calle;
    }

    public void setCalle(String calle)
    {
        this.calle = calle;
    }

    public String getPoblacion()
    {
        return poblacion;
    }

    public void setPoblacion(String poblacion)
    {
        this.poblacion = poblacion;
    }

    public int getSector()
    {
        return sector;
    }

    public void setSector(int sector)
    {
        this.sector = sector;
    }

    public String getComuna()
    {
        return comuna;
    }

    public void setComuna(String comuna)
    {
        this.comuna = comuna;
    }

    public int getTasacion()
    {
        return tasacion;
    }

    public void setTasacion(int tasacion)
    {
        this.tasacion = tasacion;
    }

    public int getPisos()
    {
        return pisos;
    }

    public void setPisos(int pisos)
    {
        this.pisos = pisos;
    }

    public boolean isEstacionamiento()
    {
        return estacionamiento;
    }

    public void setEstacionamiento(boolean estacionamiento)
    {
        this.estacionamiento = estacionamiento;
    }

    public int getBaños()
    {
        return baños;
    }

    public void setBaños(int baños)
    {
        this.baños = baños;
    }

    public int getDormitorios()
    {
        return dormitorios;
    }

    public void setDormitorios(int dormitorios)
    {
        this.dormitorios = dormitorios;
    }

    public boolean isPatioDel()
    {
        return patioDel;
    }

    public void setPatioDel(boolean patioDel)
    {
        this.patioDel = patioDel;
    }

    public boolean isPatioTras()
    {
        return patioTras;
    }

    public void setPatioTras(boolean patioTras)
    {
        this.patioTras = patioTras;
    }

    public String getComentario()
    {
        return comentario;
    }

    public void setComentario(String comentario)
    {
        this.comentario = comentario;
    }
    
    
}
