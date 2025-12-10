package tn.esprit.spring.services;

import tn.esprit.spring.entities.User;
import tn.esprit.spring.entities.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    private static User testUser;

    @Test
    @Order(1)
    void testCreateUser() {
        testUser = new User();
        testUser.setFirstName("John");
        testUser.setLastName("Doe");
        testUser.setDateNaissance(new Date());
        testUser.setRole(Role.INGENIEUR); // adapte selon ton enum Role

        User savedUser = userService.addUser(testUser);
        assertNotNull(savedUser.getId(), "L'utilisateur doit être enregistré et avoir un ID");
    }

    @Test
    @Order(2)
    void testGetUserById() {
        User foundUser = userService.retrieveUser(testUser.getId());
        assertEquals("John", foundUser.getFirstName());
        assertEquals("Doe", foundUser.getLastName());
    }

    @Test
    @Order(3)
    void testGetAllUsers() {
        List<User> users = userService.retrieveAllUsers();
        assertTrue(users.size() > 0, "Il doit y avoir au moins un utilisateur dans la base");
    }

    @Test
    @Order(4)
    void testDeleteUser() {
        userService.deleteUser(testUser.getId());
        assertNull(userService.retrieveUser(testUser.getId()), "L'utilisateur doit être supprimé");
    }
}
