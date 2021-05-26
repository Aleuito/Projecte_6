
package projecte_6_cursos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Persona {
    
    private String dni;
    private String nom;
    private String cognom;
    private String correu;
    private String contrasenya;
    private String telefon;
    private String carnet_federat;
    private String carnet_familiar;
    
    /*CONNEXIO BASE DE DADES*/
    Connection conexio = ConnexioMYSQL.getConnection();

    public Persona(String dni, String nom, String cognom, String correu, String telefon, String carnet_federat, String carnet_familiar) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
        this.correu = correu;
        this.telefon = telefon;
        this.carnet_federat = carnet_federat;
        this.carnet_familiar = carnet_familiar;
    }

    public Persona() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getCarnet_federat() {
        return carnet_federat;
    }

    public void setCarnet_federat(String carnet_federat) {
        this.carnet_federat = carnet_federat;
    }

    public String getCarnet_familiar() {
        return carnet_familiar;
    }

    public void setCarnet_familiar(String carnet_familiar) {
        this.carnet_familiar = carnet_familiar;
    }
    
    //metode per guardar en una observable list tots els clients registrats
    public ObservableList<Persona> getPersonas(Connection conexio){
        
        ObservableList<Persona> per = FXCollections.observableArrayList();
        
        try{
        
        String consulta = "SELECT * FROM clients";
        
        Statement sentencia = conexio.createStatement();
        ResultSet resultat = sentencia.executeQuery(consulta);
        
        while (resultat.next()){
            
            String dni = resultat.getString("dni");
            String nombre = resultat.getString("nom");
            String apellido = resultat.getString("cognom");
            String mail = resultat.getString("correu");
            String telefon = resultat.getString("telefon");
            String cfed = resultat.getString("carnet_federat");
            String cfam = resultat.getString("carnet_familiar");
            
            Persona p = new Persona (dni, nombre, apellido, mail, telefon, cfed, cfam);
            
            per.add(p);
        }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return per;
    }
    
    //metode per comprovar si el dni es troba a la base de dades o no
    public Boolean comprovarDni(Connection conexio, String dni){
        
        Boolean com = true;
        String nif = null;
        
        try{
        
        String consulta = "SELECT dni FROM clients WHERE dni = '"+dni+"'";
        
        Statement sentencia = conexio.createStatement();
        ResultSet resultat = sentencia.executeQuery(consulta);
        
        if(resultat.next()){
            nif = resultat.getString("dni");
        }
        
        if(nif==null){
            com = false;
        }
        
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
       return com; 
    }
    
    //metode per extreure quin tipus de carnet federat te aquella persona
    public String carnetFederat(Connection conexio, String dni){
        
        String carFed = null;
        
        try{
        
            String consulta = "SELECT carnet_federat FROM clients WHERE dni = '"+dni+"'";

            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);

            if(resultat.next()){
                carFed = resultat.getString("carnet_federat");
            }
            
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return carFed;
    }
    
    //metode per extreure si aquella persona te carnet familiaro no
    public String carnetFamiliar(Connection conexio, String dni){
        
        String carFam = null;
        
        try{
        
            String consulta = "SELECT carnet_familiar FROM clients WHERE dni = '"+dni+"'";

            Statement sentencia = conexio.createStatement();
            ResultSet resultat = sentencia.executeQuery(consulta);

            if(resultat.next()){
                carFam = resultat.getString("carnet_familiar");
            }
        
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        return carFam;
    }
}
