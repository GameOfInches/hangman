/**

 * File: GameController.java

 * Author: Krasimir Konstantinov

 * Date: 28/11/2023

 */
package com.backend.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import com.backend.models.*;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

import java.sql.SQLException;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {
    private final JdbcTemplate jdbcTemplate;
    private final RestTemplate restTemplate;

    @Autowired
    public GameController(JdbcTemplate jdbcTemplate, RestTemplate restTemplate) {

        this.jdbcTemplate = jdbcTemplate;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createLobby() {
        String randomString = getRandomString(10); // adjust length as needed
        try {
            String word = getRandomWord();

            String encodedWord = Base64.getEncoder().encodeToString(word.getBytes());

            String sql = "INSERT INTO Hangman_lobbies (lobbyId, secretWord) VALUES (?, ?)";
            jdbcTemplate.update(sql, randomString, encodedWord);
            return new ResponseEntity<>(randomString, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Failed to insert data.");
        }
    }


    @PostMapping("/game/{lobbyId}/checkLetter")
    public ResponseEntity<String> checkLetter(@PathVariable String lobbyId, @RequestBody String letter) throws SQLException {
        String secretWord = getSecretWordForLobby(lobbyId); // retrieve the word based on the lobbyId
        Word hangmanWord = new Word(secretWord); // retrieve the hangman word based on the lobbyId
        hangmanWord.checkLetter(letter.charAt(0)); // check the letter with the hangman word
        String guessedWord = hangmanWord.getGuessedWord(); // retrieve the updated guessed word
        return new ResponseEntity<>(guessedWord, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> checkLetter(@RequestParam String lobbyId) throws SQLException {
        String secretWord = getSecretWordForLobby(lobbyId); // retrieve the word based on the lobbyId
        return new ResponseEntity<>(secretWord, HttpStatus.OK);
    }

    public String getSecretWordForLobby(String lobbyId) throws SQLException {
        // Create a new SQL query to retrieve the secret word for the specified lobby.
        String sql = "SELECT secretWord FROM Hangman_lobbies WHERE lobbyId = ?";

        // Execute the query and store the result in a ResultSet object.
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, lobbyId);

        // If a row is returned, retrieve the secret word and return it.
        if (rs.next()) {
            return rs.getString("secretWord");
        }

        // If no row is returned, return null.
        return null;
    }

    static String getRandomString(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    private String getRandomWord() {
        // Call the Words API for a random word
        String apiUrl = "https://wordsapiv1.p.rapidapi.com/words/?random=true";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "dd0dc86778msh9e98fb165a2762ep12f41ejsna16a0b232485"); // Replace with your RapidAPI key
        headers.set("X-RapidAPI-Host", "wordsapiv1.p.rapidapi.com");

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> apiResponse = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);

        // Process the API response and extract the "word" field
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(apiResponse.getBody());
            return rootNode.path("word").asText();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., log an error) and return an appropriate value
            return "Failed to retrieve word";
        }
    }
}
