package com.fastcampus.sns.service;

import com.fastcampus.sns.exception.SnsApplicationException;
import com.fastcampus.sns.model.User;
import com.fastcampus.sns.model.entity.UserEntity;
import com.fastcampus.sns.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserEntityRepository userEntityRepository;
    public User join(String userName, String password) {

        Optional<UserEntity> userEntity = userEntityRepository.findByUserName(userName);
        userEntityRepository.save(new UserEntity());
        return new User();
    }

    public String login(String userName, String password) {
        UserEntity userEntity = userEntityRepository.findByUserName(userName)
                .orElseThrow(SnsApplicationException::new);

        if (!userEntity.getPassword().equals(password)) {
            throw new SnsApplicationException();
        }
        return "";
    }
}
