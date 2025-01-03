package org.example.Models;


import lombok.Data;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ElementCollection;
import lombok.Setter;

import java.util.List;


@Entity
@Data
public class Room {

    @Id
    private int id;

    private int player1Id;

    private int player2Id;

    private boolean isActive;

    private String player1SC;

    private String player2SC;

    @ElementCollection
    private List<String> player1Guesses;

    @ElementCollection
    private List<String> player1Results;

    @ElementCollection
    private List<String> player2Guesses;

    @ElementCollection
    private List<String> player2Results;

}

