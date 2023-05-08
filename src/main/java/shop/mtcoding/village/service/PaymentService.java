package shop.mtcoding.village.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.village.core.exception.Exception500;
import shop.mtcoding.village.dto.bootpay.ReceiptDTO;
import shop.mtcoding.village.model.cardData.CardDataRepository;
import shop.mtcoding.village.model.metadata.MetaData;
import shop.mtcoding.village.model.metadata.MetaRepository;
import shop.mtcoding.village.model.payment.BootPatRepository;
import shop.mtcoding.village.model.payment.BootPay;
import shop.mtcoding.village.model.payment.Payment;
import shop.mtcoding.village.model.payment.PaymentRepository;

import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private final BootPatRepository bootPatRepository;

    private final CardDataRepository cardDataRepository;

    private final MetaRepository metaRepository;

    public PaymentService(PaymentRepository paymentRepository, BootPatRepository bootPatRepository, CardDataRepository cardDataRepository, MetaRepository metaRepository) {
        this.paymentRepository = paymentRepository;
        this.bootPatRepository = bootPatRepository;
        this.cardDataRepository = cardDataRepository;
        this.metaRepository = metaRepository;
    }


    @Transactional
    public BootPay 구매요청(ReceiptDTO receiptDTO) {

//        // MetadataDTO 객체를 Map<String, Object>으로 변환
//        Map<String, Object> metadataMap = receiptDTO.getMetadataDTO().getTest();
//
//        // Map<String, Object> 객체를 Metadata 객체로 변환하여 저장
//        MetaData metadata = new MetaData(null, );
//        metaRepository.save(metadata);

        cardDataRepository.save(receiptDTO.getCard_data().toEntity());

        metaRepository.save(receiptDTO.getMetadata().toEntity());

        return bootPatRepository.save(receiptDTO.toEntity());
    }

    @Transactional
    public void 결제내역삭제(Payment payment) {
        try {
            paymentRepository.delete(payment);
        }catch (Exception e){
            throw new Exception500("예약내역 삭제 오류" + e.getMessage());
        }
    }

    @Transactional
    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

}