package kr.co.contxt.commonapi.controller;

import kr.co.contxt.commonapi.adapter.UserAdapter;
import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;
import kr.co.contxt.commonapi.dto.RoleResponse;
import kr.co.contxt.commonapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/notification")
public class NotificationRestController {
    private final UserAdapter userAdapter;
    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getNotifications(@RequestHeader(value = "X-USER-ID") String id) {
        RoleResponse role = userAdapter.getRoleData(id);
        List<NotificationResponse> notifications =
                role.getId() == 1L ? notificationService.getAllNotifications() : notificationService.getUserNotifications(role.getId());

        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }

    @PostMapping
    public ResponseEntity<Void> addNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.createNotification(notificationRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}