package com.yusufmertakpinar.service;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
