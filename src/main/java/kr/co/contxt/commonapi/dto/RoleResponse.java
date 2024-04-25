package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Role 응답 DTO 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {
    Long roleId;
    String roleName;
}
