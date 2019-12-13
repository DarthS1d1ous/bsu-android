package com.borschevskydenis.fifthlab;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static AppDatabase database;
    private LiveData<List<User>> users;

    public LiveData<List<User>> getAllUsers() {
        return users;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(getApplication());
        users = database.userDao().getAll();
    }

    public Optional<User> getUser(int id) {
        try {
            return new GetUserTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void deleteAllUsers() {
        new DeleteAllUsersTask().execute();
    }

    public void insertUser(User user) {
        new InsertUserTask().execute(user);
    }

    public void deleteUser(User user) {
        new DeleteUserTask().execute(user);
    }

    public Optional<User> getByNameAndSurname(String name, String surname) {
        try {
            return new GetUserByNameAndSurnameTask().execute(name, surname).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<User> getByNameOrSurname(String name, String surname) {
        try {
            return new GetUserByNameOrSurnameTask().execute(name, surname).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private static class GetUserByNameAndSurnameTask extends AsyncTask<String, Void, Optional<User>> {
        @Override
        protected Optional<User> doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                return database.userDao().getByNameAndSurname(strings[0], strings[1]);
            }
            return Optional.empty();
        }
    }

    private static class GetUserByNameOrSurnameTask extends AsyncTask<String, Void, Optional<User>> {
        @Override
        protected Optional<User> doInBackground(String... strings) {
            if (strings != null && strings.length > 0) {
                return database.userDao().getByNameOrSurname(strings[0], strings[1]);
            }
            return Optional.empty();
        }
    }

    private static class DeleteUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... resultsBeans) {
            if (resultsBeans != null && resultsBeans.length > 0) {
                database.userDao().delete(resultsBeans[0]);
            }
            return null;
        }
    }

    private static class InsertUserTask extends AsyncTask<User, Void, Void> {
        @Override
        protected Void doInBackground(User... resultsBeans) {
            if (resultsBeans != null && resultsBeans.length > 0) {
                database.userDao().insert(resultsBeans[0]);
            }
            return null;
        }
    }

    private static class DeleteAllUsersTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... integers) {
            if (integers != null && integers.length > 0) {
                database.userDao().deleteAllUsers();
            }
            return null;
        }
    }

    private static class GetUserTask extends AsyncTask<Integer, Void, Optional<User>> {
        @Override
        protected Optional<User> doInBackground(Integer... integers) {
            if (integers != null && integers.length > 0) {
                return database.userDao().getById(integers[0]);
            }
            return Optional.empty();
        }
    }

}