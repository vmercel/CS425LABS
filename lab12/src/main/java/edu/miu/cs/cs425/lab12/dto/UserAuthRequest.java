package edu.miu.cs.cs425.lab12.dto;

public class UserAuthRequest {
    private String email;
    private String password;

    public UserAuthRequest() {}

    public UserAuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}