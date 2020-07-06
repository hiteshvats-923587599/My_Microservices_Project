package com.hitesh.rest.webservices.restfulwebservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    HelloBean helloBean;

    @Autowired
    UserDao userDao;

    @GetMapping(value = "/hello")
    public HelloBean sayHello() {
        helloBean.setMessage("Hey Developer");
        return helloBean;
    }

    @GetMapping(value="hello/{name}")
    public HelloBean sayHelloTo(@PathVariable String name)
    {
        helloBean.setMessage(String.format("Hello %s",name ));

        return helloBean;
    }

    @GetMapping(value="user/{id}")
    public User getuser(@PathVariable int id)
    {
        if(userDao.getUser(id)!=null)
       return userDao.getUser(id);
        else
            throw  new UserNotFoundException( String.format("id : %s",(id)));
    }

    @GetMapping(value="/user/*")
    public List<User> findAll()
    {
        return userDao.findAll();
    }

    @PostMapping(value="/user/")
    public ResponseEntity<Object> AddUSer(@Valid @RequestBody User user)
    {
        User userSaved =userDao.save(user);

       URI location= ServletUriComponentsBuilder
               .fromCurrentRequestUri()
               .path("{id}")
               .buildAndExpand(userSaved.getId())
               .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "user/count")
    public int userCount()
    {
        return userDao.userCount();
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable int id)
    {
        if(userDao.isUserDeleted(id))
        return ResponseEntity.accepted().build();
        else
              throw new UserNotFoundException(String.format("id :",id));

    }

}
