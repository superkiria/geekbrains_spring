package ru.motrichkin.persistence;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate date;

    @Column
    private Integer status;

    @OneToMany(
            mappedBy = "customerOrder",
            cascade = CascadeType.ALL
    )
    private List<Position> positionList;

    @ManyToOne
    private Person person;

    public CustomerOrder() {
    }

    public CustomerOrder(LocalDate date, Integer status, List<Position> positionList, Person person) {
        this.date = date;
        this.status = status;
        this.positionList = positionList;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", positionList=" + positionList +
                ", person=" + person +
                '}';
    }
}
