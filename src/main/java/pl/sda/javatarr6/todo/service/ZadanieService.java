package pl.sda.javatarr6.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

import pl.sda.javatarr6.todo.dto.ZadanieDto;
import pl.sda.javatarr6.todo.entity.User;
import pl.sda.javatarr6.todo.entity.ZadanieEntity;
import pl.sda.javatarr6.todo.mapper.ZadaniaMapper;
import pl.sda.javatarr6.todo.repository.ZadanieRepository;


@Service
public class ZadanieService  {

    @Autowired
    private ZadanieRepository zadanieRepository;

    public List<ZadanieDto> getAll() {

        Iterable<ZadanieEntity> entities = zadanieRepository.findAll();
        List<ZadanieDto> dtos = ZadaniaMapper.mapZadanieEntitiesToDto(entities);

        return dtos;
    }

    public List<ZadanieDto> getAllByIdUser(User id) {

        Iterable<ZadanieEntity> entities = zadanieRepository.getAllByIdUser(id);
        List<ZadanieDto> dtos = ZadaniaMapper.mapZadanieEntitiesToDto(entities);
        return dtos;
    }

    public List<ZadanieDto> getAllCompletedByIdUser(boolean ukonczone, User id) {

        Iterable<ZadanieEntity> entities = zadanieRepository.getZadanieEntitiesByUkonczoneIsAndIdUser(ukonczone,id);
        List<ZadanieDto> dtos = ZadaniaMapper.mapZadanieEntitiesToDto(entities);
        return dtos;
    }


    public ZadanieDto save(ZadanieDto zadanieDto) {

        try {
            ZadanieEntity entity = ZadaniaMapper.mapDtoToZadanieEntity(zadanieDto);
            ZadanieEntity dto = zadanieRepository.save(entity);
            return ZadaniaMapper.mapZadanieEntitiesToDto(dto);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ZadanieEntity finishzadanieEntity(Long id) {
        if (id != null) {

            ZadanieEntity entity = zadanieRepository.getById(id);

            entity.setUkonczone(true);
            entity.setDataZamkniecia(new Date());
            zadanieRepository.save(entity);

        } else System.out.println("Nie podano ID");

        return null;
    }

    public ZadanieEntity zmienpiszadanieEntity(Long id, String opis) {

        if (id != null) {
            ZadanieEntity entity = zadanieRepository.getById(id);
            if (!entity.isUkonczone()) {
                entity.setOpis(opis);
                zadanieRepository.save(entity);
            }

        } else System.out.println("Nie podano ID");

        return null;
    }

}

