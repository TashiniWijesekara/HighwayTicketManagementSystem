package lk.ijse.userservice.controller;

import lk.ijse.userservice.dto.UserDTO;
import lk.ijse.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @GetMapping("/health")
    public String healthTest() {
        return "User Health Test";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        logger.info("Saving user details");
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            UserDTO savedUser = userService.saveUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (Exception exception) {
            logger.error("Error saving User: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | User saved Unsuccessfully.\nMore Details\n" + exception);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        logger.info("Fetching all Users");
        try {
            List<UserDTO> userDTOList = userService.getAllUser();
            return ResponseEntity.ok(userDTOList);
        } catch (Exception exception) {
            logger.error("Error fetching all Users: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch Users.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        logger.info("Fetching user with ID: {}", userId);
        try {
            UserDTO userDTO = userService.getSelectedUser(userId);
            return ResponseEntity.ok(userDTO);
        } catch (Exception exception) {
            logger.error("Error fetching user by ID: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to fetch user.\nMore Details\n" + exception);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        logger.info("Updating user with ID: {}", userId);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        try {
            userService.updateUser(userId, userDTO);
            return ResponseEntity.ok("User updated successfully !");
        } catch (Exception exception) {
            logger.error("Error updating user: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to update user.\nMore Details\n" + exception);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        logger.info("Deleting user with ID: {}", userId);
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User deleted successfully !");
        } catch (Exception exception) {
            logger.error("Error deleting user: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to delete user.\nMore Details\n" + exception);
        }
    }

    @GetMapping("/userExists/{userId}")
    public ResponseEntity<?> isUserExists(@PathVariable String userId) {
        logger.info("Checking user existence with ID: {}", userId);
        try {
            boolean isUserExists = userService.isUserExists(userId);
            logger.info("User Exists: {}", isUserExists);
            return ResponseEntity.ok(isUserExists);
        } catch (Exception exception) {
            logger.error("Error checking user existence: ", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error | Unable to check user existence.\nMore Details\n" + exception);
        }
    }
}
