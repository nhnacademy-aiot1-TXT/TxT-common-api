package kr.co.contxt.commonapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.contxt.commonapi.dto.PlaceRequest;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Place Rest Controller 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/common/place")
@Tag(name = "Place Rest Controller", description = "장소 API")
public class PlaceRestController {
    private final PlaceService placeService;

    /**
     * 장소 리스트 조회 메서드
     *
     * @return 센서 리스트
     */
    @GetMapping
    @Operation(summary = "장소 리스트 조회")
    public ResponseEntity<List<PlaceResponse>> getPlaceList() {
        List<PlaceResponse> places = placeService.getAllPlaces();

        return ResponseEntity.ok(places);
    }

    /**
     * 장소 단일 조회 메서드
     *
     * @param placeId 장소 아이디
     * @return 장소 place
     */
    @GetMapping("/{placeId}")
    @Operation(summary = "장소 단일 조회")
    public ResponseEntity<PlaceResponse> getPlace(@PathVariable Long placeId) {
        PlaceResponse place = placeService.getPlace(placeId);

        return ResponseEntity.ok(place);
    }

    /**
     * 장소 추가 메서드
     *
     * @param placeRequest 장소 추가 요청 dto
     * @return 추가된 장소
     */
    @PostMapping
    @Operation(summary = "장소 추가")
    public ResponseEntity<PlaceResponse> addPlace(@RequestBody @Valid PlaceRequest placeRequest) {
        PlaceResponse responsePlace = placeService.savePlace(placeRequest.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responsePlace);
    }

    /**
     * 장소 수정 메서드
     *
     * @param placeId      장소 아이디
     * @param placeRequest 장소 수정 요청 dto
     * @return the response entity
     */
    @PutMapping("/{placeId}")
    @Operation(summary = "장소 수정")
    public ResponseEntity<PlaceResponse> updatePlace(@PathVariable Long placeId, @RequestBody @Valid PlaceRequest placeRequest) {
        PlaceResponse responsePlace = placeService.updatePlace(placeId, placeRequest);

        return ResponseEntity.ok(responsePlace);
    }

    /**
     * 장소 제거 메서드
     *
     * @param placeId 장소 아이디
     * @return no content 응답
     */
    @DeleteMapping("/{placeId}")
    @Operation(summary = "장소 제거")
    public ResponseEntity<Void> deletePlace(@PathVariable Long placeId) {
        placeService.deletePlace(placeId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
