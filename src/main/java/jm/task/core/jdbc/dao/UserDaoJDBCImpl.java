package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createUsers = "CREATE TABLE users (Id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";

        try {
            Connection connection = Util.connect();
            Statement statement = connection.createStatement();
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, "test", "users", null);
            rs.next();
            if (!rs.next()) {
                statement.executeUpdate(createUsers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String dropUsers = "DROP TABLE users";

        try {
            Connection connection = Util.connect();
            Statement statement = connection.createStatement();
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, "users", null);
            rs.next();
            if (rs.next()) {
                statement.executeUpdate(dropUsers);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUsers = ("INSERT INTO users VALUES (" + null + ',' + '\'' + name + '\'' + ',' + '\'' + lastName + '\'' + ',' + age + ')');

        try {
            Connection connection = Util.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(saveUsers);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUsers = ("DELETE FROM users  WHERE Id= " + id);

        try {
            Connection connection = Util.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(removeUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();

        try {
            Connection connection = Util.connect();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                User arcadii = new User();
                arcadii.setId(rs.getLong(1));
                arcadii.setName(rs.getString(2));
                arcadii.setLastName(rs.getString(3));
                arcadii.setAge(rs.getByte(4));
                result.add(arcadii);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void cleanUsersTable() {
        String cleanUsers = ("TRUNCATE users");

        try {
            Connection connection = Util.connect();
            Statement statement = connection.createStatement();
            statement.executeUpdate(cleanUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
