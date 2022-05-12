import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicConnection implements ConnectionPool {
    private static BasicConnection instance;
    private int maxSize;
    private List<Connection> availableList = new ArrayList<>(maxSize);
    private List<Connection> usedList = new ArrayList<>(maxSize);

    private BasicConnection(String database, String user, String password, int maxSize) throws SQLException {
        Connection connection;
        for (int i = 0; i < maxSize; i++) {
            connection = DriverManager.getConnection(database, user, password);
            if (connection != null) {
                availableList.add(connection);
            } else {
                System.out.println("Connection not working");
            }
        }
    }

    public static BasicConnection getInstance(String database, String user, String password, int maxSize) throws SQLException {
        if (instance == null) {
            instance = new BasicConnection(database, user, password, maxSize);
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (availableList.size() > 0) {
            Connection connection = availableList.remove(availableList.size() - 1);
            usedList.add(connection);
            return connection;
        }
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        availableList.add(connection);
        usedList.remove(connection);
    }
}
