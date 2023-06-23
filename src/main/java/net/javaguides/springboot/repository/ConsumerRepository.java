package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConsumerRepository extends CrudRepository<Consumer, Integer>, JpaRepository<Consumer, Integer> {
    public Long countById(Integer id);

    public List<Consumer> findByNameContains(String keyWord);
}
