package com.example.service;

import com.example.po.Book;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/11 9:25
 * Description:
 */
public interface BookService {

    int insert(Book book);

    List<Book> getBooks();

}
