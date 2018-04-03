package org.learnTech.elasticsearch.web;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.learnTech.elasticsearch.entiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("rawtypes")
@RestController
public class ElasticsearchController {
	@Autowired
	private TransportClient client;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	// 查询接口
	@SuppressWarnings("unchecked")
	@GetMapping("/get/book/novel")
	public ResponseEntity get(@RequestParam(name = "id", defaultValue = "") String id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		GetResponse response = this.client.prepareGet("book", "novel", id).get();
		if (!response.isExists()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(response.getSource(), HttpStatus.OK);

	}

	// 删除接口
	@SuppressWarnings("unchecked")
	@DeleteMapping("/delete/book/novel")
	public ResponseEntity delete(@RequestParam(name = "id", defaultValue = "") String id) {
		if (StringUtils.isEmpty(id)) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		DeleteResponse response = this.client.prepareDelete("book", "novel", id).get();
		return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);

	}

	// 增加接口
	@SuppressWarnings("unchecked")
	@PostMapping("/add/book/novel")
	public ResponseEntity add(Book book) {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("title", book.getTitle())
					.field("author", book.getAuthor()).field("word_count", book.getWord_count())
					.field("public_date", book.getPublic_date()).endObject();
			IndexResponse response = this.client.prepareIndex("book", "novel",book.getId()).setSource(builder).get();
			return new ResponseEntity(response.getId(), HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 增加接口
	@SuppressWarnings("unchecked")
	@PutMapping("/update/book/novel")
	public ResponseEntity update(Book book) {
		UpdateRequest request = new UpdateRequest("book", "novel", book.getId());
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
			if (book.getAuthor() != null) {
				builder.field("author", book.getAuthor());
			}
			if (book.getTitle() != null) {
				builder.field("title", book.getTitle());
			}
			builder.endObject();
			request.doc(builder);
			UpdateResponse response = this.client.update(request).get();
			return new ResponseEntity(response.getResult().toString(), HttpStatus.OK);
		} catch (IOException | InterruptedException | ExecutionException e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("query/book/novel")
	public ResponseEntity query(@RequestParam(value = "gt_word_count", defaultValue = "0") int gtWordCount,
			@RequestParam(value = "author", required = false) String author,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "lt_word_count", required = false) Integer ltWordCount) {
		BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
		if (author != null) {
			boolBuilder.must(QueryBuilders.matchQuery("author", author));
		}
		if (title != null) {
			boolBuilder.must(QueryBuilders.matchQuery("title", title));
		}
		// if (bookBean.getTitle() != null) {
		// builder.must(QueryBuilders.matchQuery("title", bookBean.getTitle()));
		// }
		RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("word_count").from(gtWordCount);
		if (ltWordCount != null) {
			rangeQuery.to(ltWordCount);
		}
		boolBuilder.filter(rangeQuery);
		SearchRequestBuilder builder = this.client.prepareSearch("book").setTypes("novel")
				// Type 什么意思不懂
				.setSearchType(SearchType.QUERY_THEN_FETCH).setQuery(boolBuilder).setFrom(0).setSize(10);
		System.out.println(String.valueOf(builder));
		SearchResponse response = builder.get();

		List<Map<String, Object>> result = new ArrayList<>();
		response.getHits().forEach((s) -> result.add(s.getSourceAsMap()));
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
