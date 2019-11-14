package com.borschevskydenis.servicesapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int number = intent.getIntExtra("number", 0);

        List<Integer> numbers = fillSetWithNumbers(number);

        sendNumbersToActivity(numbers.size(),convertNumbers(numbers));
    }

    private String convertNumbers(List<Integer> numbers){
        StringBuilder allNumbers = new StringBuilder();

        for (int s: numbers) {
            if(s == numbers.get(numbers.size()-1)){
                allNumbers.append(s);
                continue;
            }
            allNumbers.append(s).append(", ");
        }
        return allNumbers.toString();
    }

    private List<Integer> fillSetWithNumbers(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count + 1; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    private void sendNumbersToActivity(int count, String numbers){
        Intent intent = new Intent("MainActivity");
        intent.putExtra("count", count);
        intent.putExtra("numbers",numbers);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
