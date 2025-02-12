package shop.mtcoding.village.dto.place.request;

import lombok.*;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.category.request.CategorySaveDTO;
import shop.mtcoding.village.dto.date.request.DateSaveDTO;
import shop.mtcoding.village.dto.facilityInfo.request.FacilityInfoSaveDTO;
import shop.mtcoding.village.dto.file.dto.FileSaveDTO;
import shop.mtcoding.village.dto.hashtag.request.HashtagSaveDTO;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.util.DateUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlaceSaveRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private AddressDTO address;

    private String tel;

    @NotNull(message = "대여 가능 시작시간을 입력해주세요.")
    private String startTime;

    @NotNull(message = "대여 가능 마감시간을 입력해주세요.")
    private String endTime;

    @NotNull(message = "공간의 정보를 입력해주세요.")
    private String placeIntroductionInfo;

    @NotBlank(message = "유의사항에 대해 입력해주세요.")
    private String notice;

    @NotNull(message = "최대 인원수를 입력해주세요.")
    private Integer maxPeople;

    @NotNull(message = "주차 대수를 입력해주세요.")
    private Integer maxParking;

    @NotNull(message = "시간당 금액을 입력해주세요.")
    private Integer pricePerHour;

    // 다른 엔티티들
    @NotNull(message = "사용가능한 요일을 설정해주세요.")
    private List<DateSaveDTO.DateSaveDto> dayOfWeek;

    private List<HashtagSaveDTO.HashtagSaveDto> hashtag;

    private List<FacilityInfoSaveDTO.FacilityInfoSaveDto> facilityInfo;

    @NotNull(message = "카테고리를 등록해주세요.")
    private String categoryName;

    private List<FileSaveDTO.FileSaveDto> image;

    public Place toEntity() {

        LocalDateTime startTimePaser = DateUtils.parseLocalDateTime(startTime);
        LocalDateTime endTimePaser = DateUtils.parseLocalDateTime(endTime);

        Place place = new Place();
        place.setTitle(title);
        place.setTel(tel);
        place.setStartTime(startTimePaser);
        place.setEndTime(endTimePaser);
        place.setPlaceIntroductionInfo(placeIntroductionInfo);
        place.setNotice(notice);
        place.setMaxPeople(maxPeople);
        place.setMaxParking(maxParking);
        place.setPricePerHour(pricePerHour);
        return new Place(
                title, tel, startTimePaser, endTimePaser, placeIntroductionInfo, notice, maxPeople, maxParking, pricePerHour
        );
    }


}