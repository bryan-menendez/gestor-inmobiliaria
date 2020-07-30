package cl.inacap.inmobiliaria.dto;

/**
 *
 * @author Ita
 */
public class Empleado extends Persona
{
    private int idEmpleado = -1;
    private int identificador;
    private int tipoEmpleado; //1 - vendedor, 2 - gerente, 3 - administrador
    private String foto;
    
    /**
     * Constructor en blanco
     */
    
    public Empleado()
    {
        //blank constructor
    }
    
    /**
     * Constructor con parametros
     * 
     * @param idEmpleado id del empleado en la db
     * @param nombre nombre
     * @param apellidoPat apellido paterno
     * @param apellidoMat apellido materno
     * @param run run
     * @param identificador identificador unico del trabajador
     * @param tipoEmpleado define los permisos del empleado; "1" es vendedor, "2" es gerente, "3" es administrador
     * @param foto ruta al archivo de imagen
     */

    public Empleado(int idEmpleado, String nombre, String apellidoPat, String apellidoMat, String run,
            int identificador, int tipoEmpleado, String foto)
    {
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.run = run;
        this.idEmpleado = idEmpleado;
        this.identificador = identificador;
        this.tipoEmpleado = tipoEmpleado;
        this.foto = foto;
    }
    
    
    
    public int getIdEmpleado()
    {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado)
    {
        this.idEmpleado = idEmpleado;
    }

    public int getIdentificador()
    {
        return identificador;
    }

    public void setIdentificador(int identificador)
    {
        this.identificador = identificador;
    }

    public int getTipoEmpleado()
    {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(int tipoEmpleado)
    {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }
}
