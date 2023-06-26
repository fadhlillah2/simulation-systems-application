package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ConsumerNotFoundException;
import net.javaguides.springboot.model.Consumer;
import net.javaguides.springboot.repository.ConsumerRepository;
import net.javaguides.springboot.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired private ConsumerRepository repo;

    @Override
    public List<Consumer> listAll(){
        return (List<Consumer>) repo.findAll();
    }

    @Override
    public List<Consumer> listAllByName(String keyWord){
        if(keyWord != null){
            return (List<Consumer>) repo.findByNameContains(keyWord);
        } else {
            return (List<Consumer>) repo.findAll();
        }

    }

    @Override
    public void save(Consumer user) {
        repo.save(user);
    }

    @Override
    public Consumer get(Integer id) throws ConsumerNotFoundException {
        Optional<Consumer> result = repo.findById(id);
        if(result.isPresent()) {
            return result.get();
        }
        throw new ConsumerNotFoundException("Could not find any users with ID"+ id);
    }

    @Override
    public void delete(Integer id) throws ConsumerNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0) {
            throw new ConsumerNotFoundException("Could not find any users with ID"+ id);
        }
        repo.deleteById(id);
    }

}
