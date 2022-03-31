package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        CityDBStore cities = new CityDBStore(new Main().loadPool());
        cities.add(new City(1, "testCity"));
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(111, "First", "desc", "2020-12-10 15:15:15", new City(1, "CityName"), true);
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenFindAll() {
        CityDBStore cities = new CityDBStore(new Main().loadPool());
        cities.add(new City(1, "testCity"));
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post1 = new Post(111, "Second", "desc", "2020-12-10 15:15:15", new City(1, "CityName"), true);
        Post post2 = new Post(111, "Second", "desc", "2020-12-10 15:15:15", new City(1, "CityName"), true);
        Post storedPost1 = store.add(post1);
        Post storedPost2 = store.add(post2);
        assertTrue(store.findAll().contains(storedPost1));
        assertTrue(store.findAll().contains(storedPost2));
    }

    @Test
    public void whenFindById() {
        CityDBStore cities = new CityDBStore(new Main().loadPool());
        cities.add(new City(1, "testCity"));
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post1 = new Post(111, "Second", "desc", "2020-12-10 15:15:15", new City(1, "CityName"), true);
        Post storedPost1 = store.add(post1);

    }
}
