package com.the_ajou.web.dao.point;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointResponseDAO {
    int id;
    int userId;
    String createdAt;
    int point;

    @Builder
    PointResponseDAO(int id, int userId, String createdAt, int point){
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.point = point;
    }
}
