package com.werka.shopwebapplication.domain.api;

import com.werka.shopwebapplication.config.DataHelper;
import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.basket.BasketDao;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethod;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethodDao;
import com.werka.shopwebapplication.domain.orders.OrderDao;

import java.util.List;

public class OrderService {

    private final BookService bookService = new BookService();
    private final DeliveryMethodDao deliveryMethodDao = new DeliveryMethodDao();
    private final OrderDao orderDao = new OrderDao();
    private final BasketDao basketDao = new BasketDao();

    public OrderFullInfo getOrderInfo() {
        List<BasicBasketBookInfo> books = bookService.getBooksInBasket(DataHelper.getClientId());
        double totalPrice = bookService.getOrderTotal();
        DeliveryMethod deliveryMethod = bookService.getDeliveryMethod();
        return new OrderFullInfo(books, totalPrice, deliveryMethod);
    }

    public void saveDeliveryMethod(String method) {
        CurrentOrderId.setOrderId(orderDao.getNewOrderId());
        boolean isDeliverySaved = deliveryMethodDao.isDeliverySaved();
        if(isDeliverySaved) {
            deliveryMethodDao.updateDeliveryMethod(method);
        } else {
            int orderId = CurrentOrderId.getOrderId();
            deliveryMethodDao.saveDeliveryMethod(method, orderId);
        }
    }

    public void saveOrder() {
        for(BasicBasketBookInfo book : bookService.getBooksInBasket(DataHelper.getClientId())) {
            orderDao.saveOrder(book.getId());
        }
    }

    public void removeBooksFromBasket() {
        for(BasketBooksInfo book : basketDao.getBooksInBasket(DataHelper.getClientId())) {
            basketDao.removeBookFromBasket(book.getBookId());
        }
    }

}
