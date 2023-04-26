package shop.mtcoding.village.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.reservation.request.ReservationSaveRequest;
import shop.mtcoding.village.model.reservation.Reservation;
import shop.mtcoding.village.model.reservation.ReservationRepository;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public Reservation 예약신청(ReservationSaveRequest reservationSaveRequest) {

<<<<<<< HEAD
        try {
            return reservationRepository.save(reservationSaveRequest.toEntity());
        } catch (Exception500 e) {
            throw new Exception500("예약신청 오류" + e.getMessage());
        }
=======


        return reservationRepository.save(reservationSaveRequest.toEntity());
>>>>>>> 1ec5787 (Exception 처리 전체 수정 완료)
    }

}
