package kr.co.contxt.commonapi.service;

import kr.co.contxt.commonapi.dto.PlaceRequest;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.entity.Place;

import java.util.List;

/**
 * The interface Place service.
 *
 * @author parksangwon
 * @version 1.0.0
 */
public interface PlaceService {
    /**
     * 모든 장소를 조회하는 메서드
     *
     * @return 모든 장소 리스트
     */
    List<PlaceResponse> getAllPlaces();

    /**
     * 단일 장소 조회 메서드
     *
     * @param placeId 장소 아이디
     * @return 장소 정보
     */
    PlaceResponse getPlace(Long placeId);

    /**
     * 장소 저장 메서드
     *
     * @param place 장소
     * @return 저장된 장소
     */
    PlaceResponse savePlace(Place place);

    /**
     * 장소 수정 메서드
     *
     * @param placeId      장소 아이디
     * @param placeRequest 장소 수정 요청 dto
     * @return 수정된 장소
     */
    PlaceResponse updatePlace(Long placeId, PlaceRequest placeRequest);

    /**
     * 장소 제거 메서드
     *
     * @param placeId 장소 아이디
     */
    void deletePlace(Long placeId);
}
