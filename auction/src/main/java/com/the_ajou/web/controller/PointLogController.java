package com.the_ajou.web.controller;

import com.the_ajou.domain.point_log.PointLog;
import com.the_ajou.service.PointLogService;
import com.the_ajou.web.dao.pointLog.PointLogResponseDAO;
import com.the_ajou.web.dto.point_log.PointLogCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointLogController {
    private final PointLogService pointLogService;

    @GetMapping("/log/{userId}")
    List<PointLogResponseDAO> getLogsByUserId(@RequestParam int userId){
        return pointLogService.findByUserId(userId);
    }

    @GetMapping("/log/{id}")
    PointLogResponseDAO getPointLog(@RequestParam int id){
        return pointLogService.findById(id);
    }

    @PostMapping("/log")
    int createLog(@RequestBody PointLogCreateDTO pointLogCreateDTO){
        return pointLogService.createPointLog(pointLogCreateDTO);
    }

    @PatchMapping("/log/charge/{id}")
    int updatePoint(@RequestParam int id, int chargePoint){
        return pointLogService.chargePoint(id, chargePoint);
    }
}
