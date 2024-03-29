import java.sql.*;

public class AddAndPrint {
    static Connection connection;
    public static void main(String[] args) {

        try {
            connection = connectDB("music-shop");
            Statement statement = connection.createStatement();
            String query="""
            CALL addAndPrint('Levitate',2*60+26,4,3)
            """;
            ResultSet resultSet =  statement.executeQuery(query);
            while (resultSet.next()){
                String songName = resultSet.getString("songName");
                System.out.println(songName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    static Connection connectDB(String DB) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/"+DB, "Bebi", "Bebi00");
        if (connection != null) {
            return connection;
        } else {
            System.out.println("Connection not working");
        }
        return null;
    }

}
