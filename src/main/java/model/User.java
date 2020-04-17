package model;

public class User {
    long id;
    int age;
    String name;
    String password;

    public User(long id, int age, String name, String password) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public User(int age, String name, String password) {
        this.age = age;
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
