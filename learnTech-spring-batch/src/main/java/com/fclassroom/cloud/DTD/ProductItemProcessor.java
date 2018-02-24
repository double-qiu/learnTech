package com.fclassroom.cloud.DTD;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * 把读取的数据进行处理
 */
@Component("DTD_productProcessor")
public class ProductItemProcessor implements ItemProcessor<Product, Product> {
    public static final Log log = LogFactory.getLog(ProductItemProcessor.class);

    @Override
    public Product process(Product product) throws Exception {
        product.setId(product.getId() + 1);
        product.setName(product.getName() + "fclassroom");
        log.info("=====处理完成::" + product.toString());
        return product;
    }
}
