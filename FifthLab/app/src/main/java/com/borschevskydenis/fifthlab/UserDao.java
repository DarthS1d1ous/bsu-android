package com.borschevskydenis.fifthlab;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import java.util.Optional;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    LiveData<List<User>> getAll();

    @Query("SELECT * FROM users WHERE id = :id")
    Optional<User> getById(long id);

    @Query("DELETE FROM users")
    void deleteAllUsers();

    @Query("SELECT * FROM users WHERE name LIKE :name and surname LIKE :surname")
    Optional<User> getByNameAndSurname(String name, String surname);

    @Query("SELECT * FROM users WHERE name LIKE :name or surname LIKE :surname")
    Optional<User> getByNameOrSurname(String name, String surname);

    @Query("SELECT * FROM users WHERE surname LIKE :surname")
    Optional<User> getBySurname(String surname);

    @Insert
    void insert(User employee);

    @Update
    void update(User employee);

    @Delete
    void delete(User employee);
}
