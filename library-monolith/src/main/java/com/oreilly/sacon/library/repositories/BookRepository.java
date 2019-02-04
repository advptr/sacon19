package com.oreilly.sacon.library.repositories;

import com.oreilly.sacon.library.dao.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Item, Long> {
}
