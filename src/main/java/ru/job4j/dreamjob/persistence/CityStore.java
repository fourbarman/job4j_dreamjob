package ru.job4j.dreamjob.persistence;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CityStore.
 * <p>
 * Persistence layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 19.03.2022.
 */
@ThreadSafe
@Repository
public class CityStore {
    private final Map<Integer, City> cities = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger(4);

    private CityStore() {
        cities.putIfAbsent(1, new City(1, "Москва"));
        cities.putIfAbsent(2, new City(2, "Санкт-Петербург"));
        cities.putIfAbsent(3, new City(3, "Екатеринбург"));
    }

    public Collection<City> findAll() {
        return cities.values();
    }

    public void add(City city) {
        city.setId(ids.incrementAndGet());
        cities.putIfAbsent(city.getId(), city);
    }

    public City findById(int id) {
        return cities.get(id);
    }

    public void update(City city) {
        cities.replace(city.getId(), city);
    }
}
