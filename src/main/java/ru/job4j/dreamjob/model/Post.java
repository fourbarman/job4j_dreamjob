package ru.job4j.dreamjob.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Post
 * model.
 */
public class Post implements Serializable {
    private int id;
    private String name;
    private String description;
    private String created;

    private City city;
    private boolean visible;

    public Post(int id, String name, String description, String created, City city) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String toString() {
        return id + " " + name + " " + description + " " + created + " " + city.getName();
    }
}
