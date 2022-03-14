package ru.job4j.dreamjob.services;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * CandidateService.
 * Singleton.
 * <p>
 * Service layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
public class CandidateService {
    private static final CandidateService INST = new CandidateService();

    private final CandidateStore candidates = new CandidateStore();

    private CandidateService() {
        candidates.add(new Candidate(1, "John", "apply for junior", "04.03.2022"));
        candidates.add(new Candidate(2, "Max", "apply for middle", "05.02.2022"));
        candidates.add(new Candidate(3, "George", "apply for senior", "06.03.2022"));
    }

    public static CandidateService instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.findAll();
    }

    public void add(Candidate newCandidate) {
        if (newCandidate != null) {
            newCandidate.setCreated(getCurrentTime());
            candidates.add(newCandidate);
        }
    }

    public Object findById(int id) {
        return candidates.findById(id);
    }

    public void update(Candidate newCandidate) {
        if (newCandidate != null) {
            newCandidate.setCreated(getCurrentTime());
            candidates.update(newCandidate);
        }
    }

    public String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return localDateTime.format(myFormat);
    }
}
