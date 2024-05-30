package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.PlaceRequest;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.exception.PlaceAlreadyExistException;
import kr.co.contxt.commonapi.exception.PlaceNotFountException;
import kr.co.contxt.commonapi.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(PlaceService.class)
class PlaceServiceTest {
    @Autowired
    private PlaceService placeService;
    @MockBean
    private PlaceRepository placeRepository;

    @Test
    void getAllPlaces() {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(7, 0);
        Place place = Place.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeRepository.findAll()).willReturn(List.of(place));

        // when
        List<PlaceResponse> places = placeService.getAllPlaces();

        // then
        assertAll(
                () -> assertNotNull(places),
                () -> assertFalse(places.isEmpty()),
                () -> assertEquals(placeId, places.get(0).getPlaceId()),
                () -> assertEquals(placeName, places.get(0).getPlaceName()),
                () -> assertEquals(placeCode, places.get(0).getPlaceCode()),
                () -> assertEquals(cycle, places.get(0).getCycle())
        );
    }

    @Test
    void getPlace() {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(7, 0);
        Place place = Place.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeRepository.findById(anyLong())).willReturn(Optional.of(place));

        // when
        PlaceResponse placeResponse = placeService.getPlace(placeId);

        // then
        assertAll(
                () -> assertNotNull(placeResponse),
                () -> assertEquals(placeId, placeResponse.getPlaceId()),
                () -> assertEquals(placeName, placeResponse.getPlaceName()),
                () -> assertEquals(placeCode, placeResponse.getPlaceCode()),
                () -> assertEquals(cycle, placeResponse.getCycle())
        );
    }

    @Test
    void getPlaceException() {
        given(placeRepository.findById(1L)).willThrow(PlaceNotFountException.class);

        assertThrows(PlaceNotFountException.class, () -> placeService.getPlace(1L));
    }

    @Test
    void savePlace() {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(7, 0);
        Place place = Place.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeRepository.save(place)).willReturn(place);

        // when
        PlaceResponse savePlace = placeService.savePlace(place);

        // then
        assertAll(
                () -> assertNotNull(savePlace),
                () -> assertEquals(placeId, savePlace.getPlaceId()),
                () -> assertEquals(placeName, savePlace.getPlaceName()),
                () -> assertEquals(placeCode, savePlace.getPlaceCode()),
                () -> assertEquals(cycle, savePlace.getCycle())
        );
    }

    @Test
    void savePlaceException() {
        given(placeRepository.existsByPlaceName("test place")).willThrow(PlaceAlreadyExistException.class);

        assertAll(
                () -> assertThrows(PlaceAlreadyExistException.class, ()
                        -> placeService.savePlace(new PlaceRequest("test place", null, null).toEntity()))
        );
    }

    @Test
    void updatePlace() {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(7, 0);
        Place place = Place.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();
        PlaceRequest placeRequest = new PlaceRequest();
        placeRequest.setPlaceName(placeName);
        placeRequest.setPlaceCode(placeCode);
        placeRequest.setCycle(cycle);

        given(placeRepository.findById(anyLong())).willReturn(Optional.of(place));
        given(placeRepository.save(place)).willReturn(place);

        // when
        PlaceResponse updatePlace = placeService.updatePlace(placeId, placeRequest);

        // then
        assertAll(
                () -> assertNotNull(updatePlace),
                () -> assertEquals(placeId, updatePlace.getPlaceId()),
                () -> assertEquals(placeName, updatePlace.getPlaceName()),
                () -> assertEquals(placeCode, updatePlace.getPlaceCode()),
                () -> assertEquals(cycle, updatePlace.getCycle())
        );
    }

    @Test
    void updatePlaceException() {
        PlaceRequest placeRequest = new PlaceRequest();

        given(placeRepository.findById(1L)).willThrow(PlaceNotFountException.class);

        assertThrows(PlaceNotFountException.class, () -> placeService.updatePlace(1L, placeRequest));
    }

    @Test
    void deletePlace() {
        Long placeId = 1L;

        given(placeRepository.existsById(anyLong())).willReturn(true);

        placeService.deletePlace(placeId);

        verify(placeRepository, times(1)).deleteById(placeId);
    }

    @Test
    void deletePlaceException() {
        Long placeId = 1L;

        given(placeRepository.existsById(anyLong())).willReturn(false);

        assertThrows(PlaceNotFountException.class, () -> placeService.deletePlace(placeId));
    }
}