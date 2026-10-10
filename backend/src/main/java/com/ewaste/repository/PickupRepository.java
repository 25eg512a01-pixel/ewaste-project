package com.ewaste.repository;
import com.ewaste.model.PickupRequest; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface PickupRepository extends JpaRepository<PickupRequest,Long> { List<PickupRequest> findByRequesterIdOrderByScheduledAtDesc(Long id); List<PickupRequest> findByCollectorIdOrderByScheduledAtDesc(Long id); boolean existsByReportId(Long reportId); }
