package com.the_ajou.service;

import com.the_ajou.domain.point.Point;
import com.the_ajou.domain.point.PointRepository;
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
    public List<PointResponseDAO> findPointHistoryByUserId(int userId){
        List<Point> points = pointRepository.findListByUserId(userId);
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
    public PointResponseDAO findHistoryById(int pointLogId){
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
    public boolean chargePoint(PointCreateDTO pointCreateDTO){
        User user = userRepository.findById(pointCreateDTO.getUserId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다."));

        int userPoint = user.getPoint();

        user.updatePoint(userPoint + pointCreateDTO.getPoint());
        user.setUpdatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        Point point = Point.builder()
                .user(user)
                .point(pointCreateDTO.getPoint())
                .createdAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();

        pointRepository.save(point);

        return true;
    }

    @Transactional
    public int getPointByUserId(int userId){
        Point point = pointRepository.findByUserId(userId);

        return point.getPoint();
    }
}
