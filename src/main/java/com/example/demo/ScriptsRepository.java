package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScriptsRepository extends JpaRepository<Scripts, Integer> {
    List<Scripts> findByName(String name);
}
