package kr.co.contxt.commonapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.co.contxt.commonapi.dto.PlaceRequest;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.exception.PlaceNotFountException;
import kr.co.contxt.commonapi.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(0, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String cycleString = cycle.format(formatter);
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeService.getAllPlaces()).willReturn(List.of(placeResponse));

        // when
        // then
        mockMvc.perform(get("/api/common/place"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$[0].placeName", equalTo(placeName)))
                .andExpect(jsonPath("$[0].placeCode", equalTo(placeCode)))
                .andExpect(jsonPath("$[0].cycle", equalTo(cycleString)));
    }

    @Test
    void getPlace() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(0, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String cycleString = cycle.format(formatter);
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeService.getPlace(anyLong())).willReturn(placeResponse);

        // when
        // then
        mockMvc.perform(get("/api/common/place/" + placeId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)))
                .andExpect(jsonPath("$.placeCode", equalTo(placeCode)))
                .andExpect(jsonPath("$.cycle", equalTo(cycleString)));
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
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(0, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String cycleString = cycle.format(formatter);
        PlaceRequest placeRequest = new PlaceRequest(placeName, placeCode, cycle);
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeService.savePlace(any())).willReturn(placeResponse);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/api/common/place")
                        .content(objectMapper.writeValueAsString(placeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)))
                .andExpect(jsonPath("$.placeCode", equalTo(placeCode)))
                .andExpect(jsonPath("$.cycle", equalTo(cycleString)));
    }

    @Test
    void updatePlace() throws Exception {
        // given
        Long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(0, 10, 0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String cycleString = cycle.format(formatter);
        PlaceRequest placeRequest = new PlaceRequest(placeName, placeCode, cycle);
        PlaceResponse placeResponse = PlaceResponse.builder()
                .placeId(placeId)
                .placeName(placeName)
                .placeCode(placeCode)
                .cycle(cycle)
                .build();

        given(placeService.updatePlace(anyLong(), any())).willReturn(placeResponse);

        // when
        // then
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(put("/api/common/place/" + placeId)
                        .content(objectMapper.writeValueAsString(placeRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.placeId", equalTo(placeId.intValue())))
                .andExpect(jsonPath("$.placeName", equalTo(placeName)))
                .andExpect(jsonPath("$.placeCode", equalTo(placeCode)))
                .andExpect(jsonPath("$.cycle", equalTo(cycleString)));
    }

    @Test
    void updatePlaceException() throws Exception {
        long placeId = 1L;
        String placeName = "test place";
        String placeCode = "test_place";
        LocalTime cycle = LocalTime.of(0, 30, 0);
        PlaceRequest placeRequest = new PlaceRequest(placeName, placeCode, cycle);

        given(placeService.updatePlace(anyLong(), any()))
                .willThrow(new PlaceNotFountException("Place를 찾을 수 없습니다."));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(put("/api/common/place/" + placeId)
                        .content(objectMapper.writeValueAsString(placeRequest))
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