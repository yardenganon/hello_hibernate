package org.example.hello_hibernate;


import java.util.List;

public class PrintData {

    public static void printGaragesDetails(List<Garage> garageList) {
        System.out.println("*************************************************************************");
        System.out.println("*********************************Garages*********************************");
        System.out.println("*************************************************************************");

        for (Garage garage : garageList) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Garage ID: " + garage.getId() + " | Address: " + garage.getAddress());
            System.out.println("Owners: ");
            for (Person person : garage.getGarageOwners())
                System.out.println(person.getfName() + " " + person.getlName());
            System.out.println("Cars to maintain/repair:");
            for (Car car : garage.getCarsSupervising())
                System.out.println(car.getLicensePlate());
            System.out.println("----------------------------------------------------------------------");
        }
        System.out.println("*************************************************************************");
    }

    public static void printCarsDetails(List<Car> carList) {
        System.out.println("*************************************************************************");
        System.out.println("**********************************Cars***********************************");
        System.out.println("*************************************************************************");

        for (Car car : carList) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Car ID: " + car.getId() + " | Owner: " + (car.getOwner() == null?"No Owner":car.getOwner().getfName())+ " " + (car.getOwner() == null?"":car.getOwner().getlName()));
            System.out.println("Garages to be maintained/repaired in:");
            if (!car.getGaragesToBeFixedIn().isEmpty()) {
                for (Garage garage : car.getGaragesToBeFixedIn())
                    System.out.println(garage.getAddress());
            } else {
                System.out.println("NO CARS");
                System.out.println("----------------------------------------------------------------------");
            }
            System.out.println("*************************************************************************");
        }
    }
}
