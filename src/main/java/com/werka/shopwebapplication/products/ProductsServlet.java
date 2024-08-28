package com.werka.shopwebapplication.products;

import com.werka.shopwebapplication.products.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDao();
    private BookCategoryDao bookCategoryDao = new BookCategoryDao();
    private BookDao bookDao = new BookDao();
    private List<Category> categories = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String category = request.getParameter("category");
        String sortBy = request.getParameter("sortBy");
        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "best-selling";
        }
        if(categories == null){
            categories = categoryDao.findAll();
        }

        List<Book> resultBooks = new ArrayList<>();

        if(category == null || category.equals("all")){
            resultBooks.addAll(bookDao.findAll());
        }else {
            List<BookCategory> booksCategories =  bookCategoryDao.findAll();
            List<Book> books = bookDao.findAll();
            for(Book book: books){
                for (BookCategory bookCategory : booksCategories) {
                    if (book.getId() == bookCategory.getBookId()) {
                        for(Category category1 : categories) {
                            if(category1.getId() == bookCategory.getCategoryId() && category1.getCategory().equals(category)) {
                                resultBooks.add(book);
                            }
                        }
                    }
                }
            }
        }

        if(sortBy.equals("best-selling")){
            resultBooks.sort(Comparator.comparingDouble(Book::getRating).reversed());
        }else if(sortBy.equals("price-low-high")){
            resultBooks.sort(Comparator.comparing(Book::getPrice));
        }else if(sortBy.equals("price-high-low")){
            resultBooks.sort(Comparator.comparing(Book::getPrice).reversed());
        }else if(sortBy.equals("title-az")){
            resultBooks.sort(Comparator.comparing(Book::getTitle));
        }else if(sortBy.equals("title-za")){
            resultBooks.sort(Comparator.comparing(Book::getTitle).reversed());
        }

        int resultsSize = resultBooks.size();

        request.setAttribute("resultBooks", resultBooks);
        request.setAttribute("resultsSize", resultsSize);
        request.setAttribute("categories", categories);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("category", category);

        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //obsługuje wyszukiwanie poszczególnych wyników przez użytkownika (pasek wyszukiwania)

    }

}
