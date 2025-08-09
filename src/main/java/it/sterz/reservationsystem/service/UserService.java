package it.sterz.reservationsystem.service;

import it.sterz.reservationsystem.dto.UserDto;
import it.sterz.reservationsystem.dto.response.UserResponseDto;
import it.sterz.reservationsystem.entity.User;
import it.sterz.reservationsystem.exception.NotFoundException;
import it.sterz.reservationsystem.mapper.UserMapper;
import it.sterz.reservationsystem.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Page<UserResponseDto> getAllUsers(Pageable pageable) {

        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(userMapper::toResponseDto);
    }

    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'utente con id " + id + " non trovato"));
        return userMapper.toResponseDto(user);
    }

    public UserDto createUser(UserDto userDto) {

        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    public UserDto updateUser(UserDto userDto, Long id) {

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'utente con id " + id + " non trovato"));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());

        User updateUser = userRepository.save(existingUser);
        return userMapper.toDto(updateUser);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("L'Utente con id " + id + " non trovato"));

        userRepository.delete(user);
    }
}