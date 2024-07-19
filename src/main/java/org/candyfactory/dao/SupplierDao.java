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

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Supplier> index() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM \"Supplier\"";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));

                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }

    public Supplier show(int id) {
        Supplier supplier = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"Supplier\" WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            supplier = new Supplier();
            supplier.setId(resultSet.getInt("id"));
            supplier.setName(resultSet.getString("name"));
            supplier.setAddress(resultSet.getString("address"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

    public void save(Supplier supplier) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO \"Supplier\" VALUES(2, ?, ?)");
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getAddress());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Supplier updatedSupplier) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "UPDATE \"Supplier\" SET name=?, address=? WHERE id=?");

            preparedStatement.setString(1, updatedSupplier.getName());
            preparedStatement.setString(2, updatedSupplier.getAddress());
            preparedStatement.setInt(3, updatedSupplier.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement =
                    connection.prepareStatement("DELETE FROM \"Supplier\" WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
