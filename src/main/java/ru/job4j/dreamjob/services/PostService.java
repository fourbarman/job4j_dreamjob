package ru.job4j.dreamjob.services;

import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * PostService.
 * Singleton.
 * <p>
 * Service layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
public class PostService {

    private static final PostService INST = new PostService();

    private final PostStore posts = new PostStore();

    private PostService() {
        posts.add(new Post(1, "Junior Java Job", "desc for junior", "01.03.2022"));
        posts.add(new Post(2, "Middle Java Job", "desc for middle", "02.02.2022"));
        posts.add(new Post(3, "Senior Java Job", "desc for senior", "03.03.2022"));
    }

    public static PostService instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.findAll();
    }

    public void add(Post post) {
        post.setCreated(getCurrentTime());
        posts.add(post);
    }

    public Post findById(int id) {
        return posts.findById(id);
    }

    public void update(Post newPost) {
        if (newPost != null) {
            newPost.setCreated(getCurrentTime());
            posts.update(newPost);
        }
    }

    public String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return localDateTime.format(myFormat);
    }
}
