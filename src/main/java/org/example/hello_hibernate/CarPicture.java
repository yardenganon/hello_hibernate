package org.example.hello_hibernate;

import javax.persistence.*;

@Entity
@Table(name = "carpicture")
public class CarPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Car car;

    private String picture;

    public CarPicture() {
    }

    public CarPicture(Car car, String picture) {
        this.car = car;
        this.picture = picture;
    }

    public CarPicture(String picture) {
        this.picture = picture;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
