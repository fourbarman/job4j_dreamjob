package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Post> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Post(1, "John", "apply for junior", "04.03.2022"));
        candidates.put(2, new Post(2, "Max", "apply for middle", "05.02.2022"));
        candidates.put(3, new Post(3, "George", "apply for senior", "06.03.2022"));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return candidates.values();
    }
}
