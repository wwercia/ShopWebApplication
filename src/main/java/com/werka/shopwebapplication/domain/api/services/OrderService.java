package com.werka.shopwebapplication.domain.api.services;

import com.werka.shopwebapplication.domain.CurrentOrderId;
import com.werka.shopwebapplication.domain.api.*;
import com.werka.shopwebapplication.domain.api.orders.OrderBookInfo;
import com.werka.shopwebapplication.domain.api.orders.OrderFullInfo;
import com.werka.shopwebapplication.domain.api.orders.ordersPage.OrderedBook;
import com.werka.shopwebapplication.domain.api.orders.ordersPage.SingleOrderInfo;
import com.werka.shopwebapplication.domain.basket.BasketDao;
import com.werka.shopwebapplication.domain.book.Book;
import com.werka.shopwebapplication.domain.book.BookDao;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethod;
import com.werka.shopwebapplication.domain.delivery.DeliveryMethodDao;
import com.werka.shopwebapplication.domain.orders.Order;
import com.werka.shopwebapplication.domain.orders.OrderBookDao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final BookService bookService = new BookService();
    private final DeliveryMethodDao deliveryMethodDao = new DeliveryMethodDao();
    private final OrderBookDao orderDao = new OrderBookDao();
    private final BasketDao basketDao = new BasketDao();
    private final BookDao bookDao = new BookDao();

    public OrderFullInfo getOrderInfo(int clientId) {
        List<BasicBasketBookInfo> books = bookService.getBooksInBasket(clientId);
        double totalPrice = bookService.getOrderTotal(clientId);
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

    public void saveOrder(int clientId) {
        for(BasicBasketBookInfo book : bookService.getBooksInBasket(clientId)) {
            orderDao.saveOrder(book.getId(), book.getQuantity(), clientId);
        }
    }

    public void removeBooksFromBasket(int clientId) {
        for(BasketBooksInfo book : basketDao.getBooksInBasket(clientId)) {
            basketDao.removeBookFromBasket(book.getBookId());
        }
    }

    public List<SingleOrderInfo> getOrders(int clientId) {

        List<SingleOrderInfo> result = new ArrayList<>();
        List<Order> orders = orderDao.findAll();

        SingleOrderInfo singleOrderInfo = null;
        int orderId = 0;

        for(Order order : orders) {
            if(order.getClientId() == clientId){
                if(orderId == 0)
                    orderId = order.getOrderId();

                if(orderId == order.getOrderId()){
                    if(singleOrderInfo == null) {
                        singleOrderInfo = new SingleOrderInfo(order.getOrderId(), new ArrayList<>(), 0.0, "0");
                    }
                    Book book = bookDao.findBookById(order.getBookId()).orElseThrow();
                    singleOrderInfo.addOrderedBook(new OrderedBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), order.getQuantity()));
                    singleOrderInfo.setTotal(singleOrderInfo.getTotal() + book.getPrice().doubleValue() * order.getQuantity());
                }else {
                    DeliveryMethod deliveryMethod = deliveryMethodDao.findDeliveryMethodByOrderId(orderId);
                    String cost = "";
                    if(deliveryMethod.getDisplayName().equals("pickup")) {
                        cost = "free";
                    }else {
                        cost = deliveryMethod.getPrice() + "";
                    }
                    singleOrderInfo.setDeliveryCost(cost);
                    result.add(singleOrderInfo);
                    singleOrderInfo = new SingleOrderInfo(order.getOrderId(), new ArrayList<>(), 0.0, "0");
                    Book book = bookDao.findBookById(order.getBookId()).orElseThrow();
                    singleOrderInfo.addOrderedBook(new OrderedBook(book.getId(), book.getTitle(), book.getAuthor(), book.getPrice(), order.getQuantity()));
                    singleOrderInfo.setTotal(singleOrderInfo.getTotal() + book.getPrice().doubleValue() * order.getQuantity());
                }
                orderId = order.getOrderId();
            }
        }
        if(singleOrderInfo != null) {
            DeliveryMethod deliveryMethod = deliveryMethodDao.findDeliveryMethodByOrderId(orderId);
            String cost = "";
            if(deliveryMethod.getDisplayName().equals("pickup")) {
                cost = "free";
            }else {
                cost = deliveryMethod.getPrice() + "";
            }
            singleOrderInfo.setDeliveryCost(cost);
            double total = singleOrderInfo.getTotal();
            BigDecimal roundedTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
            singleOrderInfo.setTotal(roundedTotal.doubleValue());
            result.add(singleOrderInfo);
        }

        return result;
    }

    private static class OrderMapper {
        static OrderBookInfo map(Order c) {
            return new OrderBookInfo(
                    c.getId(),
                    c.getBookId(),
                    c.getClientId(),
                    c.getOrderId()
            );
        }
    }

}
