package com.example.Servlet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryUserStorage {

    private static Set<User> users = new HashSet<>();

    public static void add(User user) {
        users.add(user);
    }

    public static List<User> findALl() {
        return new ArrayList<>(users);
    }
}
