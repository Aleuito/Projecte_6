
package projecte_6_cursos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CursFamiliar extends CursosEsqui {
    
    private double descompte;
    private String carnet;

    public CursFamiliar(String idCurs, String dni, String datacompra, int hores, double descompte, double preu_final) {
        super(idCurs, dni, datacompra, hores, preu_final);
        this.descompte = descompte;
    }

    public CursFamiliar(String idCurs, String NomCurs, double preu_hora, String carnet) {
        super(idCurs, NomCurs, preu_hora);
        this.carnet = carnet;
    }

    public CursFamiliar(String idCurs, String NomCurs) {
        super(idCurs, NomCurs);
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
    
    public CursFamiliar() {
    }

    public double getDescompte() {
        return descompte;
    }

    public void setDescompte(double descompte) {
        this.descompte = descompte;
    }
    
    //metode per alquilar el curs segons els parametres passats
    @Override
    public void llogarCurs(Connection conexio, String idCurs, String dni, String datacompra, int hores, double descompte, double preu_final){
        
        try{
        
        String consulta = "INSERT INTO compra (dni, id_curs, hores, descompte, preu_final, data_compra) VALUES ("
                + "'"+dni+"', "
                + "'"+idCurs+"', "
                + "'"+hores+"', "
                + "'"+descompte+"', "
                + "'"+preu_final+"', "
                + "'"+datacompra+"'"
                +")";
        
        Statement sentencia = conexio.createStatement();
        sentencia.executeUpdate(consulta);
        
        }catch(SQLException ex) {
             ex.printStackTrace();
        }
    }
    
}
