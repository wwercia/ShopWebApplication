package com.werka.shopwebapplication.domain.api.services;


import com.werka.shopwebapplication.domain.api.CategoryName;
import com.werka.shopwebapplication.domain.category.Category;
import com.werka.shopwebapplication.domain.category.CategoryDao;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();

    public List<CategoryName> findAllCategoryNames() {
        return categoryDao.findAll()
                .stream().map(CategoryNameMapper::map)
                .collect(Collectors.toList());
    }

    private static class CategoryNameMapper {
        static CategoryName map(Category c) {
            return new CategoryName(
                    c.getId(),
                    c.getCategory()
            );
        }
    }

}