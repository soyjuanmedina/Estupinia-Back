package com.estupinia.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estupinia.models.Theme;
import com.estupinia.models.User;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {
	
}
