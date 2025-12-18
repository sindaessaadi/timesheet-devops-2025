package tn.esprit.spring.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Role;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")   // 🔥 TRÈS IMPORTANT
@TestMethodOrder(OrderAnnotation.class)
@Disabled("Disabled because it requires DB connection")
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    private static String userId;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDateNaissance(new Date());
        user.setRole(Role.INGENIEUR);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        userId = savedUser.getId(); // ⚠️ String, PAS long
    }

    @Test
    void testGetUserById() {
        User user = userService.retrieveUser(userId);
        assertNotNull(user);
        assertEquals("John", user.getFirstName());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = userService.retrieveAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(userId);
        User user = userService.retrieveUser(userId);
        assertNull(user);
    }
}
