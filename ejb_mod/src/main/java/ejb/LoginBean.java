package ejb;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.Random;

@Stateless(name = "LoginEJB")
public class LoginBean implements LoginBeanLocal {
        @PersistenceContext(name = "WebshopUnit")
        EntityManager entityManager;

    private String name;

    public LoginBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean validateUser(String userName, String password) {
        return false;
    }

    @Override
    public String test(String name) {
        print();
        return "Hello " +  name + "!";
    }

    public void print() {
        Person person = new Person();

        final int x = new Random().nextInt(100);
        person.setName(String.valueOf(x));
        entityManager.persist(person);
        final Query q = entityManager.createQuery("select o from Person o");
        System.out.println("--");
        final Person person1 = (Person) q.getResultList().get(q.getResultList().size() - 1);
        System.out.println(q.getResultList().size());
        System.out.println(person1.getName());
        System.out.println("--");
    }
}

@Entity
@Table
class Person {
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

