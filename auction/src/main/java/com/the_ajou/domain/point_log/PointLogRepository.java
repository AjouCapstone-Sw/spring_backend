package com.the_ajou.domain.point_log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointLogRepository extends JpaRepository<PointLog, Integer> {
    List<PointLog> findByUserId(int userId);
}
