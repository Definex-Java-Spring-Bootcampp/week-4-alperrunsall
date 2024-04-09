package com.patika.kredinbizdeservice.controller;

import com.patika.kredinbizdeservice.model.Campaign;
import com.patika.kredinbizdeservice.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public List<Campaign> getAll() {
        return campaignService.getAllCampaigns();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign create(@RequestBody Campaign campaign) {
        return campaignService.save(campaign);
    }
}
