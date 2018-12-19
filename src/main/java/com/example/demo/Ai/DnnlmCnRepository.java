package com.example.demo.Ai;

import com.example.demo.BasicRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DnnlmCnRepository extends BasicRepository<DnnlmCnBean, String> {
}
