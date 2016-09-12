package domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "date")
    @NotNull
    private Date date;

    @JoinColumn(name = "person")
    @ManyToOne
    @NotNull
    private Person person;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
