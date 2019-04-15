package com.example.dao;

import com.example.po.Book;

import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/04/11 9:11
 * Description:
 */
public interface BookDAO{

    int insert(Book book);

    List<Book> getBooks();



}
