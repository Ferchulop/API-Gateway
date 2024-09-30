package com.example.game_service_api.controller;


import com.example.game_service_api.commons.constans.ApiPathVariables;
import com.example.game_service_api.commons.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// Esta interfaz define la ruta base para los endpoints de la API games.
@RequestMapping(ApiPathVariables.V1_ROUTE + ApiPathVariables.GAME_ROUTE)
public interface GameApi {
   @PostMapping
   ResponseEntity<Game> saveGame(@RequestBody Game game);
   @GetMapping("/{id}")
   ResponseEntity<Game> getGameById(@PathVariable Long id);
   @PutMapping("/{id}")
   ResponseEntity<Game> updateGameById(@RequestBody Long id, Game gameRequest);
   @DeleteMapping("/{id}")
   ResponseEntity<Void> deleteGame(@PathVariable Long id);
   @GetMapping("/user")
   ResponseEntity<String> getUserName(@RequestHeader("userIdRequest") String user);
}
