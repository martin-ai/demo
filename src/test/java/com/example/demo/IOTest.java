package com.example.demo;

import java.io.*;

public class IOTest {

    public static void main(String[] args) {
        writeObj();
        readObj();
    }

    public static void readObj() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("xx.txt"))) {
            Object o = ois.readObject();
            User user = (User) o;
            System.out.println(user);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void writeObj() {
        User u = new User("1", "ss");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("xx.txt"))) {
            oos.writeObject(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *Serializable：一个对象序列化的接口，一个类只有实现了Serializable接口，它的对象才是可序列化的。
     *而实际上，Serializable是一个空接口，没有什么具体内容，它的目的只是简单的标识一个类的对象可以被序列化。
     *
     * 好处：
     * a)比如说你的内存不够用了，那计算机就要将内存里面的一部分对象暂时的保存到硬盘中，
     * 等到要用的时候再读入到内存中，硬盘的那部分存储空间就是所谓的虚拟内存。
     * 在比如过你要将某个特定的对象保存到文件中，我隔几天在把它拿出来用，那么这时候就要实现Serializable接口；
     * b)在进行java的Socket编程的时候，你有时候可能要传输某一类的对象，那么也就要实现Serializable接口；
     * 最常见的你传输一个字符串，它是JDK里面的类，也实现了Serializable接口，所以可以在网络上传输。
     * c)如果要通过远程的方法调用（RMI）去调用一个远程对象的方法，如在计算机A中调用另一台计算机B的对象的方法，
     * 那么你需要通过JNDI服务获取计算机B目标对象的引用，将对象从B传送到A，就需要实现序列化接口。
     *
     * 说明：
     * Serializable主要作用将类的实例持久化保存，序列化就是保存，反序列化就是读取。
     * 保存也不一定保存在本地，也可以保存到远方。类一定要实现Serializable才可以
     * 如果没有实现
     * Caused by: java.io.NotSerializableException: com.example.demo.IOTest$User
     * */
    protected static class User implements Serializable {
        private String code;
        private String name;

        public User(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}


