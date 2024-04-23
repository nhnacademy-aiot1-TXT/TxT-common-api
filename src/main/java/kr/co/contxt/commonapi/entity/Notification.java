package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.NotificationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Notification Entity
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notifications")
public class Notification {
    @Id
    @Column(name = "notification_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column
    private String contents;
    @Column
    private LocalDateTime time;

    /**
     * To dto notification response.
     *
     * @return the notification response
     */
    public NotificationResponse toDto() {
        return NotificationResponse.builder()
                .roleId(role.getId())
                .time(time)
                .contents(contents)
                .build();
    }
}
