
package projecte_6_cursos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLOracle {
    
    public SQLOracle(){
        
    }
    
    public static Statement conn(){
        try{
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Connecting");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "aleu16R3");
            Statement statement = connection.createStatement();
            return statement;
        }catch (Exception e){
            System.out.println("Ha passat alguna exepcio");
            return null;
        }
    }   
}
