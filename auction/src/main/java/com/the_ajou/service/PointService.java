package com.the_ajou.service;

import com.the_ajou.domain.point.Point;
import com.the_ajou.domain.point.PointRepository;
import com.the_ajou.domain.purchase.PurchaseRepository;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.pointLog.PointLogResponseDAO;
import com.the_ajou.web.dto.point.PointCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PointService {
    private final PointRepository pointRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;

    @Transactional
    public int createPointLog(PointCreateDTO pointCreateDTO){
        User user = userRepository.findById(pointCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));


        Point point = Point.builder()
                .user(user)
                .purchaseId(pointCreateDTO.getPurchaseId())
                .type(pointCreateDTO.getType())
                .pointChange(pointCreateDTO.getPointCharge())
                .build();

        pointRepository.save(point);

        return point.getId();
    }

    @Transactional
    public List<PointLogResponseDAO> findByUserId(int userId){
        List<Point> points = pointRepository.findByUserId(userId);
        List<PointLogResponseDAO> pointLogResponseDAOS = new LinkedList<>();

        for(Point point : points){
            PointLogResponseDAO pointLogResponseDAO = PointLogResponseDAO.builder()
                    .id(point.getId())
                    .userId(point.getUser().getId())
                    .purchaseId(point.getPurchaseId())
                    .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .type(point.getType())
                    .pointChange(point.getPointChange())
                    .build();
            pointLogResponseDAOS.add(pointLogResponseDAO);
        }
        return pointLogResponseDAOS;
    }

    @Transactional
    public PointLogResponseDAO findById(int pointLogId){
        Point point = pointRepository.findById(pointLogId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 포인트 내역입니다."));

        return PointLogResponseDAO.builder()
                .id(point.getId())
                .userId(point.getUser().getId())
                .purchaseId(point.getPurchaseId())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .type(point.getType())
                .pointChange(point.getPointChange())
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
