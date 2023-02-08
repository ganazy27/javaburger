package com.projetburger.burger.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.projetburger.burger.models.Gestionnaire;
import com.projetburger.burger.models.User;
import com.projetburger.burger.models.Role;
import com.projetburger.burger.services.UserService;


@Component
public class UserFixture {
    @Autowired
    private UserService userService;

    @Autowired
   private BCryptPasswordEncoder encoder;

    public void fixtureUsers(){
        if (userService.findAllUser().size() <= 0) {
            defaultGestionnaire();
        }
    }

    public void defaultGestionnaire() {
        User user = new Gestionnaire();
        user.setEmail("gestionnaire@gmail.com");
        user.setNom("gestionnaire");
        user.setPrenom("gestionnaire");
        user.setPassword(encoder.encode("passer"));
        Role role = userService.findRoleByLibelle("GESTIONNAIRE");
        if (role == null) {
            role = new Role();
            role.setLibelle("GESTIONNAIRE");
            role = userService.addRole(role);
            
        }
        user.setRole(role);
        userService.saveUser(user);
        System.out.println(" user added succesfully");
    }
 
    
}

