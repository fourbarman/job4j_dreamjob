package ru.job4j.dreamjob.persistence;

import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * CandidateStore.
 * Singleton.
 * <p>
 * Persistence layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
public class CandidateStore {
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidates.put(candidate.getId(), candidate);
    }

    public Object findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate newCandidate) {
        candidates.replace(newCandidate.getId(), newCandidate);
    }
}
