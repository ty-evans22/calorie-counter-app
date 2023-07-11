package com.experiments.exp1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for clients.
 * 
 * @author Tyler Evans
 */

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}