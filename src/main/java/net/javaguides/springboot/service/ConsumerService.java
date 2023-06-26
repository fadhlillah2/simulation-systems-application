package net.javaguides.springboot.service;

import net.javaguides.springboot.exception.ConsumerNotFoundException;
import net.javaguides.springboot.model.Consumer;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ConsumerService {
	List<Consumer> listAll();
	List<Consumer> listAllByName(String keyWord);
	void save(Consumer user);

	Consumer get(Integer id) throws ConsumerNotFoundException;

	void delete(Integer id) throws ConsumerNotFoundException;
}
