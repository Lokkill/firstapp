package ru.geekbrains.servlet.data.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserData {
    private static Map<Long, User> users = new ConcurrentHashMap<>();

    private AtomicLong atomicLong = new AtomicLong(0);

    public List<User> findAll(){
        return new ArrayList<>(users.values());
    }

    public User findById(Long id){
        return users.get(id);
    }

    public void saveOrUpdate(User user){
        if (user.getId() == null){
            Long id = atomicLong.incrementAndGet();
            user.setId(id);
        }
        users.put(user.getId(), user);
    }

    public void deleteById(Long id){
        users.remove(id);
    }
}
