package shop.mtcoding.village.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.model.address.Address;
import shop.mtcoding.village.model.category.Category;
import shop.mtcoding.village.model.place.Place;
import shop.mtcoding.village.model.place.PlaceRepository;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;
import shop.mtcoding.village.model.review.Review;
import shop.mtcoding.village.model.user.User;
import shop.mtcoding.village.util.status.ReservationStatus;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EntityManager em;



    @BeforeEach
    public void init() {
      setUp(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 3, ReservationStatus.WAIT);
    }

    @Test
    @Transactional
    void selectAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        Assertions.assertNotEquals(reservations.size(), 0);

        Reservation reservation = reservations.get(0);
        Assertions.assertEquals(reservation.getPeopleNum(), 3);
    }

    @Test
    @Transactional
    void selectAndUpdate() {
        var optionalReservation = this.reservationRepository.findById(4L);

        if(optionalReservation.isPresent()) {
            var result = optionalReservation.get();
            Assertions.assertEquals(result.getPeopleNum(), 3);

            var peopleNum = 4;
            result.setPeopleNum(peopleNum);
            Reservation merge = entityManager.merge(result);

            Assertions.assertEquals(merge.getPeopleNum(), 4);
        } else {
            Assertions.assertNotNull(optionalReservation.get());
        }
    }

    @Test
    @Transactional
    void insertAndDelete() {
        Reservation reservation = setUp(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), 5, ReservationStatus.WAIT);

        Optional<Reservation> findReservation = this.reservationRepository.findById(reservation.getId());

        if(findReservation.isPresent()) {
            var result = findReservation.get();
            Assertions.assertEquals(result.getPeopleNum(), 5);
            entityManager.remove(reservation);
            Optional<Reservation> deleteReservation = this.reservationRepository.findById(reservation.getId());
            if (deleteReservation.isPresent()) {
                Assertions.assertNull(deleteReservation.get());
            }
        } else {
            Assertions.assertNotNull(findReservation.get());
        }
    }
    private Reservation setUp(LocalDateTime date, LocalDateTime startTime, LocalDateTime endTime, Integer peopleNum, ReservationStatus status) {
        User user = new User().builder().name("love").password("1234").email("ssar@nate.com").tel("1234").role("USER").profile("123123").build();
        this.entityManager.persist(user);

        Address address = new Address().builder().roadFullAddr("도로명주소").sggNm("시군구").zipNo("우편번호").lat("경도").lng("위도").build();
        this.entityManager.persist(address);

        Review review = new Review().builder().user(user).starRating(5).content("내용").image("이미지").likeCount(3).build();
        this.entityManager.persist(review);

        Category category = new Category().builder().name("이름").build();
        this.entityManager.persist(category);


        Place place = new Place().builder().user(user).title("제목").address(address).tel("123123").review(review)
                .placeIntroductionInfo("공간정보").guide("공간소개").facilityInfo("시설정보").hashtag("해시태그").image("사진").maxPeople(4).pricePerHour(30)
                .startTime(LocalDateTime.now()).endTime(LocalDateTime.now()).category(category).build();
        this.entityManager.persist(place);
        var reservation = new Reservation();
        reservation.setUser(user);
        reservation.setPlace(place);
        reservation.setDate(date);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setPeopleNum(peopleNum);
        reservation.setStatus(status);
        return  this.entityManager.persist(reservation);
    }
}