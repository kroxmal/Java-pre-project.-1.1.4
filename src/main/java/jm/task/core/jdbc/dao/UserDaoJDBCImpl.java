package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Connection con;

    {
        try {
            con = Util.getMySQLConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute("create table if not exists User\n" +
                    "(\n" +
                    "    id       BIGINT PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name     VARCHAR(255),\n" +
                    "    lastName VARCHAR(255),\n" +
                    "    age TINYINT\n" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute("drop table if exists user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = con.createStatement()){
            statement.execute("INSERT User(name, lastName, age) \n" +
                    "VALUES ('" + name + "', '" + lastName + "', " + age + ");");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = con.createStatement()){
            statement.execute("DELETE FROM user\n" +
                    "WHERE id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try ( Statement statement = con.createStatement()){
          ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = con.createStatement()){
            statement.execute("TRUNCATE user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
