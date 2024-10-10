package com.werka.shopwebapplication.domain.api;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.basket.Basket;
import com.werka.shopwebapplication.domain.basket.BasketDao;
import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.book.BookDao;
import com.werka.shopwebapplication.domain.bookCategory.BookCategory;
import com.werka.shopwebapplication.domain.bookCategory.BookCategoryDao;
import com.werka.shopwebapplication.domain.category.Category;
import com.werka.shopwebapplication.domain.category.CategoryDao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    private BookDao bookDao = new BookDao();
    private BookCategoryDao bookCategoryDao = new BookCategoryDao();
    private CategoryDao categoryDao = new CategoryDao();
    private BasketDao basketDao = new BasketDao();

    public List<BookBasicInfo> findSortedBooksByCategory(String category, String sortBy) {

        List<BookBasicInfo> resultBooks = findAllBooksByCategory(category);

        if(sortBy.equals("best-selling")){
            resultBooks.sort(Comparator.comparingDouble(BookBasicInfo::getRating).reversed());
        }else if(sortBy.equals("price-low-high")){
            resultBooks.sort(Comparator.comparing(BookBasicInfo::getPrice));
        }else if(sortBy.equals("price-high-low")){
            resultBooks.sort(Comparator.comparing(BookBasicInfo::getPrice).reversed());
        }else if(sortBy.equals("title-az")){
            resultBooks.sort(Comparator.comparing(BookBasicInfo::getTitle));
        }else if(sortBy.equals("title-za")){
            resultBooks.sort(Comparator.comparing(BookBasicInfo::getTitle).reversed());
        }
        return resultBooks;
    }

    private List<BookBasicInfo> findAllBooksByCategory(String category) {

        List<BookBasicInfo> resultBooks = new ArrayList<>();

        if(category.equals("all")){
            resultBooks.addAll(bookDao.findAll().stream().map(BookBasicMapper::map).collect(Collectors.toList()));
        }else {
            List<BookCategoryInfo> booksCategories =  bookCategoryDao.findAll().stream().map(BookCategoryMapper::map).collect(Collectors.toList());
            List<BookBasicInfo> books = bookDao.findAll().stream().map(BookBasicMapper::map).collect(Collectors.toList());
            for(BookBasicInfo book: books){
                for (BookCategoryInfo bookCategory : booksCategories) {
                    if (book.getId() == bookCategory.getBookId()) {
                        for(Category category1 : categoryDao.findAll()) {
                            if(category1.getId() == bookCategory.getCategoryId() && category1.getCategory().equals(category)) {
                                resultBooks.add(book);
                            }
                        }
                    }
                }
            }
        }
        return resultBooks;
    }

    public Optional<BookFullInfo> findBookByTitle(String title) {
        return bookDao.findBookByTitle(title)
                .map(BookFullInfoMapper::map);
    }

    public Optional<BookBasicInfo> findBookBasicInfoByTitle(String title) {
        return bookDao.findBookByTitle(title)
                .map(BookBasicMapper::map);
    }

    public void saveBook(String title) {

        BookBasicInfo bookBasicInfo = findBookBasicInfoByTitle(title).orElseThrow();

        int clientId = DataHelper.getClientId();
        System.out.println("client id = " + clientId);

        // jak tu mieć client id?
        basketDao.saveBook(new Basket(clientId, bookBasicInfo.getId()));

    }

    public List<BookBasicInfo> getBestRatedBooks(int number) {
        return bookDao.getBestRatedBooks(number).stream().map(BookBasicMapper::map).collect(Collectors.toList());
    }

    public List<BookBasicInfo> getRecommendedBooks() {
        return bookDao.getRecommendedBooks().stream().map(BookBasicMapper::map).collect(Collectors.toList());
    }

    private static class BookBasicMapper {
        static BookBasicInfo map(Book c) {
            return new BookBasicInfo(
                    c.getId(),
                    c.getTitle(),
                    c.getAuthor(),
                    c.getPrice(),
                    c.getRating()
            );
        }
    }

    private static class BookCategoryMapper {
        static BookCategoryInfo map(BookCategory c) {
            return new BookCategoryInfo(
                    c.getId(),
                    c.getBookId(),
                    c.getCategoryId()
            );
        }
    }

    private static class BookFullInfoMapper {
        static BookFullInfo map(Book c) {
            return new BookFullInfo(
                    c.getId(),
                    c.getTitle(),
                    c.getAuthor(),
                    c.getDescription(),
                    c.getPrice(),
                    c.getPublisher(),
                    c.getRating(),
                    c.getSeries(),
                    c.getPages(),
                    c.getCover(),
                    c.getPublicationDate()
            );
        }
    }


}
