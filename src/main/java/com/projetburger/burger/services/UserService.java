package com.projetburger.burger.services;



import com.projetburger.burger.models.Role;
import com.projetburger.burger.models.User;
import com.projetburger.burger.models.Gestionnaire;

import com.projetburger.burger.repositories.GestionnaireRepository;
import com.projetburger.burger.repositories.RoleRepository;
import com.projetburger.burger.repositories.UserRepository;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log
@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;

    }


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Gestionnaire findAdminByEmail(String email) {
        return gestionnaireRepository.findByEmail(email);
    }

    public Role findRoleByLibelle(String libelle) {
        return roleRepository.findByLibelle(libelle);
    }

    public User saveUser(User user) {
        try {
            userRepository.save(user);
            return user;
        } catch(Exception e) {
            throw e;
        }
    }

    public Role addRole(Role role) {
        try {
            roleRepository.save(role);
            return role;
        } catch(Exception e) {
            log.severe(e.getMessage());
            throw e;
        }
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }
}
