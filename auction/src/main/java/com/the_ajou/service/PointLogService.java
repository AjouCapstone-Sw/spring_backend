package com.the_ajou.service;

import com.the_ajou.domain.point_log.PointLog;
import com.the_ajou.domain.point_log.PointLogRepository;
import com.the_ajou.domain.purchase.Purchase;
import com.the_ajou.domain.purchase.PurchaseRepository;
import com.the_ajou.domain.sale.Sale;
import com.the_ajou.domain.sale.SaleRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.pointLog.PointLogResponseDAO;
import com.the_ajou.web.dto.point_log.PointLogCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointLogService {
    private final PointLogRepository pointLogRepository;
    private final SaleRepository saleRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Transactional
    public int createPointLog(PointLogCreateDTO pointLogCreateDTO){
        User user = userRepository.findById(pointLogCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));


        PointLog pointLog = PointLog.builder()
                .user(user)
                .purchaseId(pointLogCreateDTO.getPurchaseId())
                .type(pointLogCreateDTO.getType())
                .pointChange(pointLogCreateDTO.getPointCharge())
                .build();

        pointLogRepository.save(pointLog);

        return pointLog.getId();
    }

    @Transactional
    public List<PointLogResponseDAO> findByUserId(int userId){
        List<PointLog> pointLogs = pointLogRepository.findByUserId(userId);
        List<PointLogResponseDAO> pointLogResponseDAOS = new LinkedList<>();

        for(PointLog pointLog : pointLogs){
            PointLogResponseDAO pointLogResponseDAO = PointLogResponseDAO.builder()
                    .id(pointLog.getId())
                    .userId(pointLog.getUser().getId())
                    .purchaseId(pointLog.getPurchaseId())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .type(pointLog.getType())
                    .pointChange(pointLog.getPointChange())
                    .build();
            pointLogResponseDAOS.add(pointLogResponseDAO);
        }
        return pointLogResponseDAOS;
    }

    @Transactional
    public PointLogResponseDAO findById(int pointLogId){
        PointLog pointLog = pointLogRepository.findById(pointLogId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 포인트 내역입니다."));

        return PointLogResponseDAO.builder()
                .id(pointLog.getId())
                .userId(pointLog.getUser().getId())
                .purchaseId(pointLog.getPurchaseId())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .type(pointLog.getType())
                .pointChange(pointLog.getPointChange())
                .build();
    }

    @Transactional
    public int chargePoint(int userId, int chargePoint){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.updatePoint(chargePoint);
        user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return user.getId();
    }
}
