package com.fclassroom.cloud.DTD;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 把处理后的数据写入数据库
 */
@Component("DTD_productWriter")
public class ProductItemWriter implements ItemWriter<Product> {
    public static final Log log = LogFactory.getLog(ProductItemWriter.class);

    //    private static final String GET_PRODUCT = "select * from PRODUCT where id = ?";
    private static final String INSERT_PRODUCT = "insert into PRODUCT (id,name,description,quantity) values (?,?,?,?)";
    // private static final String UPDATE_PRODUCT = "update PRODUCT set name = ?, description = ?,quantity = ? where id = ?";
    //装载的是新库的JDBCTemplate;
    @Autowired
    private JdbcTemplate newjdbcTemplate;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        log.info("------- 欲写的数据" + products.size());
        for (Product product : products) {
            newjdbcTemplate.update(INSERT_PRODUCT, product.getId(), product.getName(), product.getDescription(), product.getQuantity());
        }

    }
}
