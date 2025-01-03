package org.example.Models;

import lombok.Data;

import java.util.List;

@Data
public class GuessesAndResults {

    private List<String> guesses;

    private List<String> results;

    public GuessesAndResults(List<String> player2Guesses, List<String> player2Results) {
        guesses = player2Guesses;
        results = player2Results;
    }
}
