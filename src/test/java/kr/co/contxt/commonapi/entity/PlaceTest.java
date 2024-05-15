package kr.co.contxt.commonapi.entity;

import kr.co.contxt.commonapi.dto.PlaceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaceTest {
    @Test
    void placeEntityTest() {
        Place place = Place.builder()
                .placeId(1L)
                .placeName("test")
                .build();

        PlaceResponse placeResponse = place.toDto();

        assertAll(
                () -> assertNotNull(placeResponse),
                () -> assertEquals(place.getPlaceId(), placeResponse.getPlaceId()),
                () -> assertEquals(place.getPlaceName(), placeResponse.getPlaceName())
        );
    }
}
