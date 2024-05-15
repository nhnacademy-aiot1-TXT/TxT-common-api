package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.exception.PlaceNotFountException;
import kr.co.contxt.commonapi.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class PlaceRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PlaceService placeService;

    @Test
    void getPlaceList() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .build();

        given(placeService.getAllPlaces()).willReturn(List.of(placeResponse));

        // when
        // then
        mockMvc.perform(get("/api/common/place"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$[0].placeName", equalTo(placeName)));
    }

    @Test
    void getPlace() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .build();

        given(placeService.getPlace(anyLong())).willReturn(placeResponse);

        // when
        // then
        mockMvc.perform(get("/api/common/place/" + placeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)));
    }

    @Test
    void getPlaceException() throws Exception {
        long placeId = 1L;

        given(placeService.getPlace(anyLong()))
                .willThrow(new PlaceNotFountException("Place를 찾을 수 없습니다."));

        mockMvc.perform(get("/api/common/place/" + placeId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Place를 찾을 수 없습니다.")));
    }

    @Test
    void addPlace() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        PlaceResponse place = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .build();

        given(placeService.savePlace(any())).willReturn(place);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/api/common/place")
                        .content(objectMapper.writeValueAsString(place))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)));
    }

    @Test
    void updatePlace() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        PlaceResponse place = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .build();

        given(placeService.updatePlace(anyLong(), any())).willReturn(place);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/place/" + placeId)
                        .content(objectMapper.writeValueAsString(place))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)));
    }

    @Test
    void updatePlaceException() throws Exception {
        Long placeId = 1L;
        String placeName = "test place";
        Place place = Place.builder()
                .placeId(placeId)
                .placeName(placeName)
                .build();

        given(placeService.updatePlace(anyLong(), any()))
                .willThrow(new PlaceNotFountException("Place를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/api/common/place/" + placeId)
                        .content(objectMapper.writeValueAsString(place))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Place를 찾을 수 없습니다.")));
    }

    @Test
    void deletePlace() throws Exception {
        long placeId = 1L;

        mockMvc.perform(delete("/api/common/place/" + placeId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePlaceException() throws Exception {
        long placeId = 1L;

        willThrow(new PlaceNotFountException("Place를 찾을 수 없습니다.")).given(placeService).deletePlace(anyLong());

        mockMvc.perform(delete("/api/common/place/" + placeId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", equalTo("Place를 찾을 수 없습니다.")));
    }
}