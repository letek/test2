package pl.sda.javatarr6.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import pl.sda.javatarr6.demo.dto.ZadanieDto;
import pl.sda.javatarr6.demo.entity.ZadanieEntity;
import pl.sda.javatarr6.demo.mapper.ZadaniaMapper;
import pl.sda.javatarr6.demo.repository.ZadanieRepository;

@Service
public class ZadanieService {

    @Autowired
    private ZadanieRepository zadanieRepository;

    public List<ZadanieDto> getAll() {

        Iterable<ZadanieEntity> entities = zadanieRepository.findAll();
        List<ZadanieDto> dtos = ZadaniaMapper.mapZadanieEntitiesToDto(entities);

        return dtos;
    }

//    public ZadanieDto getZadanieById(Long id) {
//
//        ZadanieEntity entity = zadanieRepository.getById(id);
//        ZadanieDto dto = ZadaniaMapper.mapZadanieEntitiesToDto(entity);
//
//        return dto;
//    }


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

//            if (entity != null) {
            entity.setUkonczone(true);
            entity.setDataZamkniecia(new Date());
            zadanieRepository.save(entity);
            //          }

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
            else {

                System.out.println("jjjjjjj");}

        } else System.out.println("Nie podano ID");

        return null;
    }

}

