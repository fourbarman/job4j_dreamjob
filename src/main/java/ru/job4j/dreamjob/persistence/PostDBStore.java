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
        String query = "select posts.id as pid, \n"
                + "posts.name as pname, \n"
                + "posts.description as pdesc, \n"
                + "created as pcreated, \n"
                + "visible, \n"
                + "city_id as cid, \n"
                + "cities.name as cname \n"
                + "from posts, cities \n"
                + "where posts.city_id = cities.id;";
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(
                            it.getInt("pid"),
                            it.getString("pname"),
                            it.getString("pdesc"),
                            it.getString("pcreated"),
                            new City(it.getInt("cid"), it.getString("cname")),
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
             PreparedStatement ps = cn.prepareStatement("insert into posts (name, description, created, city_id, visible) values (?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setObject(3, Timestamp.valueOf(post.getCreated()));
            ps.setInt(4, post.getCity().getId());
            ps.setBoolean(5, post.getVisible());

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

    /**
     * Update post.
     *
     * @param post Post.
     */
    public void update(Post post) {
        String query = new StringBuilder()
                .append("UPDATE posts ")
                .append("SET name = ?, ")
                .append("description = ?, ")
                .append("created = ?, ")
                .append("visible = ?, ")
                .append("city_id = ? ")
                .append("where id = ?;")
                .toString();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setObject(3, Timestamp.valueOf(post.getCreated()));
            ps.setBoolean(4, post.getVisible());
            ps.setInt(5, post.getCity().getId());
            ps.setInt(7, post.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post findById(int id) {
        String query = "select posts.id as pid, posts.name as pname, description, created, visible, city_id, cities.name as cname "
                + "from posts, cities where posts.id = ? and city_id = cities.id";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(
                            it.getInt("pid"),
                            it.getString("pname"),
                            it.getString("description"),
                            it.getString("created"),
                            new City(it.getInt("city_id"), it.getString("cname")),
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
