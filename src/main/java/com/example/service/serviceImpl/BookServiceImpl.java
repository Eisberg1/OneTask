package com.example.service.serviceImpl;

import com.example.dao.BookDAO;
import com.example.po.Book;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/11 9:25
 * Description:
 */

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDAO bookDAO;

    @Override
    public int insert(Book book) {
        //Jedis resource = new JedisPool().getResource();
        return bookDAO.insert(book);
    }

    @Override
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }
}
