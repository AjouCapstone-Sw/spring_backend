package com.the_ajou.web.controller;

import com.the_ajou.service.PointService;
import com.the_ajou.web.dao.pointLog.PointLogResponseDAO;
import com.the_ajou.web.dto.point.PointCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointController {
    private final PointService pointService;

    @GetMapping("/log/userId")
    List<PointLogResponseDAO> getLogsByUserId(int userId){
        return pointService.findByUserId(userId);
    }

    @GetMapping("/log/id")
    PointLogResponseDAO getPointLog(int id){
        return pointService.findById(id);
    }

    @PostMapping("/log")
    int createLog(@RequestBody PointCreateDTO pointCreateDTO){
        return pointService.createPointLog(pointCreateDTO);
    }

    @PatchMapping("/log/charge")
    int updatePoint(int id, int chargePoint){
        return pointService.chargePoint(id, chargePoint);
    }
}
