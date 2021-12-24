package HiringChallenge.controller;

import HiringChallenge.model.PlayModel;
import HiringChallenge.model.StartModel;
import HiringChallenge.repository.GameDao;
import HiringChallenge.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import HiringChallenge.utils.Util;

@RestController
@RequestMapping("/")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    Util util = new Util();

    @GetMapping("/start")
    public StartModel startGame() {
        String token = util.generateToken();
        GameDao newGame = new GameDao();
        newGame.setToken(token);
        newGame.setServerScore(0);
        newGame.setUserScore(0);
        gameRepository.save(newGame);
        StartModel model = StartModel.build(token,"READY");

        return model;
    }

    @GetMapping("/v1/{token}/{userMove}")
    public PlayModel playGameRandom(@PathVariable("token") String token, @PathVariable("userMove") String userMove) {
        GameDao game = gameRepository.findById(token).get();

        String serverMove = util.generateRandomServerMove();
        int[] score = util.findWinner(userMove,serverMove);

        game.setUserScore(game.getUserScore()+score[0]);
        game.setServerScore(game.getServerScore()+score[1]);

        gameRepository.save(game);

        if(game.getUserScore()>=3 || game.getServerScore()>=3)
            gameRepository.deleteById(token);

        return PlayModel.build(serverMove,game.getServerScore()+game.getUserScore());
    }


    @GetMapping("/v2/{token}/{userMove}")
    public PlayModel playGameStrategic(@PathVariable("token") String token, @PathVariable("userMove") String userMove) {
        GameDao game = gameRepository.findById(token).get();

        String serverMove = util.generateStrategicServerMove(userMove);
        int[] score = util.findWinner(userMove,serverMove);

        game.setUserScore(game.getUserScore()+score[0]);
        game.setServerScore(game.getServerScore()+score[1]);

        gameRepository.deleteById(token);

        return PlayModel.build(serverMove,game.getServerScore()+game.getUserScore());
    }
}
