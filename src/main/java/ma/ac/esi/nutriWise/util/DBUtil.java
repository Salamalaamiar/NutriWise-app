package ma.ac.esi.nutriWise.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour établir une connexion à la base de données PostgreSQL.
 * Cette classe utilise JDBC pour se connecter à une base distante hébergée sur Neon (cloud PostgreSQL).
 */
public class DBUtil {

    // URL de connexion à la base de données PostgreSQL avec SSL activé
    private static final String URL = "jdbc:postgresql://ep-sweet-meadow-abfcmsjy-pooler.eu-west-2.aws.neon.tech/nutriwise-db?sslmode=require";

    // Identifiant de l'utilisateur de la base
    private static final String USER = "nutriwise-db_owner";

    // Mot de passe de l'utilisateur de la base
    private static final String PASSWORD = "npg_z3BU5pdeShJK";

    /**
     * Méthode statique pour obtenir une connexion à la base de données.
     *
     * @return une instance Connection si la connexion est réussie, sinon null
     */
    public static Connection getConnection() {
        try {
            // Tentative d’établissement de la connexion avec les paramètres fournis
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            // Affichage d’un message d’erreur en cas d’échec de connexion
            System.err.println("Erreur lors de la connexion à la base de données !");
            e.printStackTrace(); // Affiche les détails de l’erreur pour le débogage
        }

        // Retourne null si la connexion a échoué
        return null;
    }
}
