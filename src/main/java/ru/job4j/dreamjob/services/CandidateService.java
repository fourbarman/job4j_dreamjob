package ru.job4j.dreamjob.services;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.persistence.CandidateStore;

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
@Service
public class CandidateService {

    private final CandidateStore candidates;
    public CandidateService(CandidateStore candidateStore) {
        this.candidates = candidateStore;
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
