package ma.ac.esi.nutriWise.TestUtil;

import ma.ac.esi.nutriWise.util.DBUtil;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    @Test
    void testConnectionNotNull() {
        Connection connection = DBUtil.getConnection();
        assertNotNull(connection, "La connexion ne doit pas être nulle");

        try {
            assertFalse(connection.isClosed(), "La connexion doit être ouverte");
            connection.close(); // Toujours fermer après test
        } catch (Exception e) {
            fail("Exception pendant la vérification de la connexion : " + e.getMessage());
        }
    }
}

