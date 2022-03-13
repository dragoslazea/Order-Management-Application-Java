package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa pentru realizarea conexiunii la baza de date
 * @author Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @since Apr 03, 2017
 */
public class ConnectionFactory {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    /**
     * JDBC-ul pentru conectare
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Data Base URL
     */
    private static final String DBURL = "jdbc:mysql://localhost:3306/ordersdb";

    /**
     * USER
     */
    private static final String USER = "root";

    /**
     * Password
     */
    private static final String PASS = "bogdanel19";

    /**
     * ConnectionFactory Instance
     */
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor fara perametri
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru crearea conexiunii
     * @return conexiunea creata
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Getter pentru conexiunea creata
     * @return conexiunea creata
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * Metoda pentru inchiderea conexiunii
     * @param connection conexiunea care se doreste a fi inchisa
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }

    /**
     * Metoda pentru inchiderea unui statement de executat
     * @param statement statementul de inchis
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Metoda pentru inchiderea obiectului generat in urma executiei comenzii
     * @param resultSet obiectul ResultSet cde inchis
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}

