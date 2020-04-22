package org.example.hello_hibernate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import jdk.dynalink.beans.StaticClass;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App 
{

    private static List<Car> carsList;
    private static List<CarPicture> carPictureList;
    private static List<Person> personList;
    private static List<Garage> garageList;

    private static Session session;
    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add entities here
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(CarPicture.class);
        configuration.addAnnotatedClass(Garage.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void generatePersons() throws Exception {
        ArrayList<String> fNames = new ArrayList<String>(Arrays.asList("Yossi","Rami","Sergey","Nadav","Kobi","Robert","Avner","Leon","Benny"));
        ArrayList<String> lNames = new ArrayList<String>(Arrays.asList("Polak","Frotis","Kamedo","Buhan","Cohen","Levi","Bellik","Magen","Ziv"));
        for (int i = 0;i<9;i++)
        {
            String fName = fNames.get(i);
            String lName = lNames.get(i);
            Person person = new Person(fName,lName, "1234",fName+lName+"@gmail.com");
            session.save(person);
        }

    }
    private static void generateGarages() throws Exception {
        Garage garage = new Garage("Moshe Hogeg 10, Haifa");
        Garage garage1 = new Garage("Hanoh Rozen 23, Jerusalem");
        Garage garage2 = new Garage("Avraham Saknin 13, Tel-Aviv");

        session.save(garage);
        session.save(garage1);
        session.save(garage2);
    }

    private static void generateCars() throws Exception {
        Random random = new Random();
        for (int i = 0;i<10;i++)
        {
            Car car = new Car("MOO-" + random.nextInt(), 100000, 2000 +
                    random.nextInt(19),null);
            session.save(car);
        }
    }
    private static void generateCarsPictures() throws Exception {
        Random random = new Random();
        for (int i = 0;i<10;i++)
        {
            CarPicture carPicture = new CarPicture("http://" +String.valueOf(random.nextInt(Integer.MAX_VALUE)) + ".jpg");
            session.save(carPicture);
        }
    }

    public static <T> List<T> getAll(Class<T> object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }
    private static void printAllCarsPictures() throws  Exception {
        List<CarPicture> pictures = getAll(CarPicture.class);
        for (CarPicture picture : pictures)
        {
            System.out.println(picture.getPicture());
        }
    }
    private static void printAllPersons() throws  Exception {
        List<Person> persons = getAll(Person.class);
        for (Person person : persons)
        {
            System.out.println(person.getfName() + " " + person.getlName() + " " + person.getEmail());
        }
    }
    private static void printAllGarages() throws Exception {
        List<Garage> garages = getAll(Garage.class);
        for (Garage garage : garages)
        {
            System.out.println(garage.getAddress());
        }
    }
    private static void printAllCars() throws Exception {
        List<Car> cars = getAll(Car.class);
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

    private static <E> void updateEntities(List<? extends E> obj) throws Exception {
        for (E object : obj)
            session.update(object);
    }

    public static void main( String[] args )
    {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            generateCars();
            printAllCars();

            generatePersons();
            printAllPersons();

            generateGarages();
            printAllGarages();

            generateCarsPictures();
            printAllCarsPictures();

            // Bonds entities;
            carsList = getAll(Car.class);
            carPictureList = getAll(CarPicture.class);
            garageList = getAll(Garage.class);
            personList = getAll(Person.class);

            // car <-> person
            carsList.get(0).setOwner(personList.get(0));
            carsList.get(1).setOwner(personList.get(0));
            carsList.get(2).setOwner(personList.get(0));
            carsList.get(3).setOwner(personList.get(1));
            carsList.get(4).setOwner(personList.get(2));

            // car <-> carPicture
            for (int i = 0 ;i<10;i++)
                (carsList.get(i)).setPicture(carPictureList.get(i)); // Setting pictures

            // car <-> garage
            carsList.get(0).addGaragesToBeFixedIn(garageList.subList(0,2));
            carsList.get(1).addGaragesToBeFixedIn(garageList.subList(1,3));
            carsList.get(2).addGaragesToBeFixedIn(garageList.subList(0,1));
            carsList.get(3).addGaragesToBeFixedIn(garageList.subList(0,3));
            carsList.get(4).addGaragesToBeFixedIn(garageList.subList(2,3));


            // garage <-> person
            garageList.get(0).addGarageOwners(personList.subList(6,8));
            garageList.get(1).addGarageOwners(personList.subList(7,8));
            garageList.get(2).addGarageOwners(personList.subList(8,9));

            updateEntities(carsList);
            updateEntities(personList);
            updateEntities(carPictureList);
            updateEntities(garageList);

            session.getTransaction().commit();

            PrintData.printGaragesDetails(getAll(Garage.class));
            PrintData.printCarsDetails(getAll(Car.class));
        }
        catch (Exception exception){
            if (session != null)
                session.getTransaction().rollback();
            System.err.println("An error occurred, changes have been rolled back.");
            exception.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
