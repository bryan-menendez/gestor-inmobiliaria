/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.tablemodels;

import cl.inacap.inmobiliaria.dao.PropiedadDAO;
import cl.inacap.inmobiliaria.dto.Propiedad;
import javax.swing.table.AbstractTableModel; 
import java.util.ArrayList; 
/**
 *
 * @author Ita
 */
public class ListaPropiedadesModel extends AbstractTableModel
{
    private String[] columnNames = {"ID","Tipo de propiedad","Tamaño (m2)", "Cantidad construida (m2)", "Calle",
            "Problacion", "Sector", "Comuna", "Pisos", "Baños", "Dormitorios", "Patio Delantero", "Patio Trasero",
            "Estacionamiento", "Tasacion", "Comentario", "Fecha Publicacion"}; 
    private ArrayList<Propiedad> list;
    private PropiedadDAO propiedades = new PropiedadDAO();
    
    public ListaPropiedadesModel (ArrayList<Propiedad> list)
    {
        this.list = list;
    }
    
    @Override
    public int getRowCount()
    {
        return list.size();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) { 
      return columnNames[col]; 
   } 

    @Override
    public Object getValueAt(int row, int col)
    {        
        
        Object temp = null; 
        
        switch (col) {
            case 0:
                temp = list.get(row).getIdPropiedad();
                break;
            case 1:
                if(!list.get(row).getTipoPropiedad())
                    temp = "Terreno";
                else
                    temp = "Inmueble";
                break;
            case 2:
                temp = list.get(row).getTamaño();
                break;
            case 3:
                temp = list.get(row).getCantidadConstruido();
                break;
            case 4:
                temp = list.get(row).getCalle();
                break;
            case 5:
                temp = list.get(row).getPoblacion();
                break;
            case 6:
                int sec = list.get(row).getSector();
                
                if (sec == 0)
                    temp = "Norte";
                if (sec == 1)
                    temp = "Sur";
                if (sec == 2)
                    temp = "Centro";
                if (sec == 3)
                    temp = "Este";
                if (sec == 4)
                    temp = "Oeste";
                break;
            case 7:
                temp = list.get(row).getComuna();
                break;
            case 8:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                else
                    temp = list.get(row).getPisos();
                break;
            case 9:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                else
                    temp = list.get(row).getBaños();
                break;
            case 10:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                else
                    temp = list.get(row).getDormitorios();
                break;
            case 11:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                if(list.get(row).isPatioDel())
                    temp = "SI";
                else
                    temp = "NO";
                break;
            case 12:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                if(list.get(row).isPatioTras())
                    temp = "SI";
                else
                    temp = "NO";
                break;
            case 13:
                if(!list.get(row).getTipoPropiedad())
                {
                    temp = "N/A";
                    break;
                }
                if(list.get(row).isEstacionamiento())
                    temp = "SI";
                else
                    temp = "NO";
                break;
            case 14:
                temp = list.get(row).getTasacion();
                break;
            case 15:
                temp = list.get(row).getComentario();
                break;
            case 16:
                String d = list.get(row).getFechaPublicacion();
                temp = d;
                break;
            default:
                temp = "DEBUG";
                break;
        }
        
        return temp; 
    }
    
}
