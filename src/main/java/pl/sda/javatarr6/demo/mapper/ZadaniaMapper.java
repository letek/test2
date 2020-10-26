package pl.sda.javatarr6.demo.mapper;

//import pl.sda.javatarr6.demo.dto.ItemDto;

import pl.sda.javatarr6.demo.dto.ZadanieDto;
import pl.sda.javatarr6.demo.entity.ZadanieEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ZadaniaMapper {

    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static List<ZadanieDto> mapZadanieEntitiesToDto(Iterable<ZadanieEntity> entities) {

        List<ZadanieDto> result = new ArrayList<>();

        for (ZadanieEntity entity : entities) {
            ZadanieDto dto = mapZadanieEntitiesToDto(entity);

            //ZadanieDto dto = new ZadanieDto(entity.getId(),entity.getOpis());
            //System.out.println("Zadanie Mapper: "+entity.getOpis());

            result.add(dto);
        }
        //System.out.println("sout z Zadanie Mapper");

        return result;
    }

    public static ZadanieDto mapZadanieEntitiesToDto(ZadanieEntity entity) {

        ZadanieDto dto = new ZadanieDto();
        dto.setId(entity.getId());
        dto.setOpis(entity.getOpis());

        //System.out.println("Zadnie Mapper + mapZadanieEntitiesToDto");

        dto.setDataUtworzenia(new SimpleDateFormat(DATE_FORMAT).format(entity.getDataUtworzenia()));

        if (entity.getDataZamkniecia() != null) {
            dto.setDataZamkniecia(new SimpleDateFormat(DATE_FORMAT).format(entity.getDataZamkniecia()));
        }
        dto.setUkonczone(entity.isUkonczone());
        return dto;
    }

    //przy dodawaniu
    public static ZadanieEntity mapDtoToZadanieEntity(ZadanieDto dto) throws ParseException {

        ZadanieEntity entity = new ZadanieEntity();
        //entity.setId(dto.getId());
        entity.setOpis(dto.getOpis());
        entity.setDataUtworzenia(new Date());
        entity.setUkonczone(dto.isUkonczone());
        return entity;
    }
}
