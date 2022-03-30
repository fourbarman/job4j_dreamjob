package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CityDBStore {
    private final BasicDataSource pool;

    public CityDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<City> findAll() {
        String query = "select * from cities;";
        List<City> cities = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    cities.add(new City(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cities;
    }

    public City add(City city) {
        String query = "insert into cities(name) values (?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, city.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    city.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public void update(City city) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("update cities set name = ? where id = ?")) {
            ps.setString(1, city.getName());
            ps.setInt(2, city.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public City findById(int id) {
        String query = "select * from cities c where c.id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    City city = new City(it.getInt("id"), it.getString("name"));
                    return city;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
