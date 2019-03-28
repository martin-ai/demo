package com.example.demo.producer_consumer;

import com.example.demo.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ARepository extends BasicRepository<A, String> {
}
