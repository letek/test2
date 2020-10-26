package pl.sda.javatarr6.demo.dto;

public class ZadanieDto {

    private long id;
    private String opis;
    private String dataUtworzenia;
    private String dataZamkniecia;
    private boolean ukonczone;


    //kreatory
    public ZadanieDto() {
    }

    public ZadanieDto(long id, String opis) {
        this.id = id;
        this.opis = opis;
        System.out.println("sout z Zadanie DTO");
    }

    //public ZadanieDto(long id, String opis, String dataUtworzenia, String dataZamkniecia, boolean ukonczone) {
    public ZadanieDto(String opis, String dataUtworzenia, String dataZamkniecia, boolean ukonczone) {
        this.id = id;
        this.opis = opis;
        this.dataUtworzenia = dataUtworzenia;
        this.dataZamkniecia = dataZamkniecia;
        this.ukonczone = ukonczone;
    }

    public ZadanieDto(long id, String opis, String dataUtworzenia, String dataZamkniecia, boolean ukonczone) {
        //public ZadanieDto(String opis, String dataUtworzenia, String dataZamkniecia, boolean ukonczone) {
        this.id = id;
        this.opis = opis;
        this.dataUtworzenia = dataUtworzenia;
        this.dataZamkniecia = dataZamkniecia;
        this.ukonczone = ukonczone;
    }

    //gettery settery


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public String getDataZamkniecia() {

        return dataZamkniecia;
    }

    public void setDataZamkniecia(String dataZamkniecia) {
        this.dataZamkniecia = dataZamkniecia;
    }

    public boolean isUkonczone() {
        return ukonczone;
    }

    public void setUkonczone(boolean ukonczone) {
        this.ukonczone = ukonczone;
    }

}
