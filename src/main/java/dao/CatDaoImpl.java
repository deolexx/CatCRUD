package dao;

import entity.Cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatDaoImpl implements CatDao {


    private CatDaoImpl() {
    }

    private static class SingleToneHelper {
        private static final CatDaoImpl INSTANCE = new CatDaoImpl();
    }

    public static CatDaoImpl getInstance() {
        return SingleToneHelper.INSTANCE;
    }

    @Override
    public Optional<Cat> find(String id) throws SQLException {
        String sql = "SELECT cat_id,price,breed,name,phone FROM cat JOIN seller ON seller.id=cat.seller_id WHERE cat.cat_id=?";
        int cats_id = 0, price = 0;
        String breed = "", seller_name = "", seller_phone = "";

        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, Integer.parseInt(id));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            cats_id = resultSet.getInt("cat_id");
            price = resultSet.getInt("price");
            breed = resultSet.getString("breed");
            seller_name = resultSet.getString("name");
            seller_phone = resultSet.getString("phone");
        }
        return Optional.of(new Cat(cats_id, price, breed, seller_name, seller_phone));
    }

    @Override
    public List<Cat> findAll() throws SQLException {
        ArrayList<Cat> cats = new ArrayList<>();
        String sql = "SELECT * FROM cat JOIN seller ON seller.id=cat.seller_id";
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("cat_id");
            int price = resultSet.getInt("price");
            String breed = resultSet.getString("breed");
            String seller_name = resultSet.getString("name");
            String seller_phone = resultSet.getString("phone");
            Cat cat = new Cat(id, price, breed, seller_name, seller_phone);
            cats.add(cat);
        }
        return cats;
    }

    @Override
    public boolean save(Cat cat) throws SQLException {
        String sql1 = "INSERT INTO seller (name, phone) VALUES ( ?, ?) RETURNING id";
        String sql2 = "SELECT currval('seller_id_seq')";
        String sql3 = "INSERT INTO cat (price,breed,seller_id) VALUES ( ?, ?, ?)";
        boolean rowInserted = false;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement1 = connection.prepareStatement(sql1);
        //insert into seller
        statement1.setString(1, cat.getSeller_name());
        statement1.setString(2, cat.getSeller_phone());

        //get seller_id from seller
        // TODO: 28.10.2021 get erors here 
        PreparedStatement statement2 = connection1.prepareStatement(sql2);
        ResultSet resultSet = statement2.executeQuery();
        int id = resultSet.getInt("cat_id");

                //int id = resultSet.getInt("cat_id");
        System.out.println("id=:" + id);

        System.out.println("2");

        //insert into cat
        Connection connection3 = JDBCPostgresConnection.getConnection();
        PreparedStatement statement3 = connection3.prepareStatement(sql3);
        statement3.setInt(1, cat.getPrice());
        statement3.setString(2, cat.getBreed());
        statement3.setInt(3, id);
        rowInserted = statement3.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Cat cat) throws SQLException {
        String sql = "UPDATE cat SET price = ?, breed = ?";
        sql += " WHERE cat_id = ?";
        boolean rowUpdated;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat.getPrice());
        statement.setString(2, cat.getBreed());
        statement.setInt(3, cat.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(Cat cat) throws SQLException {
        String sql = "DELETE FROM cat WHERE cat_id = ?";
        boolean rowUpdated;
        Connection connection = JDBCPostgresConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }
}
