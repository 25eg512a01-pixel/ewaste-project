package com.ewaste.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="pickup_requests")
public class PickupRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="report_id",unique=true) private WasteReport report;
 @ManyToOne(fetch=FetchType.EAGER,optional=false) @JoinColumn(name="requester_id") private AppUser requester;
 @ManyToOne(fetch=FetchType.EAGER) @JoinColumn(name="collector_id") private AppUser collector;
 @Column(nullable=false) private LocalDateTime scheduledAt;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private WasteStatus status=WasteStatus.SCHEDULED;
 @Column(nullable=false) private LocalDateTime createdAt=LocalDateTime.now();
 @Column(nullable=false) private boolean rewardGranted=false;
 public PickupRequest() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public WasteReport getReport(){return report;} public void setReport(WasteReport report){this.report=report;}
 public AppUser getRequester(){return requester;} public void setRequester(AppUser requester){this.requester=requester;}
 public AppUser getCollector(){return collector;} public void setCollector(AppUser collector){this.collector=collector;}
 public LocalDateTime getScheduledAt(){return scheduledAt;} public void setScheduledAt(LocalDateTime scheduledAt){this.scheduledAt=scheduledAt;}
 public WasteStatus getStatus(){return status;} public void setStatus(WasteStatus status){this.status=status;}
 public LocalDateTime getCreatedAt(){return createdAt;}
 public boolean isRewardGranted(){return rewardGranted;} public void setRewardGranted(boolean rewardGranted){this.rewardGranted=rewardGranted;}
}
