
package projecte_6_cursos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class CursCompeticio extends CursosEsqui {
    
    private String carnet_federat;

    public CursCompeticio(String idCurs, String dni, String datacompra, int hores, double preu_final) {
        super(idCurs, dni, datacompra, hores, preu_final);
    }

    public CursCompeticio() {
    }

    public String getCarnet_federat() {
        return carnet_federat;
    }

    public void setCarnet_federat(String carnet_federat) {
        this.carnet_federat = carnet_federat;
    }  
    
    //metode per alquilar el curs segons els parametres passats
    @Override
    public void llogarCurs(Connection conexio, String idCurs, String dni, String datacompra, int hores, double descompte, double preu_final){ 
        
        try{
        
        String consulta = "INSERT INTO compra (dni, id_curs, data_compra, hores, preu_final) VALUES "
                + "'"+idCurs+"', "
                + "'"+dni+"', "
                + "'"+datacompra+"', "
                + "'"+hores+"', "
                + "'"+0+"', "
                + "'"+preu_final+"', "
                +")";
        
        Statement sentencia = conexio.createStatement();
        sentencia.executeUpdate(consulta);
        
        }catch(SQLException ex) {
             ex.printStackTrace();
        }
    }
}
