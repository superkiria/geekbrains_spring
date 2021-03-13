package ru.motrichkin.persistence;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Product {

    public static final Map<String, String> COLUMN_MAPPINGS = new HashMap<>();

    static {
        COLUMN_MAPPINGS.put("id", "id");
        COLUMN_MAPPINGS.put("title", "title");
        COLUMN_MAPPINGS.put("cost", "cost");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Integer cost;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<Position> positionList;

    public Product() {
    }

    public Product(String title, Integer cost, List<Position> positionList) {
        this.title = title;
        this.cost = cost;
        this.positionList = positionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public void checkValidBusinessData() {
        if (getCost() <= 0) {
            throw new IllegalArgumentException("The cost should be above zero: " + getCost());
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                '}';
    }
}
