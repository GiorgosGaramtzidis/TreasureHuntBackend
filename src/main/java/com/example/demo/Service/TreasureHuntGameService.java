package com.example.demo.Service;

import com.example.demo.Registration.TreasureHuntGameRegistration;
import com.example.demo.dao.TreasureHuntGameRepository;
import com.example.demo.dao.UsersRepository;
import com.example.demo.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
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
        List<GameLocation> locationsGame = new ArrayList<>();
        treasureHuntGame.setId(id);
        treasureHuntGame.setGameName(gameName);
        treasureHuntGame.setGameLocation(gameLocation);
        treasureHuntGame.setUserList(userList);
        treasureHuntGame.setUserPositionList(userPositionList);
        treasureHuntGame.setState(GameState.DidNotStart);
        treasureHuntGame.setGameLocationsList(locationsGame);
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

       for (int i=0;i<treasureHuntGameRepository.findTreasureHuntGameById(id).getUserList().size();i++)
       {
           if (treasureHuntGameRepository.findTreasureHuntGameById(id).getUserList().get(i).getUserName().equals(userName))
               return true;
       }
            List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream()
                    .filter(treasureHuntGame -> treasureHuntGame.getId()
                            .equals(id))
                    .collect(Collectors.toList());
            treasureHuntGames.get(0).getUserList().add(usersRepository.findUserByUserName(userName));
            try{
                treasureHuntGames.get(0).setState(GameState.Playing);
                treasureHuntGameRepository.save(treasureHuntGames.get(0));
                return true;
            }catch (IndexOutOfBoundsException e){
                throw new Exception("GAME Does not exist");
            }

    }

    @Override
    public Boolean addLocation(GameLocation gameLocation, String id) throws Exception {

        try{
            List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream()
                    .filter(treasureHuntGame -> treasureHuntGame.getId()
                            .equals(id))
                    .collect(Collectors.toList());
                treasureHuntGames.get(0).getGameLocationsList().add(gameLocation);
                treasureHuntGameRepository.save(treasureHuntGames.get(0));

        }catch(IndexOutOfBoundsException e)
        {
            throw new Exception("GAME Does not exist");
        }
        return true;
    }

    @Override
    public void setWinner(User user, String id) throws Exception {

        List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream()
                .filter(treasureHuntGame -> treasureHuntGame.getId()
                        .equals(id))
                .collect(Collectors.toList());
     try{
         treasureHuntGames.get(0).setWinner(user);
         treasureHuntGames.get(0).setState(GameState.Over);
         treasureHuntGameRepository.save(treasureHuntGames.get(0));
     }catch (IndexOutOfBoundsException e){
         throw new Exception("GAME Does not exist");
     }

    }

    @Override
    public List<AvailableGames> getAvailableGames() throws Exception {
        List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository.findAll().stream().filter(
                treasureHuntGame ->
                        treasureHuntGame.getState().equals(GameState.DidNotStart)
                        || treasureHuntGame.getState().equals(GameState.Playing)).collect(Collectors.toList());
        List<AvailableGames> availableGames = new ArrayList<>();
        for(int i =0; i<treasureHuntGames.size();i++) {
            if (treasureHuntGames.get(i).getGameLocationsList().size() >0)
            {
                AvailableGames treasurehunt =  new AvailableGames(treasureHuntGames.get(i).getGameLocation(),treasureHuntGames.get(i).getId());
                availableGames.add(treasurehunt);
            }
        }
      return availableGames;
    }

    public void addPlayersLocationToGame(UserPosition userPosition ,String gameId)
    {

        List<TreasureHuntGame> treasureHuntGames = treasureHuntGameRepository
                .findAll()
                .stream()
                .filter(treasureHuntGame ->
                        treasureHuntGame.getId().equals(gameId))
                .collect(Collectors.toList());
        boolean flag = false;
        if (treasureHuntGames.get(0).getUserPositionList().size()>0)
        {
          for (int i=0;i<treasureHuntGames.get(0).getUserPositionList().size();i++)
          {
              if (treasureHuntGames.get(0).getUserPositionList().get(i).getUserName().equals(userPosition.getUserName())) {
                  treasureHuntGames.get(0).getUserPositionList().set(i, userPosition);
                  flag =true;
              }
              if (flag)
                  break;
          }
        }
        if (!flag)
            treasureHuntGames.get(0).getUserPositionList().add(userPosition);
        treasureHuntGameRepository.save(treasureHuntGames.get(0));
    }
}
