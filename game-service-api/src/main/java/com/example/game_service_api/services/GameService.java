package com.example.game_service_api.services;

import com.example.game_service_api.commons.entities.Game;

public interface GameService {


    Game saveGame(Game gameRequest);

    Game getGameById(Long id);

    Game updateGame(Long id,Game gameRequest);

    void deleteById(Long id);
}
