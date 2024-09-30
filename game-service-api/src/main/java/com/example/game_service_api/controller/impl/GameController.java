package com.example.game_service_api.controller.impl;

import com.example.game_service_api.commons.entities.Game;
import com.example.game_service_api.controller.GameApi;
import com.example.game_service_api.services.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Servicio para gestionar la lógica de negocio de los juegos, clase REST.
public class GameController implements GameApi {
    private final GameService gameService;

    public GameController(GameService gameService) {

        this.gameService = gameService;
    }
    // Endpoint para guardar un nuevo juego
    @Override
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {

      Game gameCreated = this.gameService.saveGame(game);
      return ResponseEntity.ok(gameCreated);
    }
    // Endpoint para obtener un juego por su ID
    @Override
    public ResponseEntity<Game> getGameById(Long id) {

        return ResponseEntity.ok(this.gameService.getGameById(id));
    }

    // Endpoint para actualizar un juego existente por su Id
    @Override
    public ResponseEntity<Game> updateGameById(@RequestBody Long id, Game gameRequest ) {
        return ResponseEntity.ok(this.gameService.updateGame(id, gameRequest));
    }
    // Endpoint para eliminar un juego por su Id
    @Override
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
            gameService.deleteById(id);
            return ResponseEntity.noContent().build();
    }
    // Endpoint para obtener el nombre de usuario desde el encabezado de la solicitud
    @Override
    public ResponseEntity<String> getUserName(@RequestHeader("userIdRequest") String user) {
        System.out.println(user);
        return ResponseEntity.ok(user);
    }

}



