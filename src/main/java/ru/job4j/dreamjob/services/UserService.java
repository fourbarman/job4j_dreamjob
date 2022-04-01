package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.persistence.UserDBStore;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Service
public class UserService {
    private final UserDBStore store;

    public UserService(UserDBStore store) {
        this.store = store;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(store.findAll());
    }

    public User findById(int id) {
        return this.store.findById(id);
    }

    public User add(User user) {
        return this.store.add(user);
    }

    public void updateUser(User user) {
        this.store.update(user);
    }
}
