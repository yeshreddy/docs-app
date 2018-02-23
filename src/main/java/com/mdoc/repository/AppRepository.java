package com.mdoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mdoc.model.App;

public interface AppRepository extends MongoRepository<App, Integer>{

}