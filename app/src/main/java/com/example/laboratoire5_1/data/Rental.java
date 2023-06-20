package com.example.laboratoire5_1.data;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "email",
                        childColumns = "userEmail",
                        onDelete = ForeignKey.CASCADE
                )
        }
)

public class Rental {
    @PrimaryKey(autoGenerate = true)
    public int rentalId;
    @NonNull
    public String userEmail;
    private String name;

    public Rental(@NonNull String userEmail, String name) {
        this.userEmail= userEmail;
        this.name = name;
    }

    @NonNull
    public String getName() {
        return name;
    }


}
