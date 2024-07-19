package org.candyfactory.dao;

import org.candyfactory.model.Supplier;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierDao  {
    private static final String URL = "jdbc:postgresql://localhost:5432/candy_factory";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "ytpkbvtyz";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Supplier> index() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Supplier";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));

                suppliers.add(supplier);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return suppliers;
    }

    public Supplier show(int id) {
        Supplier supplier = null;

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            supplier = new Supplier();

            supplier.setId(resultSet.getInt("id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setAddress(resultSet.getString("address"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return supplier;
    }
}
