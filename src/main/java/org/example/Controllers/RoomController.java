package org.example.Controllers;

import org.example.Models.GuessRequest;
import org.example.Models.GuessesAndResults;
import org.example.Models.Room;
import org.example.Services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("player/{name}")
    public String createPlayer(@PathVariable String name){
        return roomService.createPlayer(name);
    }

    @GetMapping("getname/{roomID}/{player}")
    public String getName(@PathVariable int roomID, @PathVariable int player){
        return roomService.getName(roomID, player);
    }

    @GetMapping("/create/{playerId}")
    public ResponseEntity<Room> createRoom(@PathVariable int playerId){
        return roomService.createRoom(playerId);
    }

    @GetMapping("/join/{playerId}/{roomId}")
    public ResponseEntity<String> joinRoom(@PathVariable int playerId, @PathVariable int roomId){
        return roomService.joinRoom(playerId, roomId);
    }

    @GetMapping("/set/playersc/{roomId}/{player}/{playerSc}")
    public String setSc(@PathVariable int roomId, @PathVariable int player, @PathVariable String playerSc){
        return roomService.setSC(roomId, player, playerSc);
    }

    @GetMapping("startGame/{id}")
    public boolean startGame(@PathVariable int id){
        return roomService.startGame(id);
    }

     @GetMapping("/playerGuessResult/{id}/{player}")
     public ResponseEntity<Map<String, List<String>>> getPlayerGuessesAndResults(@PathVariable int id, @PathVariable int player) {
         GuessesAndResults result = roomService.getPlayerGuessesAndResults(id, player);
         Map<String, List<String>> response = new HashMap<>();
         response.put("guesses", result.getGuesses());
         response.put("results", result.getResults());
         return ResponseEntity.ok(response);
     }

    @GetMapping("guess/{roomId}/{player}/{guess}")
    public String setPlayerGuesses(@PathVariable int roomId, @PathVariable int player, @PathVariable String guess) {
        return roomService.setPlayerGuesses(roomId, player, guess);
    }

    @GetMapping("check/{id}/{player}/{guess}")
    public String checkGuess(@PathVariable int id, @PathVariable int player, @PathVariable String guess){
        return roomService.checkGuess(id, player, guess);
    }

    @GetMapping("getsc/{roomId}/{player}")
    public String getSc(@PathVariable int roomId, @PathVariable int player){
        return roomService.getSc(roomId, player);
    }

}
