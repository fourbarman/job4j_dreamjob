package ru.job4j.dreamjob.controller;

import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.services.CityService;
import ru.job4j.dreamjob.services.PostService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

/**
 * PostController test.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 11.05.2022.
 */
public class PostControllerTest {
    /**
     * Test posts.
     */
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post1", "New post1 desc", "2021-11-11 11:11:11", new City(1, "CityName1"), true),
                new Post(2, "New post2", "New post2 desc", "2022-12-12 12:12:12", new City(2, "CityName2"), true)
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.posts(model, new MockHttpSession());
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    /**
     * Test createPost.
     */
    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "New post1", "New post1 desc", "2021-11-11 11:11:11", new City(1, "CityName1"), true);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.createPost(input, 1);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    /**
     * Test addPost.
     */
    @Test
    public void whenAddPost() {
        List<City> cities = Arrays.asList(
                new City(1, "CityName1"),
                new City(2, "CityName2")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.addPost(model, new MockHttpSession());
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("addPost"));
    }

    /**
     * Test savePost.
     */
    @Test
    public void whenSavePost() {
        Post input = new Post(1, "New post1", "New post1 desc", "2021-11-11 11:11:11", new City(1, "CityName1"), true);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.savePost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    /**
     * Test updatePost.
     */
    @Test
    public void whenUpdatePost() {
        Post input = new Post(1, "New post1", "New post1 desc", "2021-11-11 11:11:11", new City(1, "CityName1"), true);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        String page = postController.updatePost(input);
        verify(postService).update(input);
        assertThat(page, is("redirect:/posts"));
    }

    /**
     * Test formUpdatePost.
     */
    @Test
    public void whenFormUpdatePost() {
        Post input = new Post(1, "New post1", "New post1 desc", "2021-11-11 11:11:11", new City(1, "CityName1"), true);
        int id = input.getId();
        List<City> cities = Arrays.asList(
                new City(1, "CityName1"),
                new City(2, "CityName2")
        );
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService,
                cityService
        );
        when(postService.findById(id)).thenReturn(input);
        when(cityService.getAllCities()).thenReturn(cities);
        String page = postController.formUpdatePost(model, id);
        verify(model).addAttribute("post", input);
        verify(model).addAttribute("cities", cities);
        assertThat(page, is("updatePost"));
    }
}
