package com.mainclass.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mainclass.entities.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
