package org.example.hello_hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fName;
    private String lName;

    private String password;
    private String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Car> carsOwnedBy;

    @ManyToMany(mappedBy = "garageOwners", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Garage> garagesOwnedBy;

    public Person() {
    }

    public Person(String fName, String lName, String password, String email) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
        this.carsOwnedBy = new ArrayList<Car>();
        this.garagesOwnedBy = new ArrayList<Garage>();


    }

    public Person(String fName, String lName, String password, String email, List<Car> carsOwnedBy) {
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
        this.carsOwnedBy = carsOwnedBy;
        this.garagesOwnedBy = new ArrayList<Garage>();
    }

    public void setCarsOwnedBy(List<Car> carsOwnedBy) {
        this.carsOwnedBy = carsOwnedBy;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getCarsOwnedBy() {
        return carsOwnedBy;
    }

    public void addOwnedCar(Car car) {
        this.carsOwnedBy.add(car);
    }
    public void addOwnedGarage(Garage garage) {
        garagesOwnedBy.add(garage);
    }
    public List<Garage> getGaragesOwnedBy() {
        return garagesOwnedBy;
    }

    public void setGaragesOwnedBy(List<Garage> garagesOwnedBy) {
        this.garagesOwnedBy = garagesOwnedBy;
    }
}
