package com.example.jpa;

import com.example.jpa.model.Gender;
import com.example.jpa.model.User;
import com.example.jpa.model.UserProfile;
import com.example.jpa.repository.UserProfileRepository;
import com.example.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class JpaOneToOneDemoApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaOneToOneDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Clean up database tables
        userProfileRepository.deleteAllInBatch();
        userRepository.deleteAllInBatch();

        //=========================================

        // Create a User instance
        User user = new User("Paul", "Su", "paulsu@gmail.com", "PASSWORD");
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1992, 03, 21);

        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("+1-8197882053", Gender.MALE, dateOfBirth.getTime(),
                "address1", "address2", "street", "Seattle", "Washington", "Unite State", "98109");

        // Set child reference(userProfile) in parent entity(user)
        user.setUserProfile(userProfile);

        // Set parent reference(user) in child entity(userProfile)
        userProfile.setUser(user);

        // Save parent reference (which will save the child as well)
        userRepository.save(user);
    }
}
