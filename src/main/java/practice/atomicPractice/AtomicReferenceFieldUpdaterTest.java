package practice.atomicPractice;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by zhangguijiang on 2017/7/27.
 */
public class AtomicReferenceFieldUpdaterTest {

    private static final AtomicReferenceFieldUpdater<User, String> fieldUpdater = AtomicReferenceFieldUpdater
            .newUpdater(User.class, String.class, "name");

    public static void main(String[] args) throws Exception {
        User user = new User("a", 10);
        System.out.println(user);
        Thread thread1 = new Thread(() -> System.out.println(fieldUpdater.compareAndSet(user, "a", "c")));
        Thread thread2 = new Thread(() -> System.out.println(fieldUpdater.compareAndSet(user, "a", "b")));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(user);
    }

    private static class User {
        volatile String name;
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
