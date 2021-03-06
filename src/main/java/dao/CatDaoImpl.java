package dao;

import entity.Cat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CatDaoImpl implements CatDao {
    Connection connection = JDBCPostgresConnection.getConnection();

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    /**
     * Returns Optional value from database specified by passed id
     *
     * @param id - id of an object to find
     * @return - return Cat if present else return null
     */
    @Override
    public Optional<Cat> find(String id) {
        String sql = "SELECT cat_id,price,breed,name,phone FROM cat JOIN seller ON seller.id=cat.seller_id WHERE cat.cat_id=?";
        int cats_id = 0, price = 0;
        String breed = "", seller_name = "", seller_phone = "";


        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);


            statement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cats_id = resultSet.getInt("cat_id");
                price = resultSet.getInt("price");
                breed = resultSet.getString("breed");
                seller_name = resultSet.getString("name");
                seller_phone = resultSet.getString("phone");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(new Cat(cats_id, price, breed, seller_name, seller_phone));
    }

    /**
     * Returns list of Cat objects from database
     * sorted by id in ascending order
     *
     * @return - List with Cat type inside
     */
    @Override
    public List<Cat> findAll() {
        List<Cat> cats = new ArrayList<>();
        String sql = "SELECT cat_id,price,breed,name,phone FROM cat JOIN seller ON seller.id=cat.seller_id";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collections.sort(cats);
        return cats;
    }

    /**
     * Saves passed object in the database
     *
     * @param cat - object to save
     * @return - return true if object saved,else false
     * @throws SQLException - possibly thrown exception
     * @throws ClassNotFoundException - possibly thrown exception
     */
    @Override
    public boolean save(Cat cat) throws SQLException, ClassNotFoundException {
        boolean rowInserted;
        String sql1 = "INSERT INTO seller (name, phone) VALUES ( ?, ?) RETURNING id";
        String sql2 = "INSERT INTO cat (price,breed,seller_id) VALUES ( ?, ?, ?)";


        PreparedStatement statement1 = connection.prepareStatement(sql1);
        statement1.setString(1, cat.getSeller_name());
        statement1.setString(2, cat.getSeller_phone());
        connection.setAutoCommit(false);
        ResultSet resultSet = statement1.executeQuery();
        int seller_id = 0;
        if (resultSet.next()) {
            seller_id = resultSet.getInt("id");
        }
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setInt(1, cat.getPrice());
        statement2.setString(2, cat.getBreed());
        statement2.setInt(3, seller_id);

        rowInserted = statement2.executeUpdate() > 0;
        connection.commit();
        return rowInserted;
    }

    /**
     * Update  passed object in database
     *
     * @param cat - object to update
     * @return - return true if object updated,else false
     * @throws SQLException - possibly thrown exception
     * @throws ClassNotFoundException - possibly thrown exception
     */
    @Override
    public boolean update(Cat cat) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        String sql = "UPDATE cat SET price = ?, breed = ? WHERE cat_id = ? RETURNING seller_id";
        String sql2 = "UPDATE seller SET name = ? ,phone = ? WHERE id= ?";


        connection.setAutoCommit(false);
        PreparedStatement statement1 = connection.prepareStatement(sql);

        statement1.setInt(1, cat.getPrice());
        statement1.setString(2, cat.getBreed());
        statement1.setInt(3, cat.getId());
        ResultSet resultSet = statement1.executeQuery();
        int seller_id = 0;
        if (resultSet.next()) {
            seller_id = resultSet.getInt("seller_id");
        }
        PreparedStatement statement2 = connection.prepareStatement(sql2);
        statement2.setString(1, cat.getSeller_name());
        statement2.setString(2, cat.getSeller_phone());
        statement2.setInt(3, seller_id);
        rowUpdated = statement2.executeUpdate() > 0;
        connection.commit();
        return rowUpdated;
    }

    /**
     * Delete object passed as argument
     *
     * @param cat - object to delete
     * @return - bool value, true if deleted, else false
     * @throws SQLException - possibly throws exception
     * @throws ClassNotFoundException - possibly thrown exception
     */
    @Override
    public boolean delete(Cat cat) throws SQLException, ClassNotFoundException {
        boolean rowUpdated;
        String sql = "DELETE FROM cat WHERE cat_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, cat.getId());
        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }


}
