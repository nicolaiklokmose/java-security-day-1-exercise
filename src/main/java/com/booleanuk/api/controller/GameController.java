package com.booleanuk.api.controller;

import com.booleanuk.api.HelperUtils;
import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllGames() {
        return HelperUtils.okRequest(this.gameRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createGame(@RequestBody Game game) {
        return HelperUtils.createdRequest(this.gameRepository.save(game));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteGameById(@PathVariable int id) {
        System.out.println("test1");
        Game gameToDelete = HelperUtils.getAGame(id);
        System.out.println("test2");
        return HelperUtils.okRequest(gameToDelete);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateGameById(@PathVariable int id, @RequestBody Game game) {
        Game gameToUpdate = HelperUtils.updateAGame(id, game);
        return HelperUtils.createdRequest(this.gameRepository.save(gameToUpdate));
    }

}
