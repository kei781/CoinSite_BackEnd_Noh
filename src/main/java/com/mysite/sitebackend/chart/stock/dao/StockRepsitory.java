package com.mysite.sitebackend.chart.stock.dao;

import com.mysite.sitebackend.chart.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepsitory extends JpaRepository<Stock, Integer>{
}
