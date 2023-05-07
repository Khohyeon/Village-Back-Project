package shop.mtcoding.village.model.host;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.dto.address.AddressDTO;
import shop.mtcoding.village.dto.host.request.HostSaveRequest;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceAddress;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.HostStatus;

import javax.persistence.*;

@Entity
@Table(name = "host_tb")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User에서 role이 HOST인 유저의 NAME
    @Comment("호스트 이름")
    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Comment("공간 정보")
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Place place;

    @Comment("호스트의 닉네임")
    private String nickName;

    // 현재는 주소만 받아 오고 추후에 공간정보 추가
    @Comment("호스트 주소")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PlaceAddress address;

    @Comment("사업자 번호")
    private String businessNum;

    @Comment("host 신청상태")
    @Enumerated(EnumType.STRING)
    private HostStatus status;     // wail, none, deny, sign

    @Builder
    public Host(User user, PlaceAddress address, String nickName, String businessNum, HostStatus status) {
        this.user = user;
        this.address = address;
        this.nickName = nickName;
        this.businessNum = businessNum;
        this.status = status;
    }

//    public HostSaveRequest toResponse() {
//        return new HostSaveRequest(user.getName(), nickName, businessNum);
//    }
}
