
package projecte_6_cursos;

import java.sql.*;

public class ConnexioMYSQL {
    
    // Atributs de la connexió
        private static Connection conn = null;
        private String driver;
        private String url;
        private String usuario;
        private String password;

        // Constructor de la connexió per accedir als mètodes
    public ConnexioMYSQL(){

        String url = "jdbc:mysql://localhost:3306/equipament";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
    }
        // Mètode per realitzar la connexió
        public static Connection getConnection(){

            if (conn == null){
                new ConnexioMYSQL();
            }

            return conn;
        } // Fin getConnection
    
        
    public ResultSet consultaBD(String dades){
        
        ResultSet resultat = null;
        
        try{
            /*CONNEXIO BASE DE DADES*/
            ConnexioMYSQL conexion = new ConnexioMYSQL();
            Connection conexio = conexion.getConnection();
            /*CONSULTA*/
            String consulta = dades;
            /*CREEM SENTENCIA*/
            Statement sentencia = conexio.createStatement();
            /*EXECUTA CONSULTA*/
            resultat = sentencia.executeQuery(consulta);
            
        } catch (SQLException ex) {
                ex.printStackTrace();
        }
        
        return resultat;
    }
}
