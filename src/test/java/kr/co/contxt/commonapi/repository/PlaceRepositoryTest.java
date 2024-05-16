package kr.co.contxt.commonapi.repository;

import kr.co.contxt.commonapi.entity.Place;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlaceRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private PlaceRepository placeRepository;

    @Test
    void existsByPlaceName() {
        String placeName = "test place";
        LocalTime cycle = LocalTime.of(7, 0);
        Place place = Place.builder()
                .placeName(placeName)
                .cycle(cycle)
                .build();

        entityManager.persist(place);

        boolean result = placeRepository.existsByPlaceName(placeName);

        assertTrue(result);
    }
}