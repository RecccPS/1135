package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;




public class UserServiceImpl implements UserService {
    @Override
    public void createUsersTable() {
        String createUsers = "CREATE TABLE users (Id INTEGER PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        Connection connection = Util.connect();
        try {
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

    @Override
    public void dropUsersTable() {
        String dropUsers = "DROP TABLE users";
        Connection connection = Util.connect();
        try {

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

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String saveUsers = ("INSERT INTO users VALUES (" + null + ',' + '\'' + name + '\'' + ',' + '\'' + lastName + '\'' + ',' + age + ')');
        Connection connection = Util.connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(saveUsers);
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUsers = ("DELETE FROM users  WHERE Id= " + id);
        Connection connection = Util.connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(removeUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> getAllUsers() {
        Connection connection = Util.connect();
        List<User> result = new ArrayList<>();

        try {
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

    @Override
    public void cleanUsersTable() {
        String cleanUsers = ("TRUNCATE users");
        Connection connection = Util.connect();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(cleanUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
