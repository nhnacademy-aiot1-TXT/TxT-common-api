package kr.co.contxt.commonapi.adapter;

import kr.co.contxt.commonapi.dto.RoleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "user-management")
public interface UserAdapter {
    @GetMapping("/api/user/role")
    RoleResponse getRoleData(@RequestHeader("X-USER-ID") String id);
}
