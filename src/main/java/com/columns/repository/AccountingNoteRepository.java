package com.columns.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.columns.models.AccountingNote;

@Transactional 
public interface AccountingNoteRepository extends CrudRepository<AccountingNote, Long>{

}
