package br.org.edu.ifrn.LojaCarro.model;

public enum UserRole {
    GERENTE("GERENTE"),
    VENDEDOR("VENDEDOR");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}