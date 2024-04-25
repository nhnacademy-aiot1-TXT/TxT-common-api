package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * Notification Rest Controller 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/notification")
@Tag(name = "Notification Rest Controller", description = "알림 API")
public class NotificationRestController {
    private final UserAdapter userAdapter;
    private final NotificationService notificationService;

    /**
     * 권한별 알림 리스트 조회 api
     *
     * @param id userId
     * @return List<Notification>
     */
    @GetMapping
    @Operation(summary = "알림 리스트 조회")
    public ResponseEntity<List<NotificationResponse>> getNotifications(@RequestHeader(value = "X-USER-ID") String id) {
        RoleResponse role = userAdapter.getRoleData(id);
        List<NotificationResponse> notifications =
                role.getRoleId() == 1L ? notificationService.getAllNotifications() : notificationService.getUserNotifications(role.getRoleId());

        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }

    /**
     * 알림 추가 api
     *
     * @param notificationRequest the notification request
     * @return the response entity
     */
    @PostMapping
    @Operation(summary = "알림 추가")
    public ResponseEntity<Void> addNotification(@RequestBody NotificationRequest notificationRequest) {
        notificationService.createNotification(notificationRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}