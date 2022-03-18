package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
@ThreadSafe
@Repository
public class CandidateStore {
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    private CandidateStore() {
        candidates.putIfAbsent(1, new Candidate(1, "John", "apply for junior", "04.03.2022"));
        candidates.putIfAbsent(2, new Candidate(2, "Max", "apply for middle", "05.02.2022"));
        candidates.putIfAbsent(3, new Candidate(3, "George", "apply for senior", "06.03.2022"));
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(ids.incrementAndGet());
        candidates.putIfAbsent(candidate.getId(), candidate);
    }

    public Object findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate newCandidate) {
        candidates.replace(newCandidate.getId(), newCandidate);
    }
}
