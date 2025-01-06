package org.example.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class GuessesAndResults {

    private List<String> guesses;

    public List<String> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<String> guesses) {
        this.guesses = guesses;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    private List<String> results;

    public GuessesAndResults(List<String> player2Guesses, List<String> player2Results) {
        guesses = player2Guesses;
        results = player2Results;
    }
}
