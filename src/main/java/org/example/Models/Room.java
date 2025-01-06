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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1Id() {
        return player1Id;
    }

    public void setPlayer1Id(int player1Id) {
        this.player1Id = player1Id;
    }

    public int getPlayer2Id() {
        return player2Id;
    }

    public void setPlayer2Id(int player2Id) {
        this.player2Id = player2Id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPlayer1SC() {
        return player1SC;
    }

    public void setPlayer1SC(String player1SC) {
        this.player1SC = player1SC;
    }

    public String getPlayer2SC() {
        return player2SC;
    }

    public void setPlayer2SC(String player2SC) {
        this.player2SC = player2SC;
    }

    public List<String> getPlayer1Guesses() {
        return player1Guesses;
    }

    public void setPlayer1Guesses(List<String> player1Guesses) {
        this.player1Guesses = player1Guesses;
    }

    public List<String> getPlayer1Results() {
        return player1Results;
    }

    public void setPlayer1Results(List<String> player1Results) {
        this.player1Results = player1Results;
    }

    public List<String> getPlayer2Guesses() {
        return player2Guesses;
    }

    public void setPlayer2Guesses(List<String> player2Guesses) {
        this.player2Guesses = player2Guesses;
    }

    public List<String> getPlayer2Results() {
        return player2Results;
    }

    public void setPlayer2Results(List<String> player2Results) {
        this.player2Results = player2Results;
    }
}

