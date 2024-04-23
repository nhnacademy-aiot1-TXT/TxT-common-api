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

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(Notification::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationResponse> getUserNotifications(Long roleId) {
        return notificationRepository.findByRole_Id(roleId)
                .stream()
                .map(Notification::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createNotification(NotificationRequest notificationRequest) {
        Notification notification = notificationRequest.toEntity();
        notificationRepository.save(notification);
    }
}
