package com.fclassroom.cloud.FTD;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 把读取的数据进行处理
 */
@Component("FTD_productProcessor")
public class ProductItemProcessor implements ItemProcessor<Product, Product> {
    public static final Log log = LogFactory.getLog(ProductItemWriter.class);

    @Override
    public Product process(Product product) throws Exception {
        product.setId(product.getId() + 1);
        Thread.sleep(10 + new Random().nextInt(100));
        log.info("=====处理完成::" + product.toString());
        return product;
    }
}
