package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Notification;
import kr.co.contxt.commonapi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    LocalDateTime time;
    Long roleId;
    String contents;

    public Notification toEntity() {
        return Notification.builder()
                .role(new Role(roleId, null))
                .contents(contents)
                .time(time)
                .build();
    }
}
