package kr.co.contxt.commonapi.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 사용자 권한 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_role")
public class Role {
    @Id
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    @Builder
    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
