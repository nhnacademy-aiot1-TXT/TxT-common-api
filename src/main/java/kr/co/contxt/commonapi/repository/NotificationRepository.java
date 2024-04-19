package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAll();

    List<Notification> findByRole_Id(Long roleId);
}
