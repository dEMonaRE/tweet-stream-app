package com.emrezorlu.app.demo.tweetstream.message.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.emrezorlu.app.demo.tweetstream.message.entity.StatisticEntity;

@Repository
public interface StatisticRepository extends MongoRepository<StatisticEntity, String> {

	@Query("{'createDate' : { $gte: ?0, $lte: ?1 } }")
	List<StatisticEntity> findByCreateDateBetween(LocalDateTime from, LocalDateTime to);
}
