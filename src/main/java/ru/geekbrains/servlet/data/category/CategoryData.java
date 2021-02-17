package ru.geekbrains.servlet.data.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CategoryData {
    private static Map<Long, Category> categories = new ConcurrentHashMap<>();

    private AtomicLong atomicLong = new AtomicLong(0);

    public List<Category> findAll(){
        return new ArrayList<>(categories.values());
    }

    public Category findById(Long id){
        return categories.get(id);
    }

    public void saveOrUpdate(Category category){
        if (category.getId() == null){
            Long id = atomicLong.incrementAndGet();
            category.setId(id);
        }
        categories.put(category.getId(), category);
    }

    public void deleteById(Long id){
        categories.remove(id);
    }
}
