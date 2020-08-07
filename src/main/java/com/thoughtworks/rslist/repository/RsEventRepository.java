package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RsEventRepository extends CrudRepository<RsEventEntity, Integer> {
    List<RsEventEntity> findAll();
    @Transactional
    void deleteByUserId(int userId);
    @Transactional
    RsEventEntity findByUserId(int userId);
}
