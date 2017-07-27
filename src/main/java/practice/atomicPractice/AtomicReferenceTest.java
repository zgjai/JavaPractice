package practice.atomicPractice;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by zhangguijiang on 2017/7/27.
 */
public class AtomicReferenceTest {

    private static AtomicReference<User> userAtomicReference = new AtomicReference<>();

    public static void main(String[] args) {
        AtomicReferenceTest test = new AtomicReferenceTest();
        User user = new User("user", 3);
        userAtomicReference.set(user);
        System.out.println(userAtomicReference.get().toString());
        User user1 = new User("user1", 10);
        User user2 = new User("user2", 20);
        new Thread(() -> cas(user, user1)).start();
        new Thread(() -> cas(user, user2)).start();
    }

    private static void cas(User oUser, User nUser) {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + userAtomicReference.compareAndSet(oUser, nUser)
                + " cur v: " + userAtomicReference.get().toString());
    }

    private static class User {
        private String name;
        private int age;

        private User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + "name='" + name + '\'' + ", age=" + age + '}';
        }
    }
}
