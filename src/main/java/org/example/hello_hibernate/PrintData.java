package org.example.hello_hibernate;


import java.util.List;

public class PrintData {

    public static void printGaragesDetails(List<Garage> garageList) {
        System.out.println("*********************************Garages*********************************");

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
    }

    public static void printCarsDetails(List<Car> carList) {
        System.out.println("**********************************Cars***********************************");

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
        }
    }
    public static void printAllCarsPictures() throws  Exception {
        List<CarPicture> pictures = App.getAll(CarPicture.class);
        for (CarPicture picture : pictures)
        {
            System.out.println(picture.getPicture());
        }
    }
    public static void printAllPersons() throws  Exception {
        List<Person> persons = App.getAll(Person.class);
        for (Person person : persons)
        {
            System.out.println(person.getfName() + " " + person.getlName() + " " + person.getEmail());
        }
    }
    public static void printAllGarages() throws Exception {
        List<Garage> garages = App.getAll(Garage.class);
        for (Garage garage : garages)
        {
            System.out.println(garage.getAddress());
        }
    }
    public static void printAllCars() throws Exception {
        List<Car> cars = App.getAll(Car.class);
        for (Car car : cars)
        {
            System.out.print("Id: ");
            System.out.print(car.getId());
            System.out.print(", License plate: ");
            System.out.print(car.getLicensePlate());
            System.out.print(", Price: ");
            System.out.print(car.getPrice());
            System.out.print(", Year: ");
            System.out.print(car.getYear());
            System.out.print('\n');        }
    }
}
