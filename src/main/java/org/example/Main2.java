package org.example;

import java.sql.*;

public class Main2 {
    public static void main(String[] args)  {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
        acceptConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void acceptConnection(Connection connection) throws SQLException {

        createTable(connection);
        insertDate(connection);


        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select id, firstname," +
                    "lastname, age from Students");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");
                System.out.println("id = " + id + ", Имя = " + firstname + ", Фамилия = "
                        + lastname + ", возраст = " + age + " лет");
            }
        }


    }

    private static void insertDate(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            int affectedRows = statement.executeUpdate("""
                    insert into Students(id,firstname, lastname, age) values
                    (1, 'Petr','Petrov', 17),
                    (2, 'Alex','Vasil`ev',18),
                    (3,'John','Petrenko',19),
                    (4, 'Victor','Belousov',18)

                        """);
            System.out.println("INSERT: affectedRows: = " + affectedRows);
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            statement.execute("""
                    create table Students (
                    id bigint,
                    firstname varchar(50),
                    lastname varchar(50),
                    age int
                    )
                    """);

        }
    }
}