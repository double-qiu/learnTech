package com.fclassroom.cloud.FTD;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 把处理后的数据写入数据库
 */
@Component("FTD_productWriter")
public class ProductItemWriter implements ItemWriter<Product> {
    public static final Log log = LogFactory.getLog(ProductItemWriter.class);
    //    private static final String GET_PRODUCT = "select * from PRODUCT where id = ?";
    private static final String INSERT_PRODUCT = "insert into PRODUCT (id,name,description,quantity) values (?,?,?,?)";
    // private static final String UPDATE_PRODUCT = "update PRODUCT set name = ?, description = ?,quantity = ? where id = ?";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void write(final List<? extends Product> products) throws Exception {
        log.info("########批量写入 数据   " + products.size() + "#######");
        List<Object[]> data = new ArrayList<>();
        for (Product product : products) {
            Object[] temp = {product.getId(), product.getName(), product.getDescription(), product.getQuantity()};
            data.add(temp);
        }
        jdbcTemplate.batchUpdate(INSERT_PRODUCT, data);
    }
}
