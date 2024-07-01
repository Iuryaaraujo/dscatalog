package com.devsuperior.dscatalog.projections;

public interface UserDetailsProjection {

    // Consulta do meu UserRepository searchUserAndRolesByEmail
    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}
