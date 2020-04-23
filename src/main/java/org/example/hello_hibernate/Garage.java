package org.example.hello_hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "garage")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String address;

    @ManyToMany
    @JoinTable(
            name = "person_garage",
            joinColumns = @JoinColumn(name = "garagesOwnedBy_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id",referencedColumnName = "id"))
    private List<Person> garageOwners;

    @ManyToMany(mappedBy = "garagesToBeFixedIn",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> carsSupervising;


    public Garage() {
    }

    public Garage(String address) {
        this.address = address;
        this.garageOwners = new ArrayList<Person>();
        this.carsSupervising = new ArrayList<Car>();
    }

    public Garage(String address, List<Car> carsSupervising) {
        this.address = address;
        this.garageOwners = new ArrayList<Person>();
        this.carsSupervising = carsSupervising;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Person> getGarageOwners() {
        return garageOwners;
    }

    public void setGarageOwners(List<Person> garageOwners) {
        this.garageOwners = garageOwners;
    }

    public List<Car> getCarsSupervising() {
        return carsSupervising;
    }

    public void setCarsSupervising(List<Car> carsSupervising) {
        this.carsSupervising = carsSupervising;
    }
    public void addGarageOwner(Person person){
        garageOwners.add(person);

    }
    public void addGarageOwners(List<Person> persons){
        for (Person person : persons) {
            addGarageOwner(person);
            person.addOwnedGarage(this);
        }
    }

    public void addCarSupervising(Car car) {
        carsSupervising.add(car);
    }
}

