package com.Auth.Service;

import com.Auth.Exception.UserAlreadyExistException;
import com.Auth.Model.User;
import com.Auth.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * <p>Сохранение пользователя</p>
     * @param user пользователь
     * @return сохраненный пользователь
     */
    public User save(User user){
        return userRepository.save(user);
    }

    /**
     * Создание пользователя
     * @param user
     * @return Созданный пользователь
     */
    public User create(User user){
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistException("Пользователь с таким именем уже существует");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistException("Пользователь с таким Email уже существует");
        }
        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     * @param username имя пользователя
     * @return пользователь
     */
    public User getByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    /**
     * Получение пользователя по имени **для Spring Security**
     * @return
     */
    public UserDetailsService userDetailsService(){
        return this::getByUsername;
    }

    /**
     * Получение текущего пользователя
     * @return текущий пользователь
     */
    public User getCurrentUser(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

}
