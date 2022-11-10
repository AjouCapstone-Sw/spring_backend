package com.the_ajou.service;

import com.the_ajou.domain.point.Point;
import com.the_ajou.domain.point.PointRepository;
import com.the_ajou.domain.purchase.PurchaseRepository;
import com.the_ajou.domain.product.ProductRepository;
import com.the_ajou.domain.user.User;
import com.the_ajou.domain.user.UserRepository;
import com.the_ajou.web.dao.point.PointResponseDAO;
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
    private final UserRepository userRepository;


    @Transactional
    public List<PointResponseDAO> findByUserId(int userId){
        List<Point> points = pointRepository.findByUserId(userId);
        List<PointResponseDAO> pointResponseDAOS = new LinkedList<>();

        for(Point point : points){
            PointResponseDAO pointResponseDAO = PointResponseDAO.builder()
                    .id(point.getId())
                    .userId(point.getUser().getId())
                    .createdAt(point.getCreatedAt())
                    .point(point.getPoint())
                    .build();
            pointResponseDAOS.add(pointResponseDAO);
        }
        return pointResponseDAOS;
    }

    @Transactional
    public PointResponseDAO findById(int pointLogId){
        Point point = pointRepository.findById(pointLogId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않은 포인트 내역입니다."));

        return PointResponseDAO.builder()
                .id(point.getId())
                .userId(point.getUser().getId())
                .createdAt(point.getCreatedAt())
                .point(point.getPoint())
                .build();
    }

    @Transactional
    public int chargePoint(int userId, int chargePoint){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        int point = user.getPoint();

        user.updatePoint(point + chargePoint);
        user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return user.getId();
    }
}
