package shop.mtcoding.village.model.place;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shop.mtcoding.village.model.date.Dates;
import shop.mtcoding.village.model.search.Search;

import javax.persistence.EntityManager;
import java.util.List;
public interface PlaceRepository extends JpaRepository<Place,Long> {

//    @Query("SELECT p, d.dayOfWeekName FROM Place p LEFT JOIN Dates d ON p.id = d.placeId LEFT JOIN Hashtag h ON p.id = h.placeId WHERE p.id = :id")
//    Place findAllWithDate(@Param("id") Long id);

}
