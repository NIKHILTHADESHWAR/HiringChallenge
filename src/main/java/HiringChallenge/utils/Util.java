package HiringChallenge.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class Util {

    private Map<String,Integer> moveMap = new HashMap<String,Integer>();
    private Map<Integer,String> reverseMoveMap = new HashMap<Integer,String>();
    private Map<String,String> moveStrategicMap = new HashMap<String,String>();
    private int[][][] scoreTable = new int[3][3][2];

    public Util() {
        moveMap.put("rock",0);
        moveMap.put("paper",1);
        moveMap.put("scissor",2);

        reverseMoveMap.put(0,"rock");
        reverseMoveMap.put(1,"paper");
        reverseMoveMap.put(2,"scissor");

        scoreTable[0][1] = new int[]{0,1};
        scoreTable[0][2] = new int[]{1,0};
        scoreTable[1][0] = new int[]{1,0};
        scoreTable[1][2] = new int[]{0,1};
        scoreTable[2][0] = new int[]{0,1};
        scoreTable[2][1] = new int[]{1,0};
        for(int i=0; i<3; i++)
            scoreTable[i][i] = new int[]{0,0};

        moveStrategicMap.put("rock","paper");
        moveStrategicMap.put("paper","scissor");
        moveStrategicMap.put("scissor","rock");
    }

    private static final int tokenSize = 10;

    public String generateToken() {
        return RandomStringUtils.randomAlphanumeric(tokenSize);
    }

    public String generateRandomServerMove() {
        int move = new Random().nextInt(10000)%3;
        return reverseMoveMap.get(move);
    }

    public int getMoveMapping(String move) {
        return moveMap.get(move);
    }

    public boolean checkMoveMapping(String move) {
        return moveMap.containsKey(move);
    }

    public int[] findWinner(String userMove, String serverMove) {
        System.out.println("ServerMove : "+moveMap.get(serverMove));
        System.out.println("UserMove : "+moveMap.get(userMove));
        return scoreTable[(int)moveMap.get(userMove)][(int)moveMap.get(serverMove)];
    }

    public String generateStrategicServerMove(String userMove) {
          return moveStrategicMap.get(userMove);
    }
}
