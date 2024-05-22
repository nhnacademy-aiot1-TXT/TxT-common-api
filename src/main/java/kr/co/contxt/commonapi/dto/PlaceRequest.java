package kr.co.contxt.commonapi.dto;

import kr.co.contxt.commonapi.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

/**
 * Place 요청 DTO 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceRequest {
    @NotBlank
    private String placeName;
    @NotNull
    private String placeCode;
    @NotNull
    private LocalTime cycle;

    /**
     * dto를 entity로 변환하는 메서드
     *
     * @return 장소 entity
     */
    public Place toEntity() {
        return Place.builder()
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();
    }
}
