package com.ewaste.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="waste_reports")
public class WasteReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=80) private String category;
 @Column(nullable=false,length=1000) private String description;
 @Column(nullable=false) private double quantityKg;
 @Column(nullable=false,length=500) private String address;
 @Column(length=500) private String notes;
 @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="user_id") private AppUser user;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 public WasteReport() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getCategory(){return category;} public void setCategory(String category){this.category=category;}
 public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
 public double getQuantityKg(){return quantityKg;} public void setQuantityKg(double quantityKg){this.quantityKg=quantityKg;}
 public String getAddress(){return address;} public void setAddress(String address){this.address=address;}
 public String getNotes(){return notes;} public void setNotes(String notes){this.notes=notes;}
 public AppUser getUser(){return user;} public void setUser(AppUser user){this.user=user;}
 public LocalDateTime getCreatedAt(){return createdAt;}
}
