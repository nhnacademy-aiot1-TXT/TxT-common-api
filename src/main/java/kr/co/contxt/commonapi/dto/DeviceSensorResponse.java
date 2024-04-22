package kr.co.contxt.commonapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSensorResponse {
    private Float onValue;
    private Float offValue;
}
