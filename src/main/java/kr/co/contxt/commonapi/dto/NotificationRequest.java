package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Notification;
import kr.co.contxt.commonapi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Notification 저장을 위한 RequestDTO
 *
 * @author jongsikk
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    @NotNull
    LocalDateTime time;
    @NotNull
    Long roleId;
    @NotBlank
    String contents;

    /**
     * To entity notification.
     *
     * @return the notification
     */
    public Notification toEntity() {
        return Notification.builder()
                .role(new Role(roleId, null))
                .contents(contents)
                .time(time)
                .build();
    }
}
