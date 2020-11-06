package pl.sda.javatarr6.todo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @OneToMany(mappedBy = "idUser")
    private List<ZadanieEntity> zadanieEntities;

    @Column(nullable = false,unique=true,length = 50)
    @NotBlank(message = "Nazwa uzytkownika jest wymagana!")

    private String username; //login, adres email

    @Column(nullable = false)
    @NotBlank(message = "Hasło jest wymagane!")
    private String password;

    @Column(length = 50)
    private String text;

    public User() {
    }

    public User(Long idUser) {
        this.idUser = idUser;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long idUser, String username, String password, String text) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.text = text;
    }

    public User(String username,  String password, String text) {
        this.username = username;
        this.text = text;
        this.password = password;
    }

    public User(long idUser, String username, String password) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
