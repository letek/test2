package pl.sda.javatarr6.todo.mapper;

import pl.sda.javatarr6.todo.dto.ZadanieDto;
import pl.sda.javatarr6.todo.entity.ZadanieEntity;

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

            result.add(dto);
        }

        return result;
    }

    public static ZadanieDto mapZadanieEntitiesToDto(ZadanieEntity entity) {

        ZadanieDto dto = new ZadanieDto();
        dto.setId(entity.getId());
        dto.setOpis(entity.getOpis());

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
        entity.setOpis(dto.getOpis());
        entity.setDataUtworzenia(new Date());
        entity.setUkonczone(dto.isUkonczone());
        entity.setIdUser(dto.getIdUser());
        return entity;
    }
}
