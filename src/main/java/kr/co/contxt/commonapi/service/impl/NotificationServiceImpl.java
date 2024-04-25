package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.NotificationRequest;
import kr.co.contxt.commonapi.dto.NotificationResponse;
import kr.co.contxt.commonapi.entity.Notification;
import kr.co.contxt.commonapi.repository.NotificationRepository;
import kr.co.contxt.commonapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Notification service 구현 클래스
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    /**
     * Notification 리스트 조회 메서드
     *
     * @return notification list
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(Notification::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 권한별 Notification 조회 메서드
     *
     * @param roleId the role id
     * @return roleId에 맞는 notification list
     */
    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getUserNotifications(Long roleId) {
        return notificationRepository.findByRole_RoleId(roleId)
                .stream()
                .map(Notification::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 알림 추가 메서드
     *
     * @param notificationRequest the notification request
     */
    @Override
    @Transactional
    public void createNotification(NotificationRequest notificationRequest) {
        Notification notification = notificationRequest.toEntity();
        notificationRepository.save(notification);
    }
}
