package se.danielk;

import se.danielk.DaoPackage.CityDao;
import se.danielk.DaoPackage.CityDaoJDBC;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        CityDao cityDao = new CityDaoJDBC();
        City city = cityDao.findById(1);

        System.out.println(city);

        List<City> findByCode = cityDao.findByCode("SWE");
        findByCode.forEach(System.out::println);

        List<City> findByName = cityDao.findByName("Borås");
        findByName.forEach(System.out::println);

        List<City> allCities = cityDao.findAll();
        allCities.forEach(System.out::println);

        List<City> findAll = cityDao.findAll();
        findAll.forEach(System.out::println);

        City addCity = new City("Värmdö","JPN","Stockholm",50000);
        City newCity = cityDao.add(addCity);

        System.out.println(newCity);

        City updateCity = new City ("Värmdö","JPN","Stockholm",50000);
        City updatedCity = cityDao.update(updateCity);
        System.out.println(updatedCity);

        City deleteCity = new City("Värmdö","JPN","Stockholm",50000);
        int delete = cityDao.delete(deleteCity);
        System.out.println(delete);



        }
    }
