package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDBStore {

    private final BasicDataSource pool;

    public PostDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Post> findAll() {
        String query = new StringBuilder()
                .append("select p.id, p.name, description, created, visible, c.id, c.name from posts p")
                .append("join post_city pc on p.id = pc.post_id")
                .append("join cities c on pc.city_id = c.id;")
                .toString();
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
                PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created"),
                            new City(it.getInt("c.id"), it.getString("c.name")),
                            it.getBoolean("visible")
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    public Post add(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO posts(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public void update(Post post) {
        String query = new StringBuilder()
                .append("UPDATE posts ")
                .append("SET name = ?, ")
                .append("description = ?, ")
                .append("created = ?, ")
                .append("visible = ? ")
                .append("where id = ?;")
                .append("UPDATE post_city ")
                .append("SET city_id = ? ")
                .append("where post_id = ?;")
                .toString();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setObject(3, Timestamp.valueOf(post.getCreated()));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getId());
            ps.setInt(6, post.getCity().getId());
            ps.setInt(7, post.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post findById(int id) {
        String query = new StringBuilder()
                .append("select p.id, p.name, description, created, visible, c.id, c.name from posts p ")
                .append("join post_city pc on p.id = pc.post_id ")
                .append("join cities c on pc.city_id = c.id ")
                .append("where p.id = ?;")
                .toString();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created"),
                            new City(it.getInt("c.id"), it.getString("c.name")),
                            it.getBoolean("visible")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
