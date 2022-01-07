package com.intunisia.data.service;

import com.intunisia.data.entity.SampleFoodProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleFoodProductRepository extends JpaRepository<SampleFoodProduct, Integer> {

}