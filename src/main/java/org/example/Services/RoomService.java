package org.example.Services;

import org.example.DAO.PlayerDAO;
import org.example.DAO.RoomDAO;
import org.example.Models.GuessesAndResults;
import org.example.Models.Player;
import org.example.Models.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RoomService {

    private static final Random random = new Random();

    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    RoomDAO roomDAO;

    public ResponseEntity<Room> createRoom(int playerId) {
        int id;
        do{
            id = 1000 + random.nextInt(9000);
            System.out.println(id);
        }while(roomDAO.findById(id).isPresent());

        Room room = new Room();
        room.setId(id);
        room.setPlayer1Id(playerId);
        room.setActive(false);

        roomDAO.save(room);

        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    public ResponseEntity<String> joinRoom(int playerId, int roomId) {
        Optional<Room> roomObj = roomDAO.findById(roomId);

        if(roomObj.isEmpty()) return new ResponseEntity<>("Room not found!!", HttpStatus.NOT_FOUND);
        Room room = roomObj.get();

        room.setPlayer2Id(playerId);
        room.setActive(true);

        roomDAO.save(room);

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    public String setSC(int roomId, int player, String playerSc) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if(roomObj.isEmpty()) return "Room not found!!";
        Room room = roomObj.get();
        if(player == 1) room.setPlayer1SC(playerSc);
        else room.setPlayer2SC(playerSc);

        roomDAO.save(room);

        return "Success!";
    }

    public boolean startGame(int roomId) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if(roomObj.isEmpty()) return false;
        Room room = roomObj.get();
        return room.isActive();
    }

    public GuessesAndResults getPlayerGuessesAndResults(int roomId, int player) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if(roomObj.isEmpty()) return null;
        Room room = roomObj.get();
        if(player == 1) {
            return new GuessesAndResults(room.getPlayer2Guesses(), room.getPlayer2Results());
        }
        else return new GuessesAndResults(room.getPlayer1Guesses(), room.getPlayer1Results());
    }

    public String setPlayerGuesses(int roomId, int player, String guess) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if (roomObj.isEmpty()) return "Room not found!!";

        Room room = roomObj.get();
        List<String> playerGuesses = (player == 1) ? room.getPlayer1Guesses() : room.getPlayer2Guesses();
        List<String> playerResults = (player == 1) ? room.getPlayer1Results() : room.getPlayer2Results();

        if (playerGuesses == null) playerGuesses = new ArrayList<>();
        if (playerResults == null) playerResults = new ArrayList<>();

        playerGuesses.add(guess);
        playerResults.add(checkGuess(roomId, player, guess));

        if (player == 1){
            room.setPlayer1Guesses(playerGuesses);
            room.setPlayer1Results(playerResults);
        }
        else {
            room.setPlayer2Guesses(playerGuesses);
            room.setPlayer2Results(playerResults);
        }

        roomDAO.save(room);
        return "Success!";
    }

    public String checkGuess(int roomId, int player, String guess) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if (roomObj.isEmpty()) return "Room not found!!";
        Room room = roomObj.get();

        if(player == 1) return checkGuessHelper(guess, room.getPlayer2SC());
        else return checkGuessHelper(guess, room.getPlayer1SC());
    }

    public String checkGuessHelper(String guess, String sc) {
        int correctPosition = 0;
        int correctCharacter = 0;

        for (int i = 0; i < 4; i++) {
            char guessChar = guess.charAt(i);
            if (guessChar == sc.charAt(i)) correctPosition++;
            else if (sc.indexOf(guessChar) != -1) correctCharacter++;
        }

        return correctPosition + "C" + correctCharacter + "B";
    }

    public String createPlayer(String name) {
        Optional<Player> existingPlayer = playerDAO.findByName(name);
        if (existingPlayer.isPresent()) {
            return ""+existingPlayer.get().getId();
        }

        Player player = new Player();
        player.setName(name);
        Player savedPlayer = playerDAO.save(player);
        return ""+savedPlayer.getId();
    }


    public String getSc(int roomId, int player) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if (roomObj.isEmpty()) return "Room not found!!";
        Room room = roomObj.get();
        if(player == 1) return room.getPlayer1SC();
        else return room.getPlayer2SC();
    }

    public String getName(int roomId, int player) {
        Optional<Room> roomObj = roomDAO.findById(roomId);
        if (roomObj.isEmpty()) return "Room not found!!";
        Room room = roomObj.get();
        if(player == 1) return playerDAO.findById(room.getPlayer2Id()).get().getName();
        else return playerDAO.findById(room.getPlayer1Id()).get().getName();
    }
}
