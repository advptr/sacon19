package com.oreilly.sacon.library.availability;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAvailabilityRepository extends JpaRepository<Book, Long> {
}