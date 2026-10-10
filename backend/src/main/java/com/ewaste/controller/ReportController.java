package com.ewaste.controller;
import com.ewaste.dto.CreateReportRequest;import com.ewaste.model.*;import com.ewaste.repository.*;import jakarta.validation.Valid;import org.springframework.http.*;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequestMapping("/api/reports") public class ReportController {
 private final WasteReportRepository reports;private final UserRepository users;
 public ReportController(WasteReportRepository reports,UserRepository users){this.reports=reports;this.users=users;}
 @PostMapping public ResponseEntity<?> create(@Valid @RequestBody CreateReportRequest r,Authentication a){AppUser u=users.findByEmailIgnoreCase(a.getName()).orElseThrow();WasteReport w=new WasteReport();w.setCategory(r.category());w.setDescription(r.description());w.setQuantityKg(r.quantityKg());w.setAddress(r.address());w.setNotes(r.notes());w.setUser(u);return ResponseEntity.status(201).body(reports.save(w));}
 @GetMapping public List<WasteReport> list(Authentication a){AppUser u=users.findByEmailIgnoreCase(a.getName()).orElseThrow();return u.getRole()==Role.ADMIN?reports.findAll():reports.findByUserIdOrderByCreatedAtDesc(u.getId());}
 @GetMapping("/all") @PreAuthorize("hasRole('ADMIN')") public List<WasteReport> all(){return reports.findAll();}
 @DeleteMapping("/{id}") @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<?> delete(@PathVariable Long id){if(!reports.existsById(id))return ResponseEntity.notFound().build();reports.deleteById(id);return ResponseEntity.noContent().build();}
}
