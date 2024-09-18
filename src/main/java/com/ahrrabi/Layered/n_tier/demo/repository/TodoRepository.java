package com.ahrrabi.Layered.n_tier.demo.repository;

import com.ahrrabi.Layered.n_tier.demo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {}

