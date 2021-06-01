
package projecte_6_cursos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public abstract class CursosEsqui {
    
    protected String idCurs;
    protected String NomCurs;
    protected double preu_hora;
    
    private String dni;
    private String datacompra;
    private int hores;
    private double preu_final;
    
    //declarem les observable list que utilitzarem
    public ObservableList<CursosEsqui> cursosdisponibles;
    public ObservableList<CursosEsqui> cursosllogats;
    
    //declaro una variable tipus CursosEsqui
    CursosEsqui curs;
    static Persona p;
    
    /*CONNEXIO BASE DE DADES*/
    Connection conexio = ConnexioMYSQL.getConnection();

    public CursosEsqui(String idCurs, String NomCurs, double preu_hora) {
        this.idCurs = idCurs;
        this.NomCurs = NomCurs;
        this.preu_hora = preu_hora;
    }

    public CursosEsqui(String idCurs, String dni, String datacompra, int hores, double preu_final) {
        this.idCurs = idCurs;
        this.dni = dni;
        this.datacompra = datacompra;
        this.hores = hores;
        this.preu_final = preu_final;
    }

    public CursosEsqui(String idCurs, String NomCurs) {
        this.idCurs = idCurs;
        this.NomCurs = NomCurs;
    }
    
    public CursosEsqui() {
    }

    public String getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(String idCurs) {
        this.idCurs = idCurs;
    }

    public String getNomCurs() {
        return NomCurs;
    }

    public void setNomCurs(String NomCurs) {
        this.NomCurs = NomCurs;
    }

    public double getPreu_hora() {
        return preu_hora;
    }

    public void setPreu_hora(double preu_hora) {
        this.preu_hora = preu_hora;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDatacompra() {
        return datacompra;
    }

    public void setDatacompra(String datacompra) {
        this.datacompra = datacompra;
    }

    public int getHores() {
        return hores;
    }

    public void setHores(int hores) {
        this.hores = hores;
    }

    public double getPreu_final() {
        return preu_final;
    }

    public void setPreu_final(double preu_final) {
        this.preu_final = preu_final;
    }    
    
    //metode que mostra els cursos disponibles de la classe pare que comparteix per herencia amb les filles
    public ObservableList<CursosEsqui> mostrar_cursos(Connection conexio){
        
        cursosdisponibles = FXCollections.observableArrayList();
        
        String id_curs;
        String nom_curs;
        double preu;
        String carnet;
        
        try{
            /*CONSULTA*/
            String consulta1 = "SELECT * FROM detalls_cursos";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta1);
            
            while(resultat.next()){
                id_curs = resultat.getString("id_curs");
                nom_curs = resultat.getString("nom");
                preu = resultat.getDouble("preu_hora");
                carnet = resultat.getString("carnet_federat");
                
                curs = new CursFamiliar (id_curs, nom_curs, preu, carnet);

                cursosdisponibles.add(curs);

            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return cursosdisponibles;   
    }
    
    //Metodo per sobrescriure a les classes filles
    public abstract void llogarCurs(Connection conexio, String idCurs, String dni, String datacompra, int hores, double descompte, double preu_final);
    
    //metode per mostrar els cursos llogats en el dia actual de la classe pare que comparteix per herencia amb les filles
    public ObservableList<CursosEsqui> cursos_comprats(Connection conexio){
        
        cursosllogats = FXCollections.observableArrayList();
        
        try{
            /*CONSULTA*/
            String consulta1 = "SELECT * FROM compra WHERE data_compra = '"+LocalDate.now()+"'";
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            ResultSet resultat = sentencia.executeQuery(consulta1);
            
            while(resultat.next()){
                String dni = resultat.getString("dni");
                String id_curs = resultat.getString("id_curs");
                String data = resultat.getString("data_compra");
                int hores = resultat.getInt("hores");
                int descompte = resultat.getInt("descompte");
                double preu = resultat.getDouble("preu_final");

                curs = new CursFamiliar (id_curs, dni, data, hores, descompte, preu);
                
                cursosllogats.add(curs);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return cursosllogats;   
    }  
    
    //metode per comprovar si el id del curs introduit es correcte
    public Boolean comprovarIdCurso(Connection conexio, String curso){
        
        Boolean com = true;
        String IdCurs=null;
        
        try{
        
        String consulta = "SELECT id_curs FROM cursos WHERE id_curs = '"+curso+"'";
        
        Statement sentencia = conexio.createStatement();
        ResultSet resultat = sentencia.executeQuery(consulta);
        
        if(resultat.next()){
           IdCurs = resultat.getString("id_curs"); 
        }
        
        if(IdCurs==null){
            com = false;
        }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
       return com; 
    }
    
    //metode per calcular el preu del curset tenint en compte totes les possibilitats
    public double getPreu(Connection conexio, String id, int hores, String dni){
        
        double preu_final = 0;
        p = new Persona();
        
        String curset = id.substring(0, 4);
        
        if(curset.equals("CFAM")){
            
            String carnet_familiar = p.carnetFamiliar(conexio, dni);
            
            if (carnet_familiar==null){
                try{
                    
                    Double preu = 0.0;
                    
                    String consulta1 = "SELECT preu_hora FROM cursos WHERE id_curs = '"+id+"'";

                    Statement sentencia1 = conexio.createStatement();
                    ResultSet resultat1 = sentencia1.executeQuery(consulta1);

                    while (resultat1.next()){
                        preu = resultat1.getDouble("preu_hora");  
                    }
                    
                    preu_final = PreuTotal (hores, preu);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }  
                
            }else   if(carnet_familiar.equals("CFAM2021")){
                        try{
                            Double preu = 0.0;
                            
                            String consulta1 = "SELECT preu_hora FROM cursos WHERE id_curs = '"+id+"'";

                            Statement sentencia1 = conexio.createStatement();
                            ResultSet resultat1 = sentencia1.executeQuery(consulta1);

                            while (resultat1.next()){
                                preu = resultat1.getDouble("preu_hora");  
                            }
                            
                            preu_final = PreuTotal (hores, preu);
                            
                            double descFam = 40;
                            preu_final = preu_final-((preu_final*descFam)/100);

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } 
                    }
                
        }else{
            
            String carnetFederat = p.carnetFederat(conexio, dni);
            String carnetCurs = cursosCompeticio(conexio,id);
            
            if(carnetFederat==null){
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("No té cap carnet federat associat al seu nom, no pot realitzar cursets de competició");
                alert.showAndWait();
                
            }else if (carnetFederat.equals(carnetCurs)){
                        try{
                            Double preu = 0.0;
                            
                            String consulta1 = "SELECT preu_hora FROM cursos WHERE id_curs = '"+id+"'";

                            Statement sentencia1 = conexio.createStatement();
                            ResultSet resultat1 = sentencia1.executeQuery(consulta1);

                            while (resultat1.next()){
                                preu = resultat1.getDouble("preu_hora");  
                            }

                            preu_final = PreuTotal (hores, preu);

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } 
                    }else if(!(carnetFederat.equals(carnetCurs))){
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText("El tipus de carnet federat que té no li permet realitzar aquest curs");
                        alert.showAndWait();
                        
                    }
        }
        return preu_final;
    }
    
    //metode per extreure el tipo de carnet que es necessita per un curs de competicio
    public String cursosCompeticio(Connection conexio, String id){
        
        String carnet = null;
       
        try{
        
            String consulta = "SELECT carnet_federat FROM competicio WHERE id_curs = '"+id+"'";

            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);

            if(resultat.next()){
                carnet = resultat.getString("carnet_federat");
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return carnet;
    }
    //Metode calcular preu hora amb descompte de les hores
    public double PreuTotal(int hora, double preu){
        
        double preu_total = 0;
        double descompte = 0;
        
        if(hora==1 || hora==2){
            descompte = 20;
        }else if(hora==3){
            descompte = 30;
        } else if(hora==7){
            descompte = 50;
        }
        
        double preuDes = (hora*preu); 
        preu_total = preuDes-((preuDes*descompte)/100);
        return preu_total;
    }
    
    
    public CursosEsqui cursmesllogat(String id){
        
        CursosEsqui cmllogat = new CursFamiliar("", "");
        
        try{
        
            String consulta = "SELECT * FROM cursos WHERE id_curs = '"+id+"'";

            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);

            if(resultat.next()){
                String idCurs = resultat.getString("id_curs");
                String nomCurs = resultat.getString("nom");
                
                cmllogat = new CursFamiliar (idCurs, nomCurs);
            }
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        
        return cmllogat;
    }
    
}
