package com.booleanuk.api;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.repository.GameRepository;
import com.booleanuk.api.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

public class HelperUtils {
    /**
     * Private Constructor to avoid instantiation as class holds static helper methods
     */
    private HelperUtils() {}

    @Autowired
    private static GameRepository gameRepository;

    public static <T> ResponseEntity<ApiResponse<?>> badRequest(T data) {
        ApiResponse<T> badRequest = new ApiResponse<>("error", data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequest);
    }

    public static <T> ResponseEntity<ApiResponse<?>> createdRequest(T data) {
        ApiResponse<T> createdRequest = new ApiResponse<>("success", data);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
    }

    public static <T> ResponseEntity<ApiResponse<?>> okRequest(T data) {
        ApiResponse<T> okRequest = new ApiResponse<>("success", data);
        return ResponseEntity.status(HttpStatus.OK).body(okRequest);
    }

    public static Game getAGame(int id) {
        return gameRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    public static Game updateAGame(int id, Game game) {
        Game gameToUpdate = getAGame(id);
        gameToUpdate.setTitle(game.getTitle());
        gameToUpdate.setGameStudio(game.getGameStudio());
        gameToUpdate.setGenre(game.getGenre());
        gameToUpdate.setPlayers(game.getPlayers());
        gameToUpdate.setAgeRating(game.getAgeRating());
        return gameToUpdate;
    }
}

