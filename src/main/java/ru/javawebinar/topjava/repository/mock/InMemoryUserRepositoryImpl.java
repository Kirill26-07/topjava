package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private final Map<Integer, User> userRepository = new ConcurrentHashMap<>();

    private AtomicInteger userId = new AtomicInteger(0);

    @Override
    public boolean delete(final int id) {
        log.info("delete {}", id);
        userRepository.remove(id);
        return true;
    }

    @Override
    public User save(final User user) {
        log.info("save {}", user);
        return userRepository.put(userId.incrementAndGet(), user);
    }

    @Override
    public User get(final int id) {
        log.info("get {}", id);
        return userRepository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return userRepository.values().stream()
                .sorted(Comparator.comparing(User::toString))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(final String email) {
        log.info("getByEmail {}", email);
        return userRepository.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst().orElse(null);
    }

}
