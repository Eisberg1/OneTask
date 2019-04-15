package com.example.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Author:Sphinx
 * Date:2019/04/10 13:34
 * Description:
 */
@TableName("t_book")
@Data
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private int bookId;
    @TableField
    private String bookName;
    @TableField
    private BigDecimal bookPrice;





   /* private Integer price;
    private String  name;
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Book> bookClass = Book.class;
        Constructor<Book> c = bookClass.getConstructor(Integer.class,String.class);
        int parameterCount = c.getParameterCount();
        Book book = c.newInstance(5,"44");
        System.out.println(book);
    }
    public Book(){}
    public Book(Integer price,String name){
        this.name=name;
        this.price=price;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Book{" +
                "price=" + price +
                ", name='" + name + '\'' +
                '}';
    }*/
}
