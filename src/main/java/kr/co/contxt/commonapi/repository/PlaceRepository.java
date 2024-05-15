package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Place Table 접근을 위한 JpaRepository interface
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface PlaceRepository extends JpaRepository<Place, Long> {
    /**
     * 장소 이름이 존재하는지 확인하는 메서드
     *
     * @param placeName 장소 이름
     * @return 존재 여부
     */
    boolean existsByPlaceName(String placeName);
}
