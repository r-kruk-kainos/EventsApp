package domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Principal {

    @Id
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "admin")
    @NotNull
    private boolean admin;

    @Column(name = "access_token")
    @NotNull
    private String accessToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Event> eventList = new ArrayList<Event>();

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public String getName() {
        return "person";
    }

    public boolean implies(Subject subject) {
        return false;
    }
}
