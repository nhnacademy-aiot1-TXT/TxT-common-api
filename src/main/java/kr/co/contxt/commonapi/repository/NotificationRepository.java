package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Notification Table 접근을 위한 JpaRepository interface
 *
 * @author jongsikk
 * @version 1.0.0
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAll();

    /**
     * Find by role id list.
     *
     * @param roleId the role id
     * @return notification list
     */
    List<Notification> findByRole_Id(Long roleId);
}
