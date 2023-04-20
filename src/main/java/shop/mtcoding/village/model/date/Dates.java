package shop.mtcoding.village.model.date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import shop.mtcoding.village.dto.date.response.DateSaveResponse;
import shop.mtcoding.village.model.place.Place;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "dates_tb")
public class Dates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("날짜 아이디")
//    @JsonIgnore
    private Long id;

    @ElementCollection
    @CollectionTable(name = "day_of_week_name", joinColumns = @JoinColumn(name = "dates_id"))
    @Column(name = "dayOfWeekName")
    @Comment("요일")
    private List<String> dayOfWeekName;

    @Comment("공간의 아이디")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placeId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Place place;

    @Builder
    public Dates(List<String> dayOfWeekName, Place place) {
        this.dayOfWeekName = dayOfWeekName;
        this.place = place;
    }

}