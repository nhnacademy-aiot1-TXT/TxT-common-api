package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.PlaceResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * Place Entity
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "place")
public class Place {
    @Id
    @Column(name = "place_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;
    @Column(name = "place_name")
    private String placeName;
    @Column(name = "place_code")
    private String placeCode;
    @Column
    private LocalTime cycle;

    /**
     * 장소 entity를 센서 정보 dto로 변환해주는 메서드
     *
     * @return 센서 정보 dto
     */
    public PlaceResponse toDto() {
        return PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();
    }
}
