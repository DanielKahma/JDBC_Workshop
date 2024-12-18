package se.danielk.DaoPackage;

import se.danielk.City;
import se.danielk.SQLConnection.MySQLConnection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {


    @Override
    public City findById(int id) {
        String query = "SELECT * FROM city WHERE id = ?";
        City city = new City();
        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
                )
        {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){

                resultSet.getInt("ID");
                resultSet.getString("Name");
                resultSet.getString("CountryCode");
                resultSet.getString("District");
                resultSet.getInt("Population");
            }

        } catch(SQLException e) {
            e.printStackTrace();
    }
    return city;

    }

        @Override
    public List<City> findByCode(String code) {
        String query = "SELECT * FROM city WHERE CountryCode = ?";
        List<City> cityList = new ArrayList<>();

            try (
                    PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
            )
            {
                preparedStatement.setString(1, code);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    cityList.add(new City(

                    resultSet.getInt("ID"),
                    resultSet.getString("Name"),
                    resultSet.getString("CountryCode"),
                    resultSet.getString("District"),
                    resultSet.getInt("Population")));
                }

            } catch(SQLException e) {
                e.printStackTrace();
            }
            return cityList;
    }

    @Override
    public List<City> findByName(String name) {
        String query = "SELECT * FROM city WHERE name = ?";
        List<City> nameList = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
        )
        {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                nameList.add(new City(

                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return nameList;
    }

    @Override
    public List<City> findAll() {
        String query = "SELECT * FROM city";
        List<City> allList = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
        ){
            ResultSet resultSet = preparedStatement.executeQuery(query);
            if (resultSet.next()){
                allList.add(new City(

                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("CountryCode"),
                        resultSet.getString("District"),
                        resultSet.getInt("Population")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return allList;
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
