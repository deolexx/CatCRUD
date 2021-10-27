package dao;

import entity.Cat;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DaoCat implements CatDao{

    private DaoCat() {
    }

    private static class SingleToneHelper {
        private static final DaoCat INSTANCE = new DaoCat();
    }
    public static DaoCat getInstance(){
        return SingleToneHelper.INSTANCE;
    }


    @Override
    public Optional<Cat> find(String id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Cat> findAll() throws SQLException {
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
