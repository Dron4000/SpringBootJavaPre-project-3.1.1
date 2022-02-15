package com.example.springboot.Service;

import com.example.springboot.Model.User;
import com.example.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    UserRepository userRepository;


  public   List<User> getAllUsers(){
        return userRepository.findAll();

    }

  public   void save(User user){
        userRepository.save(user);
    }

    public  void delete(User user){
        userRepository.delete(user);
    }



    public   User getById(long id){
      return   userRepository.getById(id);
    }


    public   User getUserByEmail(String email_address){
         return  userRepository.getUserByEmail(email_address);
    }



    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = getUserByEmail(emailAddress);
        if (user==null) throw new UsernameNotFoundException("Нет такого "+ emailAddress);
        return  user ;
    }
}
