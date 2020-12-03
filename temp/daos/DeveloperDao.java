package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Developer;

import java.util.Collection;
import java.sql.*;

public interface DeveloperDao {
    //1
    void createDeveloper(Developer developer);
    //2
    Collection<Developer> findAllDevelopers();
    //3
    Developer findDeveloperByID(int developerID);
    //4
    Developer findDeveloperByUsername(String username);
    //5
    Developer findDeveloperByCredentials(String username, String password);
    //6
    int updateDeveloper(int developerID, Developer developer);
    //7
    int deleteDeveloper(int developerID);
}
