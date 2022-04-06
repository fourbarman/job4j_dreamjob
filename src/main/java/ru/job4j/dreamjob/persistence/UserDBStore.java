package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;

import ru.job4j.dreamjob.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDBStore {
    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<User> findAll() {
        String query = "select * from users;";
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    users.add(new User(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("email"),
                            it.getString("password")
                            )
                    );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public Optional<User> add(User user) {
        String query = "insert into users(email, name, password) values (?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception throwables) {
            return Optional.empty();
        }
        return Optional.of(user);
    }

    public void update(User user) {
        String query = "update users set name = ?, email = ?, password = ? where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User findById(int id) {
        String query = "select * from users where id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"));
                    return user;
                }
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Optional<User> findByEmailAndPwd(String email, String pwd) {
        String query = "select * from users where email = ? and password = ?";
        User user = null;
        try (Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, pwd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
}
