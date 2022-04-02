package ru.job4j.dreamjob.persistence;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CityDBStoreTest {
    @Test
    public void whenFindAll() {
        CityDBStore cdb = new CityDBStore(new Main().loadPool());
        City city1 = cdb.add(new City(1, "City1"));
        City city2 = cdb.add(new City(2, "City2"));
        assertTrue(cdb.findAll().contains(city1));
        assertTrue(cdb.findAll().contains(city2));
    }

    @Test
    public void whenAddCity() {
        CityDBStore cdb = new CityDBStore(new Main().loadPool());
        City city3 = new City(1, "City3");
        Assert.assertThat(cdb.add(city3).getName(), is(city3.getName()));
    }

    @Test
    public void whenFindById() {
        CityDBStore cdb = new CityDBStore(new Main().loadPool());
        City city4 = new City(1, "City4");
        City added = cdb.add(city4);
        assertThat(cdb.findById(added.getId()).getName(), is(city4.getName()));
    }

    @Test
    public void whenUpdate() {
        CityDBStore cdb = new CityDBStore(new Main().loadPool());
        City city5 = new City(1, "City5");
        City added = cdb.add(city5);
        City updateCity = new City(added.getId(), "newCityName");
        cdb.update(updateCity);
        assertEquals(cdb.findById(added.getId()).getName(), updateCity.getName());
    }
}
