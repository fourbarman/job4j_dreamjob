package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateDBStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * CandidateService.
 * <p>
 * Service layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
@ThreadSafe
@Service
public class CandidateService {

    private final CandidateDBStore candidates;
    public CandidateService(CandidateDBStore candidateDBStore) {
        this.candidates = candidateDBStore;
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

    public Candidate findById(int id) {
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
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        return localDateTime.format(myFormat);
    }
}
