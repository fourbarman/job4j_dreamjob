package ru.job4j.dreamjob.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

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
                    Candidate candidate = new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created"));
                    candidate.setPhoto(it.getBytes("photo"));
                    candidates.add(candidate);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        String query = "insert into candidates(name, description, created, photo) values (?, ?, ?, ?)";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query,
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBytes(4, candidate.getPhoto());
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
                .append("photo = ? ")
                .append("where id = ?;")
                .toString();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDesc());
            ps.setObject(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBytes(4, candidate.getPhoto());
            ps.setInt(5, candidate.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Candidate findById(int id) {
        String query = "select * from candidates c where c.id = ?";
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement(query)
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    Candidate candidate = new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getString("created")
                    );
                    candidate.setPhoto(it.getBytes("photo"));
                    return candidate;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
