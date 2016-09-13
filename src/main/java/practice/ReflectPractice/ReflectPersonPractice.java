package practice.ReflectPractice;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by zhangguijiang on 16/6/29.
 */
public class ReflectPersonPractice {
    public static void main(String[] args) {
        Class<?> personType = Person.class;
        Person person1 = null;
        Person person2 = null;
        try {
            person1 = (Person) personType.newInstance();
            person1.setAll("zgj", 24, "man");
            person1.printInfo();
            Constructor[] constructors = personType.getConstructors();
            person1 = (Person) constructors[0].newInstance();
            person2 = (Person) constructors[1].newInstance("rsj", 20, "female");
            person1.printInfo();
            person2.printInfo();

            Method method1 = personType.getMethod("sayHello");
            method1.invoke(personType.newInstance());

            Method method2 = personType.getMethod("saySomething", String.class);
            method2.invoke(personType.newInstance(), "zgj");

            Method method3 = personType.getMethod("sayNameAndAge", String.class, Integer.class);
            method3.invoke(personType.newInstance(), "zgj", 26);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
