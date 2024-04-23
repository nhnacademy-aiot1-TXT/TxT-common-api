package kr.co.contxt.commonapi.adapter;

import kr.co.contxt.commonapi.dto.RoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * Notification Api 에서 사용하는 유저의 권한을 가져오는 FeignClient interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
@FeignClient(value = "user-management")
public interface UserAdapter {
    /**
     * RoleId를 가져오는 메서드
     *
     * @param id userId
     * @return role data
     */
    @GetMapping("/api/user/role")
    RoleResponse getRoleData(@RequestHeader("X-USER-ID") String id);
}
