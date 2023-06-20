package com.example.laboratoire5_1.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class User implements Serializable{

    @PrimaryKey
    @NonNull
    public String email;
    private String name;

    public User(String name, @NonNull String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }
}
