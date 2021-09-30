package com.chatapplicationspringBoot.Model;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id; //User ID
    @Column(nullable = false, unique = true)
    private String firstName; //User First Name
    @Column(nullable = false)
    private String lastName;//User Last Name
    @Column(nullable = false,unique = true)
    private String email;//User email
    private int age;//User age
    @Column(nullable = true)
    private String dob;//User date of birth
    @Column(nullable = false)
    private String password; //User Password

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private List<Chat> chat;

    public User() {
    }

    public User(long uid, String firstName, String lastName, String email, int age, String dob, String password) {
        this.id = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.age= age;
        this.dob=dob;
        this.password=password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chat> getChat() {
        return chat;
    }

    public void setChat(List<Chat> chat) {
        this.chat = chat;
    }
}
