package shop.mtcoding.village.model.place;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.village.dto.place.response.PlaceSaveResponse;
import shop.mtcoding.village.dto.place.response.PlaceUpdateResponse;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.file.FileInfo;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.PlaceStatus;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "place_tb")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("공간 아이디")
    private Long id;

    @Comment("유저(호스트) 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Comment("공간 제목")
    private String title;

    @Comment("공간 전화번호")
    private String tel;

    @Comment("공간 정보")
    private String placeIntroductionInfo;

    @Comment("공간 소개")
    private String notice;

    @Comment("공간 사진")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_info_id")
    private FileInfo fileInfo;

    @Comment("공간의 최대 인원수")
    private Integer maxPeople;

    @Comment("주차 가능 대수")
    private Integer maxParking;


    @Comment("시간당 결제 금액")
    private Integer pricePerHour;

    @Comment("시작 시간")
    private LocalDateTime startTime;

    @Comment("마감 시간")
    private LocalDateTime endTime;

    @Comment("공간상태")
    @Enumerated(EnumType.STRING)
    private PlaceStatus status;

    @Comment("예약승인 필요여부")
    private Boolean isConfirmed;

    public Place(User user, String title, String tel, String placeIntroductionInfo, String notice, FileInfo fileInfo, Integer maxPeople,
            Integer maxParking, Integer pricePerHour, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.title = title;
        this.tel = tel;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.fileInfo = fileInfo;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Builder
    public Place(String title, String tel, LocalDateTime startTime, LocalDateTime endTime,
                 String placeIntroductionInfo,
                 String notice, Integer maxPeople, Integer maxParking, Integer pricePerHour) {
        this.title = title;
        this.tel = tel;
        this.startTime = startTime;
        this.endTime = endTime;
        this.placeIntroductionInfo = placeIntroductionInfo;
        this.notice = notice;
        this.maxPeople = maxPeople;
        this.maxParking = maxParking;
        this.pricePerHour = pricePerHour;
//        this.fileInfo = fileInfo;
    }

    public PlaceSaveResponse toResponse() {
        return new PlaceSaveResponse(
                title, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }

    public PlaceUpdateResponse toUpdateResponse() {
        return new PlaceUpdateResponse(
                title, tel, startTime.toString(), endTime.toString(), placeIntroductionInfo, pricePerHour, maxPeople, maxParking, notice
        );
    }

}