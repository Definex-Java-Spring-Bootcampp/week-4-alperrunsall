package com.patika.kredinbizdeservice.repository;

import com.patika.kredinbizdeservice.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign,Long> {
}
