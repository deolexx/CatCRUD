package dao;

import entity.Cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoCat implements CatDao {


    private DaoCat() {
    }

    private static class SingleToneHelper {
        private static final DaoCat INSTANCE = new DaoCat();
    }

    public static DaoCat getInstance() {
        return SingleToneHelper.INSTANCE;
    }

    @Override
    public Optional<Cat> find(String id) throws SQLException {
        String sql = "SELECT cat_id,price,breed,owner FROM cat WHERE id=?";
        int id_cat = 0, price = 0;
        String breed = "", seller = "";
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            id_cat = resultSet.getInt("cat_id");
            price = resultSet.getInt("age");
            breed = resultSet.getString("breed");
            seller = resultSet.getString("seller");
        }
        return Optional.of(new Cat(id_cat, price, breed, seller));
    }

    @Override
    public List<Cat> findAll() throws SQLException {
        ArrayList<Cat> cats = new ArrayList<>();
        String sql = "SELECT cat_id,price,breed,owner FROM cat";
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id_cat = resultSet.getInt("cat_id");
            int price = resultSet.getInt("age");
            String breed = resultSet.getString("breed");
            String seller = resultSet.getString("seller");
            Cat cat = new Cat(id_cat, price, breed, seller);
            cats.add(cat);
        }
        return cats;
    }

    @Override
    public boolean save(Cat cat) throws SQLException {
        String sql = "INSERT INTO cat (price,breed,owner) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat.getPrice());
        statement.setString(2, cat.getBreed());
        statement.setString(3, cat.getSeller());
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Cat cat) throws SQLException {
        String sql = "UPDATE cat SET price = ?, breed = ?, owner = ?";
        sql += " WHERE cat_id = ?";
        boolean rowUpdated;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat.getPrice());
        statement.setString(2, cat.getBreed());
        statement.setString(3, cat.getSeller());
        statement.setInt(4, cat.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(Cat cat) throws SQLException {
        String sql = "DELETE FROM cat WHERE cat_id = ?";
        boolean rowUpdated;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,cat.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }
}
