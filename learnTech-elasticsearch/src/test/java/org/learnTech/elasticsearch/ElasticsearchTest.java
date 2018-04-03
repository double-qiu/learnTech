package org.learnTech.elasticsearch;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.learnTech.elasticsearch.entiy.Book;
import org.learnTech.elasticsearch.web.ElasticsearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)	
@SpringBootTest(classes=ElasticsearchApplication.class)
public class ElasticsearchTest {

	@Autowired
	private ElasticsearchController elasticsearchController;

	@SuppressWarnings("rawtypes")
	@Test
	public void addBookTest() {
		Book book = new Book("1","test","DOUBLE",100,new Date());
		ResponseEntity responseEntity = elasticsearchController.add(book);
		System.out.println(responseEntity);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void getBookTest() {
		ResponseEntity responseEntity = elasticsearchController.get("V4u5imIBpjs6y-swZdZK");
		System.out.println(responseEntity);
	}
	
	@Test
	public void getUpdateTest() {
		Book book = new Book("V4u5imIBpjs6y-swZdZK","test2","DOUBLE",100,new Date());
		elasticsearchController.update(book);
	}
	
	@Test
	public void getDeleteTest() {
		elasticsearchController.delete("V4u5imIBpjs6y-swZdZK");
	}
	
}
