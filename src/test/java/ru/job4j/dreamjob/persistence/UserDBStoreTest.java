package ru.job4j.dreamjob.persistence;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDBStoreTest {
    @Test
    public void whenFindAllUsers() {
        UserDBStore cdb = new UserDBStore(new Main().loadPool());
        User user1 = cdb.add(new User(0, "user1@mail", "user1"));
        User user2 = cdb.add(new User(0, "user2@mail", "user2"));
        assertTrue(cdb.findAll().contains(user1));
        assertTrue(cdb.findAll().contains(user2));
    }

    @Test
    public void whenAddUser() {
        UserDBStore cdb = new UserDBStore(new Main().loadPool());
        User user3 = new User(0, "user3@mail", "user3");
        User user4 = cdb.add(user3);
        assertEquals(user3.getEmail(), user4.getEmail());
    }

    @Test
    public void whenFindById() {
        UserDBStore cdb = new UserDBStore(new Main().loadPool());
        User user5 = new User(0, "user5@mail", "user5");
        User added = cdb.add(user5);
        assertThat(cdb.findById(added.getId()).getEmail(), is(user5.getEmail()));
    }

    @Test
    public void whenUpdateUser() {
        UserDBStore cdb = new UserDBStore(new Main().loadPool());
        User user6 = new User(0, "user6@mail", "user6");
        User added = cdb.add(user6);
        User updateUser = new User(added.getId(), "addedUser@mail", "newpass");
        cdb.update(updateUser);
        assertThat(cdb.findById(updateUser.getId()).getEmail(), is(updateUser.getEmail()));
    }
}
