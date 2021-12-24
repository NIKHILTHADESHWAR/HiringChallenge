package HiringChallenge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GameRepository extends CrudRepository<GameDao, String> {
}