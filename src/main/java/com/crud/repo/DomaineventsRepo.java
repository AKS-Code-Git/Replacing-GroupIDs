package com.crud.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crud.pojo.Domainevents;

public interface DomaineventsRepo  extends MongoRepository<Domainevents, String>{

}
