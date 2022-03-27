package ru.job4j.dreamjob.persistence;


import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PostStore.
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
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    private PostStore() {
        posts.putIfAbsent(1,
                new Post(1,
                        "Junior Java Job",
                        "desc for junior",
                        "01.03.2022",
                        new City(1, "Город 1"),
                        true
                ));
        posts.putIfAbsent(2,
                new Post(2,
                        "Middle Java Job",
                        "desc for middle",
                        "02.02.2022",
                        new City(2, "Город 2"),
                        true
                ));
        posts.putIfAbsent(3,
                new Post(3,
                        "Senior Java Job",
                        "desc for senior",
                        "03.03.2022",
                        new City(3, "Город 3"),
                        true
                ));
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setId(ids.incrementAndGet());
        posts.putIfAbsent(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post newPost) {
        posts.replace(newPost.getId(), newPost);
    }
}