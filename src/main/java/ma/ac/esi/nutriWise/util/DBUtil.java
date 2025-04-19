package ma.ac.esi.nutriWise.util;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;


public class DBUtil {

    private static final String  URL = "jdbc:postgresql://ep-sweet-meadow-abfcmsjy-pooler.eu-west-2.aws.neon.tech/nutriwise-db?sslmode=require";

    private static final String USER = "nutriwise-db_owner";
    private static final String PASSWORD = "npg_z3BU5pdeShJK";

    public static Connection getConnection() {
        try {
            
            
            return DriverManager.getConnection(URL, USER, PASSWORD);
      
           
       
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données !");
            e.printStackTrace();
        }
        return null;
    }

    
}