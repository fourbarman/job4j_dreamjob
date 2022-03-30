package ru.job4j;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.persistence.PostDBStore;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostDBStoreTest {
    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(111, "Java Job", "desc", "2020-12-10 15:15:15", new City(1, "CityName"), true);
        System.out.println(post.toString());
        store.add(post);
//        System.out.println(post.toString());
//        Post postInDb = store.findById(post.getId());
//        assertThat(postInDb.getName(), is(post.getName()));
    }
}
