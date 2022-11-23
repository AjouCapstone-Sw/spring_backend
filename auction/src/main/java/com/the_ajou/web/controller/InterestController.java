package com.the_ajou.web.controller;

import com.the_ajou.service.InterestService;
import com.the_ajou.web.dto.interest.InterestCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;

    @PostMapping("/api/interest/create")
    private boolean createInterest(@RequestBody InterestCreateDTO interestCreateDTO){
        return interestService.createInterest(interestCreateDTO);
    }

    @DeleteMapping("/api/interest/delete")
    private boolean deleteInterest(int interestId){
        return interestService.deleteInterest(interestId);
    }
}
