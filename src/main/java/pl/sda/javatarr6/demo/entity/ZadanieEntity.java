package pl.sda.javatarr6.demo.entity;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

//encja
@Entity
public class ZadanieEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    //@NotBlank(message = "Name is mandatory")
    private String opis;

    @Temporal(TemporalType.DATE)
    private Date dataUtworzenia;

    @Temporal(TemporalType.DATE)
    private Date dataZamkniecia;

    private boolean ukonczone;

    //Gettery Settery


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

    public Date getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(Date dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Date getDataZamkniecia() {
        return dataZamkniecia;
    }

    public void setDataZamkniecia(Date dataZamkniecia) {
        this.dataZamkniecia = dataZamkniecia;
    }

    public boolean isUkonczone() {
        return ukonczone;
    }

    public void setUkonczone(boolean ukonczone) {
        this.ukonczone = ukonczone;
    }

    //Konstruktory


    public ZadanieEntity(String opis, Date dataUtworzenia, Date dataZamkniecia, boolean ukonczone) {
        //this.id = id;
        this.opis = opis;
        this.dataUtworzenia = dataUtworzenia;
        this.dataZamkniecia = dataZamkniecia;
        this.ukonczone = ukonczone;
    }

//    public ZadanieEntity(String opis) {
//
//        this.opis = opis;
//    }

    public ZadanieEntity() {
    }

    public ZadanieEntity(long id) {
        this.id = id;
    }
}

