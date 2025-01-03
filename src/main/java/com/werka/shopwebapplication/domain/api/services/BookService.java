package com.werka.shopwebapplication.domain.api.services;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.api.*;
import com.werka.shopwebapplication.domain.basket.Basket;
import com.werka.shopwebapplication.domain.basket.BasketDao;
import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.book.BookDao;
import com.werka.shopwebapplication.domain.bookCategory.BookCategory;
import com.werka.shopwebapplication.domain.bookCategory.BookCategoryDao;
import com.werka.shopwebapplication.domain.category.Category;
import com.werka.shopwebapplication.domain.category.CategoryDao;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethod;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethodDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookService {

    private final BookDao bookDao = new BookDao();
    private final BookCategoryDao bookCategoryDao = new BookCategoryDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final BasketDao basketDao = new BasketDao();
    private final DeliveryMethodDao deliveryMethodDao = new DeliveryMethodDao();

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

    public List<BookFullInfo> findSearchedBookByTitle(String title) {
        Optional<BookFullInfo> book = bookDao.findSearchedBookByTitle(title).map(BookFullInfoMapper::map);
        List<BookFullInfo> booksFound = new ArrayList<>();
        if(book.isEmpty()){
            List<BookFullInfo> books = bookDao.findSearchedBooksWithTextInTitle(title).stream().map(BookFullInfoMapper::map).collect(Collectors.toList());
            if(!books.isEmpty()){
                booksFound = books;
            }
        }else {
            booksFound.add(book.get());
        }
        return booksFound;
    }

    public Optional<BookBasicInfo> findBookBasicInfoByTitle(String title) {
        return bookDao.findBookByTitle(title)
                .map(BookBasicMapper::map);
    }

    public void saveBook(String title) {
        BookBasicInfo bookBasicInfo = findBookBasicInfoByTitle(title).orElseThrow();
        int clientId = DataHelper.getClientId();
        basketDao.saveBook(new Basket(clientId, bookBasicInfo.getId()));
    }

    public double getOrderTotal() {
        List<BasicBasketBookInfo> books = getBooksInBasket(DataHelper.getClientId());
        BigDecimal total = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
        for(BasicBasketBookInfo book : books) {
            total = total.add(book.getPrice());
        }
        return total.doubleValue();
    }

    public void updateBookQuantity(String title, int quantity) {
        List<BasicBasketBookInfo> booksInBasket = getBooksInBasket(DataHelper.getClientId());
        basketDao.updateBookQuantity(title, quantity, booksInBasket);
    }

    public List<BookBasicInfo> findBooksInSeries(BookFullInfo book) {
        List<BookBasicInfo> booksInSeries =  bookDao.findBooksInSeries(book.getSeries()).stream().map(BookBasicMapper::map).collect(Collectors.toList());
        BookBasicInfo bookToRemove = null;
        for(BookBasicInfo bookk : booksInSeries) {
            if(book.getTitle().equals(bookk.getTitle())){
                bookToRemove = bookk;
            }
        }
        if(bookToRemove != null) {
            booksInSeries.remove(bookToRemove);
        }
        return booksInSeries;
    }

    public List<BasicBasketBookInfo> getBooksInBasket(int clientId) {

        List<BasketBooksInfo> basketBooks = basketDao.getBooksInBasket(clientId);
        List<BasicBasketBookInfo> result = new ArrayList<>();
        for(BasketBooksInfo book : basketBooks){
            BookBasicInfo b = findBookById(book.getBookId()).orElseThrow();
            BigDecimal price = BigDecimal.valueOf(b.getPrice().floatValue() * book.getQuantity()).setScale(2, RoundingMode.HALF_EVEN);
            BasicBasketBookInfo finalBook = new BasicBasketBookInfo(b.getId(), b.getTitle(), b.getAuthor(), price, b.getRating(), book.getQuantity());
            result.add(finalBook);
        }
        return result;
    }

    public Optional<BookBasicInfo> findBookById(int bookId) {
        return bookDao.findBookById(bookId)
                .map(BookBasicMapper::map);
    }

    public List<BookBasicInfo> getBestRatedBooks(int number) {
        return bookDao.getBestRatedBooks(number).stream().map(BookBasicMapper::map).collect(Collectors.toList());
    }

    public List<BookBasicInfo> getRecommendedBooks() {
        return bookDao.getRecommendedBooks().stream().map(BookBasicMapper::map).collect(Collectors.toList());
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethodDao.findDeliveryMethodByOrderId(CurrentOrderId.getOrderId());
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
