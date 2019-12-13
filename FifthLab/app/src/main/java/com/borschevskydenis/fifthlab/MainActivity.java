package com.borschevskydenis.fifthlab;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Optional;


public class MainActivity extends AppCompatActivity {

    private EditText searchUserByNameEditText;
    private EditText searchUserBySurnameEditText;
    private Button searchUserButton;
    private EditText nameEditText;
    private EditText surnameEditText;
    private EditText birthOfYearEditText;
    private Button addUserButton;

    private TextView addUserErrorTextView;
    private TextView userIdTextView;
    private TextView userNameTextView;
    private TextView userSurnameTextView;
    private TextView userBirthYearTextView;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchUserByNameEditText = findViewById(R.id.searchUserByNameEditText);
        searchUserBySurnameEditText = findViewById(R.id.searchUserBySurnameEditText);
        searchUserButton = findViewById(R.id.searchUserButton);
        nameEditText = findViewById(R.id.editName);
        surnameEditText = findViewById(R.id.editSurname);
        birthOfYearEditText = findViewById(R.id.editBirthYear);
        addUserButton = findViewById(R.id.addUserButton);
        userIdTextView = findViewById(R.id.userId);
        userNameTextView = findViewById(R.id.userName);
        userSurnameTextView = findViewById(R.id.userSurname);
        userBirthYearTextView = findViewById(R.id.userBirthYear);
        addUserErrorTextView = findViewById(R.id.addUserError);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        addUserButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String surname = surnameEditText.getText().toString().trim();
            Optional<User> user = viewModel.getByNameAndSurname(name, surname);
            if (user.isPresent()) {
                addUserErrorTextView.setText(getString(R.string.addUserError));
            } else {
                viewModel.insertUser(new User(nameEditText.getText().toString(),
                        surnameEditText.getText().toString(),
                        birthOfYearEditText.getText().toString().trim()));
                nameEditText.setText("");
                surnameEditText.setText("");
                birthOfYearEditText.setText("");
                addUserErrorTextView.setText("");
            }
        });

        searchUserButton.setOnClickListener(v -> {
            Optional<User> user;
            String name = searchUserByNameEditText.getText().toString().trim();
            String surname = searchUserBySurnameEditText.getText().toString().trim();
            if (name.equals("") || surname.equals("")) {
                user = viewModel.getByNameOrSurname(name, surname);
            } else {
                user = viewModel.getByNameAndSurname(name, surname);
            }
            if (user.isPresent()) {
                User user1 = user.get();
                userIdTextView.setText(String.valueOf(user1.getId()));
                userNameTextView.setText(user1.getName());
                userSurnameTextView.setText(user1.getSurname());
                userBirthYearTextView.setText(user1.getBirthYear());
            } else {
                userIdTextView.setText(getString(R.string.nothingFound));
                userNameTextView.setText("");
                userSurnameTextView.setText("");
                userBirthYearTextView.setText("");
            }
        });
    }
}
