package com.calculadoradecostes.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.calculadoradecostes.models.AccountingNote;

@Transactional 
public interface AccountingNoteRepository extends CrudRepository<AccountingNote, Long>{

}
