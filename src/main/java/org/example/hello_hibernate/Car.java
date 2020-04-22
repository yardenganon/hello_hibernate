package org.example.hello_hibernate;
import  javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person owner;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarPicture picture;

    @ManyToMany
    @JoinTable(
            name = "garage_car",
            joinColumns = @JoinColumn(name = "carsSupervising_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id",referencedColumnName = "id"))
    private List<Garage> garagesToBeFixedIn;

    private String licensePlate;
    private double price;
    private int year;

    public Car() {
    }

    public Car(Person owner, CarPicture picture, String licensePlate, double price, int year) {
        this.owner = owner;
        this.picture = picture;
        this.garagesToBeFixedIn = new ArrayList<Garage>();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
    }

    public Car(String licensePlate, double price, int year, Person owner) {
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.owner = owner;
        this.garagesToBeFixedIn = new ArrayList<Garage>();

    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        owner.addOwnedCar(this);
    }

    public CarPicture getPicture() {
        return picture;
    }

    public void setPicture(CarPicture picture) {
        this.picture = picture;
        picture.setCar(this);
    }

    public List<Garage> getGaragesToBeFixedIn() {
        return garagesToBeFixedIn;
    }

    public void addGaragesToBeFixedIn(List<Garage> garagesToBeFixedIn) {
        for (Garage garage : garagesToBeFixedIn) {
            if (garage!=null) {
                this.garagesToBeFixedIn.add(garage);
                garage.addCarSupervising(this);
            }
        }
    }
}
