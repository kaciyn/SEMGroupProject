package com.napier.sem;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.napier.sem.App.cities;
import static com.napier.sem.App.countries;

//handles population number requests
public class PopulationNumbers
{
    //enum for area scopes
    public enum Scope
    {
        Continent,
        Region,
        District,
        City
    }

    //Prints population to console, using BigInteger because int can't hold enough
    static void PrintPopulationToConsole(String place, BigInteger population)
    {
        System.out.println("The population of " + place + " is " + population + "\n");
    }

    //Returns total population of list of countries
    static BigInteger CalculatePopulationInCountries(List<Country> countriesToCount)
    {

        BigInteger population = new BigInteger("0");

        for (Country country : countriesToCount) {

            population = population.add(BigInteger.valueOf(country.Population));
        }
        return population;
    }

    //Returns total population of list of cities
    static BigInteger CalculatePopulationInCities(List<City> citiesToCount)
    {
        BigInteger population = new BigInteger("0");

        for (City city : citiesToCount) {
            population = population.add(BigInteger.valueOf(city.Population));
        }
        return population;
    }

    //returns list of countries in given area
    static List<Country> GetCountriesInArea(Scope scope, String area)
    {
        List<Country> countriesToReturn;

        switch (scope) {
            case Continent:
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Continent.equals(area)).collect(Collectors.toList());

                if (countriesToReturn.isEmpty()) {
                    System.out.println("No countries found in " + area + ". Please enter a different area.\n");
                    GetContinentPopulation();
                }
                return countriesToReturn;

            case Region:
                countriesToReturn = countries.stream()
                        .filter((country) -> country.Region.equals(area)).collect(Collectors.toList());

                if (countriesToReturn.isEmpty()) {
                    System.out.println("No countries found on " + area + ". Please enter a different area.\n");
                    GetRegionPopulation();
                }
                return countriesToReturn;
            default: {
                System.out.println("Unknown option entered, returning no countries\n");
                return new ArrayList<>();
            }
        }
    }

    //returns list of cities in given area
    static List<City> GetCitiesInArea(Scope scope, String area)
    {
        List<City> citiesToReturn;

        switch (scope) {
            case District:
                citiesToReturn = cities.stream()
                        .filter((city) -> city.District.equals(area)).collect(Collectors.toList());

                if (citiesToReturn.isEmpty()) {
                    System.out.println("No cities found on " + area + ". Please enter a different district.");
                    GetDistrictPopulation();
                }
                return citiesToReturn;

            case City:
                citiesToReturn = cities.stream()
                        .filter((city) -> city.Name.equals(area)).collect(Collectors.toList());

                if (citiesToReturn.isEmpty()) {
                    System.out.println("No cities found with name " + area + ". Please enter a different city.");
                    GetCityPopulation();
                }
                return citiesToReturn;

            default: {
                System.out.println("Unknown option entered, returning no cities");
                return new ArrayList<>();
            }
        }

    }

    //Gets+prints world population
    static void GetWorldPopulation()
    {
        PrintPopulationToConsole("the world", CalculatePopulationInCountries(countries));
    }

    //Gets+prints continent population
    static void GetContinentPopulation()
    {
        System.out.println("Please enter which continent you want to get the population for: \n");

        Scanner in = new Scanner(System.in);
        String continent = in.nextLine();

        List<Country> requestedCountries = GetCountriesInArea(Scope.Continent, continent);

        PrintPopulationToConsole(continent, CalculatePopulationInCountries(requestedCountries));
    }

    //Gets+prints region population
    static void GetRegionPopulation()
    {
        System.out.println("Please enter which continent you want to get the population for: \n");

        Scanner in = new Scanner(System.in);
        String region = in.nextLine();

        List<Country> requestedCountries = GetCountriesInArea(Scope.Region, region);

        PrintPopulationToConsole(region, CalculatePopulationInCountries(requestedCountries));
    }

    //Gets+prints district population
    static void GetDistrictPopulation()
    {
        System.out.println("Please enter which district you want to get the population for: \n");

        Scanner in = new Scanner(System.in);
        String district = in.nextLine();

        List<City> requestedCities = GetCitiesInArea(Scope.District, district);

        PrintPopulationToConsole(district, CalculatePopulationInCities(requestedCities));
    }

    //Gets+prints city population
    static void GetCityPopulation()
    {
        System.out.println("Please enter which district you want to get the population for: \n");

        Scanner in = new Scanner(System.in);
        String city = in.nextLine();

        List<City> requestedCities = GetCitiesInArea(Scope.City, city);

        PrintPopulationToConsole(city, CalculatePopulationInCities(requestedCities));
    }
}

