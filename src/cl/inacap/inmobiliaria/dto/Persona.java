package cl.inacap.inmobiliaria.dto;

/**
 *
 * @author Ita
 */
public abstract class Persona 
{
    protected String nombre;
    protected String apellidoPat;
    protected String apellidoMat;
    protected String run;

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidoPat()
    {
        return apellidoPat;
    }

    public void setApellidoPat(String apellidoPat)
    {
        this.apellidoPat = apellidoPat;
    }

    public String getApellidoMat()
    {
        return apellidoMat;
    }

    public void setApellidoMat(String apellidoMat)
    {
        this.apellidoMat = apellidoMat;
    }

    public String getRun()
    {
        return run;
    }

    public void setRun(String run)
    {
        this.run = run;
    }    
}
