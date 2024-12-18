package se.danielk.DaoPackage;

import se.danielk.City;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CityDaoJDBC implements CityDao {

    Connection connection;
    {

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/world",
                    "root",
                    "0611");
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public City findById(int id) {
        String query = "SELECT * FROM city WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        String query = "SELECT * FROM city WHERE CountryCode = ?";
        return List.of();
    }

    @Override
    public List<City> findByName(String name) {
        String query = "SELECT * FROM city WHERE name = ?";
        return List.of();
    }

    @Override
    public List<City> findAll() {
        String query = "SELECT * FROM city";
        return List.of();
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
