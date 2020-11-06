package pl.sda.javatarr6.todo.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.entity.ZadanieEntity;


@Repository
public interface ZadanieRepository extends CrudRepository<ZadanieEntity, Long> {
    ZadanieEntity getById(Long id);
    List<ZadanieEntity> getAllByIdUser(User id);
    List<ZadanieEntity> getZadanieEntitiesByUkonczoneIsAndIdUser(boolean ukonczone, User id);

}