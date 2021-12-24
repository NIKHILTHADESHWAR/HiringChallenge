package HiringChallenge.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="game")
@Data
public class GameDao {
    @Id
    private String token;
    private int serverScore;
    private int userScore;
}
