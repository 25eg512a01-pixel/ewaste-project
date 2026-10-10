package com.ewaste.repository;
import com.ewaste.model.WasteReport; import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface WasteReportRepository extends JpaRepository<WasteReport,Long> { List<WasteReport> findByUserIdOrderByCreatedAtDesc(Long userId); }
