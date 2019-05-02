package com.example.changeapp.dao;

import com.example.changeapp.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MachineDAO extends JpaRepository<Machine, Long> {

}
