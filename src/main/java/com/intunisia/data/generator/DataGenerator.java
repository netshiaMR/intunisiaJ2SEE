package com.intunisia.data.generator;

import com.intunisia.data.entity.SampleFoodProduct;
import com.intunisia.data.service.SampleFoodProductRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(SampleFoodProductRepository sampleFoodProductRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (sampleFoodProductRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Sample Food Product entities...");
            ExampleDataGenerator<SampleFoodProduct> sampleFoodProductRepositoryGenerator = new ExampleDataGenerator<>(
                    SampleFoodProduct.class, LocalDateTime.of(2022, 1, 7, 0, 0, 0));
            sampleFoodProductRepositoryGenerator.setData(SampleFoodProduct::setId, DataType.ID);
            sampleFoodProductRepositoryGenerator.setData(SampleFoodProduct::setImage, DataType.FOOD_PRODUCT_IMAGE);
            sampleFoodProductRepositoryGenerator.setData(SampleFoodProduct::setName, DataType.FOOD_PRODUCT_NAME);
            sampleFoodProductRepositoryGenerator.setData(SampleFoodProduct::setEanCode, DataType.FOOD_PRODUCT_EAN);
            sampleFoodProductRepository.saveAll(sampleFoodProductRepositoryGenerator.create(100, seed));

            logger.info("Generated demo data");
        };
    }

}