package kr.co.contxt.commonapi.service.impl;

import kr.co.contxt.commonapi.dto.PlaceRequest;
import kr.co.contxt.commonapi.dto.PlaceResponse;
import kr.co.contxt.commonapi.entity.Place;
import kr.co.contxt.commonapi.exception.PlaceAlreadyExistException;
import kr.co.contxt.commonapi.exception.PlaceNotFountException;
import kr.co.contxt.commonapi.repository.PlaceRepository;
import kr.co.contxt.commonapi.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Place service 구현 클래스
 *
 * @author parksangwon
 * @version 1.0.0
 */
@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;
    private static final String PLACE_NOT_FOUND_MESSAGE = "장소를 찾을 수 없습니다.";
    private static final String PLACE_ALREADY_EXIST_EXCEPTION_MESSAGE = "장소가 이미 존재합니다.";

    /**
     * Place 리스트 조회 메서드
     *
     * @return place list
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getAllPlaces",
            key = "'all'",
            unless = "#result.isEmpty()"
    )
    public List<PlaceResponse> getAllPlaces() {
        return placeRepository.findAll()
                .stream().map(Place::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Place 단일 조회 메서드
     *
     * @param placeId 장소 아이디
     * @return place
     */
    @Override
    @Transactional(readOnly = true)
    @Cacheable(
            value = "getPlace",
            key = "#placeId",
            unless = "#result == null"
    )
    public PlaceResponse getPlace(Long placeId) {
        return placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFountException(PLACE_NOT_FOUND_MESSAGE))
                .toDto();
    }

    /**
     * Place 저장 메서드
     *
     * @param place 장소
     * @return 저장된 장소
     */
    @Override
    @Transactional
    @CacheEvict(
            value = "getAllPlaces",
            key = "'all'"
    )
    public PlaceResponse savePlace(Place place) {
        if (placeRepository.existsByPlaceName(place.getPlaceName())) {
            throw new PlaceAlreadyExistException(PLACE_ALREADY_EXIST_EXCEPTION_MESSAGE);
        }
        
        return placeRepository.save(place)
                .toDto();
    }

    /**
     * Place 수정 메서드
     *
     * @param placeId      장소 아이디
     * @param placeRequest 장소 수정 요청 dto
     * @return 수정된 장소
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getAllPlaces",
                            key = "'all'"
                    )
            },
            put = {
                    @CachePut(
                            value = "getPlace",
                            key = "#placeId",
                            unless = "#result == null"
                    )
            }
    )
    public PlaceResponse updatePlace(Long placeId, PlaceRequest placeRequest) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFountException(PLACE_NOT_FOUND_MESSAGE));

        place.setPlaceName(placeRequest.getPlaceName());
        place.setPlaceCode(placeRequest.getPlaceCode());
        place.setCycle(placeRequest.getCycle());

        return placeRepository.save(place)
                .toDto();
    }

    /**
     * Place 제거 메서드
     *
     * @param placeId 장소 아이디
     */
    @Override
    @Transactional
    @Caching(
            evict = {
                    @CacheEvict(
                            value = "getAllPlaces",
                            key = "'all'"
                    ),
                    @CacheEvict(
                            value = "getPlace",
                            key = "#placeId"
                    )
            }
    )
    public void deletePlace(Long placeId) {
        if (!placeRepository.existsById(placeId))
            throw new PlaceNotFountException(PLACE_NOT_FOUND_MESSAGE);

        placeRepository.deleteById(placeId);
    }
}
