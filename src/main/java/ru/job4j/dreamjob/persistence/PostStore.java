package ru.job4j.dreamjob.persistence;


import ru.job4j.dreamjob.model.Post;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
public class PostStore {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        this.posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post newPost) {
        posts.replace(newPost.getId(), newPost);
    }
}
