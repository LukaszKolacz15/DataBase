import com.mysql.jdbc.Connection;

import java.sql.*;

/**
 * Created by Lukasz Kolacz on 08.04.2017.
 */

public class Mysql {

//    private static final String DB = "jdbc:mysql://adressIP:port/nazwabazy?kodowanie";
    private static final String DB = "jdbc:mysql://5.135.218.27:3306/spring?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "oskar";
    private static final String USERPW = "akademiakodu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";


    public static void main(String[] args) {
//          REFLEKSJA - przywołanie obiektu DRIVER
        try {
            Class.forName(DRIVER).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//          ŁĄCZENIE Z BAZĄ DANYCH
        try {
            Connection connection = (Connection) DriverManager.getConnection(DB, USER, USERPW);
            Statement statement = connection.createStatement();


            addUser(connection, "Moody", "s1ad123");
            addUser(connection, "Vanar", "asvs2sr567");

//          ----------------------------------------------------------------------------
//              Update

//            statement.executeUpdate("UPDATE user SET username = 'LukaszKol' WHERE id = 115");
//            statement.executeUpdate("UPDATE user SET role = 'user' WHERE id = 115");


//            --------------------------------------------------------------------------
//              Tworzenie nowego usera

//            statement.execute("INSERT INTO user(username, password, role, gender)" + "VALUES('lukaszK', 'haslo', 'admin', '0')");
//            statement.close();


//            -------------------------------------------------------------------------
//              Select

//            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
//            while (resultSet.next()){
//                System.out.println("------------------");
//                System.out.println("username: " + resultSet.getString("username"));
//                System.out.println("id: " + resultSet.getInt("id"));
//                System.out.println("------------------");
//            }
//            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Jeśli wyżej nie ma błędu, to znaczy że jest połączenie");

    }

    public static void addUser(Connection connection, String name, String password) throws SQLException {

        String sql = "INSERT INTO user(username, password, gender, role) VALUES(?, ?, '0', 'ADMIN')";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, password);

        statement.execute();

        statement.close();

        System.out.println("Dodałem użytkownika!");
    }
}
