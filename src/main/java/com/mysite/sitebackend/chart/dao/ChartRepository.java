package com.mysite.sitebackend.chart.dao;

import com.mysite.sitebackend.chart.domain.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartRepository extends JpaRepository<Chart, Integer> {
    Chart findByDateAndNameAndChartIndex(String date, String name, String chartIndex);
}
