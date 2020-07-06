package com.hitesh.rest.webservices.restfulwebservices;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {
    private static List<User> userList = new ArrayList<User>();
    private static int counter = 0;

    static {
        userList.add(new User(counter++, "A", new Date()));
        userList.add(new User(counter++, "B", new Date()));
        userList.add(new User(counter++, "C", new Date()));
        userList.add(new User(counter++, "D", new Date()));
        userList.add(new User(counter++, "E", new Date()));
        userList.add(new User(counter++, "F", new Date()));
        userList.add(new User(counter++, "G", new Date()));
    }

    public List<User> findAll() {
        return userList;
    }

    public User save(User newuser) {
        for (User user : userList) {
            if (user.getId() == newuser.getId())
                newuser.setId(counter++);
        }
        userList.add(newuser);
        counter++;
        return newuser;
    }

    public boolean isUserDeleted(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                userList.remove(user);
                counter--;
                return true;

            }
        }
        return false;

    }

    public User getUser(int id) {
        for (User user : userList) {
            if (user.getId() == id)
                return user;
        }
        return null;

    }

    public int userCount() {
        return counter;
    }
}
