package ru.job4j.dreamjob.services;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.persistence.CityDBStore;
import ru.job4j.dreamjob.persistence.CityStore;

import java.util.ArrayList;
import java.util.List;

/**
 * CityService.
 * <p>
 * Service layer.
 *
 * @author fourbarman (maks.java@yandex.ru).
 * @version %I%, %G%.
 * @since 19.03.2022.
 */
@ThreadSafe
@Service
public class CityService {
    private final CityDBStore cities;

    public CityService(CityDBStore cities) {
        this.cities = cities;
    }

    public List<City> getAllCities() {
        return new ArrayList<>(cities.findAll());
    }

    public City findById(int id) {
        return cities.findById(id);
    }

    public void add(City city) {
        cities.add(city);
    }

    public void updateCity(City city) {
        cities.update(city);
    }
}
