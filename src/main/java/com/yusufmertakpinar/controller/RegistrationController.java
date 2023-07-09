package com.yusufmertakpinar.controller;

import com.yusufmertakpinar.model.dto.RegistrationDto;
import com.yusufmertakpinar.service.LoginRequest;
import com.yusufmertakpinar.model.entity.RegistrationEntity;
import com.yusufmertakpinar.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/")
public class RegistrationController {

    private final RegistrationService service;

    // SHOWS REGISTER PAGE
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    // SHOW LOGIN PAGE
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    // GET USER BY ID, RESPONSE -> JSON
    @GetMapping("/get-user")
    @ResponseBody
    public RegistrationDto getUserById(@RequestParam Integer id) {
        return this.service.getUserById(id);
    }

    // GET ALL USERS IN THE DB, DISPLAY IN TABLE
    @GetMapping("/get-all-users")
    public String getAllUsers(Model model) {
        List<RegistrationEntity> users = this.service.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // CREATE USER, RequestBody -> JSON, Response -> JSON (STATUS OF THE CURRENT REQUEST)
    @PostMapping("/create-user")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> createUser(@RequestBody RegistrationDto dto) {
        boolean createResponse = this.service.createUser(dto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", createResponse);
        return ResponseEntity.ok(response);
    }

    // UPDATE USER BY ID, RequestBody -> JSON, Response -> JSON
    @PostMapping("/update-user")
    @ResponseBody
    public boolean updateUser(@RequestBody RegistrationDto dto) {
        return this.service.createUser(dto);
    }

    // DELETE USER BY ID, RESPONSE -> VOID
    @GetMapping("/delete-user")
    @ResponseBody
    public void deleteUser(@RequestParam Integer id) {
        this.service.deleteUser(id);
    }

    // VALIDATE USER CREDENTIALS, RESPONSE -> JSON (boolean::status)
    @PostMapping("/validate-login")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> validateLogin(@RequestBody LoginRequest loginRequest) {
        boolean authResponse = this.service.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", authResponse);
        return ResponseEntity.ok(response);
    }

}
