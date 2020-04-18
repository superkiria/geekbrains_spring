package ru.motrichkin;

import org.hibernate.cfg.Configuration;
import ru.motrichkin.persistence.CustomerOrder;
import ru.motrichkin.persistence.Person;
import ru.motrichkin.persistence.Position;
import ru.motrichkin.persistence.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TempTest {


    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = factory.createEntityManager();
//        Person person = new Person("Valeriy", "Kipelov", LocalDate.of(1977, 2, 12), Collections.EMPTY_LIST);
//        putEntity(em, person);
//        Product product = new Product("Shure Microphone", 1400, Collections.EMPTY_LIST);
//        putEntity(em, product);
//        putSimpleCustomerOrder(em, 7, 9);
        printAll(em, Person.class);
        printAll(em, Product.class);
        printAll(em, CustomerOrder.class);
        printAll(em, Position.class);
        System.out.println();
//        whatPersonHasBought(em, 7);
//        whoBoughtTheProduct(em, 9);
        whatPriceWasForWhom(em);
        em.close();
    }

    public static void printAll(EntityManager em, Class cl) {
        printResultList(em, "from " + cl.getName());
    }

    public static void printResultList(EntityManager em, String query) {
        List results = em.createQuery(query).getResultList();
        results.forEach(System.out::println);
    }

    public static void whatPersonHasBought(EntityManager em, Integer personId) {
        printResultList(em,"from CustomerOrder o where o.person.id = " + personId);
    }

    public static void whoBoughtTheProduct(EntityManager em, Integer productId) {
        printResultList(em,"select c.person from CustomerOrder as c, Position as p where p.product.id = " + productId);
    }

    public static void whatPriceWasForWhom(EntityManager em) {
        List results = em.createQuery("select c.person, p.price from CustomerOrder as c, Position as p").getResultList();
        results.forEach(o -> {
            System.out.println(Arrays.toString((Object[]) o));
        });
    }

    public static void putEntity(EntityManager em, Object object) {
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    public static void putSimpleCustomerOrder(EntityManager em, Integer personId, Integer ... productIds) {
        Person person = (Person) em.createQuery("from Person p where p.id = " + personId).getSingleResult();
        List<Position> positions = new ArrayList<>();
        CustomerOrder customerOrder = new CustomerOrder(LocalDate.now(), 88, positions, person);
        for(Integer productId : productIds) {
            Product product = (Product) em.createQuery("from Product p where p.id = " + productId).getSingleResult();
            positions.add(new Position(customerOrder, product, 1, product.getCost()));
        }
        customerOrder.setPositionList(positions);
        putEntity(em, customerOrder);
    }

    public static void delete(EntityManager em, Class entity, Long id) {
        em.getTransaction().begin();
        try {
            em.remove(em.find(entity, id));
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            ex.printStackTrace();
        }
    }
}
