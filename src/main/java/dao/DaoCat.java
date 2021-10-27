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
        }
        return null;
    }

    @Override
    public boolean save(Cat o) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Cat o) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Cat o) throws SQLException {
        return false;
    }
}
