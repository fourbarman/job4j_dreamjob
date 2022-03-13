package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "desc for junior", "01.03.2022"));
        posts.put(2, new Post(2, "Middle Java Job", "desc for middle", "02.02.2022"));
        posts.put(3, new Post(3, "Senior Java Job", "desc for senior", "03.03.2022"));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        post.setCreated(getCurrentTime());
        this.posts.put(post.getId(), post);
    }

    public Post findById(int id) {
        for (Post post : posts.values()) {
            if (id == post.getId()) {
                return post;
            }
        }
        return null;
    }

    public void update(Post newPost) {
        for (Post post : posts.values()) {
            if (newPost.getId() == post.getId()) {
                post.setName(newPost.getName());
                post.setDescription(newPost.getDescription());
                post.setCreated(getCurrentTime());
            }
        }
    }

    public String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return localDateTime.format(myFormat);
    }
}
