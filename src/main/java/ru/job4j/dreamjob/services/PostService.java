package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

/**
 * PostService.
 * <p>
 * Service layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 14.03.2022.
 */
@ThreadSafe
@Service
public class PostService {

    private final PostStore posts;

    public PostService(PostStore postStore) {
        this.posts = postStore;
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
