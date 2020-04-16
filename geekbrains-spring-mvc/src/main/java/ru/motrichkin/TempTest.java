package ru.motrichkin;

import org.hibernate.cfg.Configuration;
import ru.motrichkin.persistence.CustomerOrder;
import ru.motrichkin.persistence.Person;
import ru.motrichkin.persistence.Position;
import ru.motrichkin.persistence.Product;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TempTest {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        List<Person> persons;
        List<Product> products;
        List<Position> positions;

//        Person person = new Person("Valeriy", "Kipelov", LocalDate.of(1977, 2, 12), Collections.EMPTY_LIST);
//        putEntity(em, person);
//        Product product = new Product("Shure Microphone", 1400, Collections.EMPTY_LIST);
//        putEntity(em, product);

        putSimpleCustomerOrder(em, 4, 2, 3, 1, 1);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        persons = em.createQuery("from Person").getResultList();
        persons.forEach(System.out::println);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        products = em.createQuery("from Product").getResultList();
        products.forEach(System.out::println);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        positions = em.createQuery("from Position").getResultList();
        positions.forEach(System.out::println);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<CustomerOrder> customerOrders = em.createQuery("from CustomerOrder").getResultList();
        customerOrders.forEach(System.out::println);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        whatPersonBought(em, 5);
        whoBoughtTheProduct(em, 3);

        em.close();
    }

    public static void putEntity(EntityManager em, Object object) {
        em.getTransaction().begin();

        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        System.out.println(object);

        Object persistedObject = em.find(Person.class, 2L);
        System.out.println(persistedObject);
    }

    public static void putSimpleCustomerOrder(EntityManager em, Integer personId, Integer ... productIds) {

        List<Person> persons;
        List<Product> products;
        List<Position> positions;

        persons = em.createQuery("from Person p where p.id = " + personId).getResultList();
        positions = new ArrayList<>();
        for(Integer productId : productIds) {
            products = em.createQuery("from Product p where p.id = " + productId).getResultList();
            positions.add(new Position(products.get(0), 1, products.get(0).getCost()));
        }
        CustomerOrder customerOrder = new CustomerOrder(LocalDate.now(), 88, positions, persons.get(0));
        putEntity(em, customerOrder);
        em.getTransaction().begin();
    }

    public static void whatPersonBought(EntityManager em, Integer personId) {
        List<CustomerOrder> customerOrders;
        customerOrders = em.createQuery("from CustomerOrder o where o.person.id = " + personId).getResultList();
        customerOrders.forEach(System.out::println);
    }

    public static void whoBoughtTheProduct(EntityManager em, Integer productId) {
        List<Person> persons; // пока не работает
        persons = em.createQuery("select pos.customerOrder.person from Position pos where pos.product.id = " + productId).getResultList();
        System.out.println(persons.size());
        persons.forEach(System.out::println);
    }

}
