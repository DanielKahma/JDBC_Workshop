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
        String query = "add to city (name, countrycode, district, population) values (?,?,?,?)";
        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int result = preparedStatement.executeUpdate();

            System.out.println("city has been successfully added!");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public City update(City city) {
        String query = "update city set name=?, countrycode=?, district=?, population=? WHERE id=?";

        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
        ){
            preparedStatement.setInt(1, city.getId());
            preparedStatement.setString(2, city.getName());
            preparedStatement.setString(3, city.getCountryCode());
            preparedStatement.setString(4, city.getDistrict());
            preparedStatement.setInt(5, city.getPopulation());

            int result = preparedStatement.executeUpdate();

            System.out.println("city has been successfully updated!");

        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public int delete(City city) {
        String query = "delete FROM city WHERE id=?";

        try (
                PreparedStatement preparedStatement = MySQLConnection.getConnection().prepareStatement(query);
        ){
            preparedStatement.setInt(1, city.getId());
            int result = preparedStatement.executeUpdate();

            System.out.println("City has been successfully deleted!");
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }
}
