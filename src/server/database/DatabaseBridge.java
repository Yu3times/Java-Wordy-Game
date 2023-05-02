package server.database;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * The DatabaseBridge is a class meant to be used by the server in initiating a connection to the
 * MySQL database to be used for the Wordy backend. Everything that the server needs to
 * access in the database should be provided here using methods.
 */
public class DatabaseBridge {

    private Connection connection;
    //TODO: Implement the DatabaseBridge class.

    public DatabaseBridge() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/wordy", "root", "");
    }

}
