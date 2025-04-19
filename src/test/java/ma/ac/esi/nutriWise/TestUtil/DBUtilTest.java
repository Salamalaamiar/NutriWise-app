package ma.ac.esi.nutriWise.TestUtil;

import ma.ac.esi.nutriWise.util.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    // Teste si la connexion à la base de données est non nulle
    @Test
    void testConnectionNotNull() {
        // On tente d'obtenir une connexion à la base de données via DBUtil
        Connection connection = DBUtil.getConnection();

        // Vérifie que la connexion n'est pas nulle
        assertNotNull(connection, "La connexion ne doit pas être nulle");

        try {
            // Vérifie que la connexion est bien ouverte
            assertFalse(connection.isClosed(), "La connexion doit être ouverte");

            // Ferme la connexion après le test pour éviter toute fuite de connexion
            connection.close();
        } catch (Exception e) {
            // Si une exception survient, le test échoue
            fail("Exception pendant la vérification de la connexion : " + e.getMessage());
        }
    }
}
