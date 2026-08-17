package com.esnet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esnet.dto.StatistiqueDashboardDTO;
import com.esnet.services.DashboardService;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping()
    public StatistiqueDashboardDTO statistiques() {

        return dashboardService.statistiques();

    }

	 
}
