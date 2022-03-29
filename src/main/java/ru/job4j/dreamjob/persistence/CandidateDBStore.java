package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDBStore {

    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        String query = "select * from candidates;";
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
            PreparedStatement ps = cn.prepareStatement(query)) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created")
                    ));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        String query = "insert into candidates(name) values (?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public void update(Candidate candidate) {
        String query = new StringBuilder()
                .append("UPDATE candidates ")
                .append("SET name = ?, ")
                .append("description = ?, ")
                .append("created = ?, ")
                .append("where id = ?;")
                .toString();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setObject(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setInt(4, candidate.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        String query = "select * from candidates c where c.id = 'id'";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
