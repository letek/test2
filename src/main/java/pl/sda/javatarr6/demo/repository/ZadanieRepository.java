package pl.sda.javatarr6.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.javatarr6.demo.entity.ZadanieEntity;

@Repository
public interface ZadanieRepository extends CrudRepository<ZadanieEntity, Long> {
    ZadanieEntity getById(Long id);

    //List<Zadanie> getAllByDate(Date date);



}