package kr.co.contxt.commonapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {
    @Test
    void roleEntityTest() {
        Role role = Role.builder()
                .roleId(1L)
                .roleName("ADMIN")
                .build();

        assertEquals(role.getRoleId(), 1L);
        assertEquals(role.getRoleName(), "ADMIN");
    }
}