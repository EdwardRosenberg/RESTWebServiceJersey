package org.example.dao.impl;

import org.example.dao.PersonDao;
import org.example.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class PersonDaoImpl implements PersonDao {

    private Connection connection = null;

    @Autowired
    private DataSource dataSource;

    @Override
    public List<Person> getAll() {

        List<Person> people = new ArrayList<>();
        try {
            people = executeQuery("SELECT * FROM user");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return people;
    }

    @Override
    public List<Person> search(Person person) {
        List<Person> people = null;
        try {
            people = executeQuery("SELECT * FROM user WHERE username='" + person + "'");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return people;
    }

    @Override
    public void create(Person person) {

        try {
            executeUpdateQuery("Insert into person VALUES('" + person.getName() + "')");
            closeConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public Person get(long id) {
        return null;
    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(long id) {

    }

    public void executeUpdateQuery(String query) {
        Connection con;
        try {
            con = getConnection();
            Statement stmt = con.createStatement();
            stmt.execute(query);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public List<Person> executeQuery(String query) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<Person> als = convertPojoList(rs);
        closeConnection();
        return als;
    }

    private List<Person> convertPojoList(ResultSet rs) {
        List<Person> people = new ArrayList<Person>();
        try {
            while (rs.next()) {
                Person person = new Person(rs.getString("name"));
                people.add(person);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return people;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        connection = dataSource.getConnection();
        return connection;
    }

    private void closeConnection() throws SQLException {
        if (isConnectionOpen()) {
            connection.close();
            connection = null;
        }
    }

    private boolean isConnectionOpen() {
        return (connection != null);
    }

}
