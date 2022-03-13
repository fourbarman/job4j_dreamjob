package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "John", "apply for junior", "04.03.2022"));
        candidates.put(2, new Candidate(2, "Max", "apply for middle", "05.02.2022"));
        candidates.put(3, new Candidate(3, "George", "apply for senior", "06.03.2022"));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setCreated(getCurrentTime());
        this.candidates.put(candidate.getId(), candidate);
    }

    public Object findById(int id) {
        for (Candidate candidate: candidates.values()) {
            if (id == candidate.getId()) {
                return candidate;
            }
        }
        return null;
    }

    public void update(Candidate newCandidate) {
        for (Candidate candidate : candidates.values()) {
            if (candidate.getId() == newCandidate.getId()) {
                candidate.setName(newCandidate.getName());
                candidate.setDesc(newCandidate.getDesc());
                candidate.setCreated(getCurrentTime());
            }
        }
    }

    public String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return localDateTime.format(myFormat);
    }
}
