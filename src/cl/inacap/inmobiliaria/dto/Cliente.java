package cl.inacap.inmobiliaria.dto;

/**
 *
 * @author Ita
 */
public class Cliente extends Persona
{
    //datos generales
    private int idCliente = -1;
    private String fechaNac;
    private int sexo;
    private int ocupacion;
    private int nivelEscolar;
    private int estadoCivil;
    private String comuna;
    
    //datos comprador
    private boolean comprador;
    private boolean viviendaPropia;
    private int creditoBancario;
    private int subsidio;
    private int rentaBruta;
    private int porcFichaReg;
    
    /**
     * Constructor en blanco
     */
    public Cliente()
    {
        //blank constructor
    }
    
    /**
     * Constructor con parametros
     * 
     * @param idCliente id cliente en database
     * @param nombre nombre
     * @param apellidoPat apellido paterno
     * @param apellidoMat apellido materno
     * @param run run
     * @param fechaNac fecha de nacimiento, formato mysql YYYY-MM-DD
     * @param ocupacion ocupacion, referencia a tabla
     * @param nivelEscolar nivel escolar, referencia a tabla
     * @param sexo sexo, referencia a tabla
     * @param estadoCivil estado civil, referencia a tabla
     * @param comuna comuna
     * @param viviendaPropia vivienda propia, booleano, 0 - no posee
     * @param creditoBancario valor credito bancario
     * @param subsidio valor subsidio
     * @param rentaBruta valor renta bruta
     * @param porcFichaReg porcentaje del cliente en su ficha de registro social 
     */
    

    public Cliente(int idCliente, boolean comprador, String nombre, String apellidoPat, String apellidoMat, String run, 
            String fechaNac, int sexo, int ocupacion, int nivelEscolar, int estadoCivil, 
            String comuna, boolean viviendaPropia, int creditoBancario, 
            int subsidio, int rentaBruta, int porcFichaReg)
    {
        this.idCliente = idCliente;
        this.comprador = comprador;
        this.nombre = nombre;
        this.apellidoPat = apellidoPat;
        this.apellidoMat = apellidoMat;
        this.run = run;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.ocupacion = ocupacion;
        this.nivelEscolar = nivelEscolar;
        this.estadoCivil = estadoCivil;
        this.comuna = comuna;
        this.viviendaPropia = viviendaPropia;
        this.creditoBancario = creditoBancario;
        this.subsidio = subsidio;
        this.rentaBruta = rentaBruta;
        this.porcFichaReg = porcFichaReg;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public boolean isComprador()
    {
        return comprador;
    }

    public void setComprador(boolean comprador)
    {
        this.comprador = comprador;
    }
    
    public String getFechaNac()
    {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac)
    {
        this.fechaNac = fechaNac;
    }

    public int getOcupacion()
    {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion)
    {
        this.ocupacion = ocupacion;
    }

    public int getNivelEscolar()
    {
        return nivelEscolar;
    }

    public void setNivelEscolar(int nivelEscolar)
    {
        this.nivelEscolar = nivelEscolar;
    }

    public int getSexo()
    {
        return sexo;
    }

    public void setSexo(int sexo)
    {
        this.sexo = sexo;
    }

    public int getEstadoCivil()
    {
        return estadoCivil;
    }

    public void setEstadoCivil(int estadoCivil)
    {
        this.estadoCivil = estadoCivil;
    }

    public String getComuna()
    {
        return comuna;
    }

    public void setComuna(String comuna)
    {
        this.comuna = comuna;
    }

    public boolean getViviendaPropia()
    {
        return viviendaPropia;
    }

    public void setViviendaPropia(boolean viviendaPropia)
    {
        this.viviendaPropia = viviendaPropia;
    }

    public int getCreditoBancario()
    {
        return creditoBancario;
    }

    public void setCreditoBancario(int creditoBancario)
    {
        this.creditoBancario = creditoBancario;
    }

    public int getSubsidio()
    {
        return subsidio;
    }

    public void setSubsidio(int subsidio)
    {
        this.subsidio = subsidio;
    }

    public int getRentaBruta()
    {
        return rentaBruta;
    }

    public void setRentaBruta(int rentaBruta)
    {
        this.rentaBruta = rentaBruta;
    }

    public int getPorcFichaReg()
    {
        return porcFichaReg;
    }

    public void setPorcFichaReg(int porcFichaReg)
    {
        this.porcFichaReg = porcFichaReg;
    }
    
}
