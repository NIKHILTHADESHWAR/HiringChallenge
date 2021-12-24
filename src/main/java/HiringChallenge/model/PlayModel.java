package HiringChallenge.model;

import lombok.Data;

@Data
public class PlayModel {
    private String serverMove;
    private int totalScore;

    public static PlayModel build(String serverMove, int totalScore) {
        PlayModel model = new PlayModel();
        model.serverMove = serverMove;
        model.totalScore = totalScore;

        return model;
    }
}
