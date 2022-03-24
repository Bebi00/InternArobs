import javax.management.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        BasicConnection basicConnection =BasicConnection.getInstance("jdbc:mysql://127.0.0.1:3306/music-shop","Bebi","Bebi00",5);
        HikariConnection hikariConnection = new HikariConnection("jdbc:mysql://127.0.0.1:3306/music-shop","Bebi","Bebi00",5);

        Connection connection = basicConnection.getConnection();
        Statement statement =  connection.createStatement();
        String query = "SELECT * FROM artist";
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            int id = Integer.parseInt(resultSet.getString("artistId"));
            String stageName = resultSet.getString("artistLastName");
            System.out.println(id+". "+stageName);
        }


//        for (int i=0;i<10;i++) {
//            System.out.println("Connection "+i);
//            Connection connectionH = hikariConnection.getConnection();
//
//            Statement statementH = connectionH.createStatement();
//            String queryH = "SELECT * FROM artist";
//            ResultSet resultSetH = statementH.executeQuery(queryH);
//            while (resultSetH.next()) {
//                int id = Integer.parseInt(resultSetH.getString("artistId"));
//                String stageName = resultSetH.getString("artistLastName");
//                System.out.println(id + ". " + stageName);
//            }
//        }
        
    }
}
