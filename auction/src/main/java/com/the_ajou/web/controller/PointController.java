package com.the_ajou.web.controller;

import com.the_ajou.service.PointService;
import com.the_ajou.web.dao.point.PointResponseDAO;
import com.the_ajou.web.dto.point.PointCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/point/userId")
    List<PointResponseDAO> getsByUserId(int userId){
        return pointService.findByUserId(userId);
    }

    @GetMapping("/point")
    PointResponseDAO getPoint(int id){
        return pointService.findById(id);
    }

    @PatchMapping("/point/charge")
    int updatePoint(int id, int chargePoint){
        return pointService.chargePoint(id, chargePoint);
    }
}
