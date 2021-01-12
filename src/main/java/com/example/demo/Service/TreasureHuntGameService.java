package com.example.demo.Service;

import com.example.demo.Registration.TreasureHuntGameRegistration;
import com.example.demo.dao.TreasureHuntGameRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreasureHuntGameService implements TreasureHuntGameRegistration<List<LocationsNew>> {

    @Autowired
    private TreasureHuntGameRepository treasureHuntGameRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Boolean createGame(String id,String gameName,String gameLocation){
        TreasureHuntGame treasureHuntGame = new TreasureHuntGame();
        List<User> userList = new ArrayList<>();
        List<UserPosition> userPositionList = new ArrayList<>();
        List<GameLocation> gameLocations = new ArrayList<>();
        treasureHuntGame.setId(id);
        treasureHuntGame.setGameName(gameName);
        treasureHuntGame.setGameLocation(gameLocation);
        treasureHuntGame.setUserList(userList);
        treasureHuntGame.setUserPositionList(userPositionList);
        treasureHuntGame.setState(GameState.DidNotStart);
        treasureHuntGame.setGameLocationsList(gameLocations);
        treasureHuntGame.setWinner(null);
        treasureHuntGameRepository.save(treasureHuntGame);
        return true;
    }


    @Override
    public Optional<TreasureHuntGame> getGame(String gameId) throws Exception {
        if(!treasureHuntGameRepository.existsById(gameId))
            throw new Exception("Game Does not Exist");
        return treasureHuntGameRepository.findById(gameId);

    }

    @Override
    public Boolean addUser(String userName,String id) throws Exception{

            List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream()
                    .filter(treasureHuntGame -> treasureHuntGame.getId()
                            .equals(id))
                    .collect(Collectors.toList());
            treasureHuntGames.get(0).getUserList().add(usersRepository.findUserByUserName(userName));
            if(treasureHuntGames.get(0)==null){
                throw new Exception("GAME Does not exist");
            }
            treasureHuntGameRepository.save(treasureHuntGames.get(0));
            return true;

    }

    @Override
    public Boolean addLocation(GameLocation gameLocation, String id) throws Exception {

        List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream()
                .filter(treasureHuntGame -> treasureHuntGame.getId()
                        .equals(id))
                .collect(Collectors.toList());
        if(treasureHuntGames.get(0)==null){
            throw new Exception("GAME Does not exist");
        }
        treasureHuntGames.get(0).getGameLocationsList().add(gameLocation);

        treasureHuntGameRepository.save(treasureHuntGames.get(0));

        return true;
    }


}
