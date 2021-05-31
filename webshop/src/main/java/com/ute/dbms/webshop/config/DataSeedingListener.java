package com.ute.dbms.webshop.config;

import com.ute.dbms.webshop.entity.Role;
import com.ute.dbms.webshop.entity.User;
import com.ute.dbms.webshop.entity.UserInfo;
import com.ute.dbms.webshop.repository.RoleRepository;
import com.ute.dbms.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeedingListener implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String...args) throws Exception{
        // Roles

        if (roleRepository.findByRoleName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
        if (roleRepository.findByRoleName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

            // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User admin = new User();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(passwordEncoder.encode("12345678"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRoleName("ROLE_ADMIN"));
            roles.add(roleRepository.findByRoleName("ROLE_MEMBER"));
            admin.setRoles(roles);
            UserInfo userInfo = new UserInfo("admin","0989542812","Hiệp bình chánh thủ đức");
            userInfo.setUser(admin);
            admin.setUserInfo(userInfo);
            userRepository.save(admin);
            }
    }
}
