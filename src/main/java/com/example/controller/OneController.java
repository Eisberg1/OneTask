package com.example.controller;

import com.example.po.Book;
import com.example.po.Report;
import com.example.po.User;
import com.example.service.BookService;
import com.example.service.ReportService;
import com.example.service.UserService;
import com.example.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Author:Sphinx
 * Date:2019/03/25 16:15
 * Description:
 */
@Controller
public class OneController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "html/login";
    }

    @RequestMapping("/check")
    public String check(User user, Model model) {
        System.out.println(user);
        try {
            userService.login(user);
        } catch (UnknownAccountException e) {
            model.addAttribute("loginError", "用户名不存在");
            return "html/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("loginError", "密码错误");
            return "html/login";
        } catch (AuthenticationException e) {
            model.addAttribute("loginError", "登陆失败");
            return "html/login";
        }
        return "jsp/main";


        //if (user.getUserId() > 1) {
        //    return "jsp/main";
        //} else {
        //    model.addAttribute("user",user);
        //    return "html/regist";
        //}
    }

    @RequestMapping("/book")
    public String book() {
        return "jsp/book";
    }

    @ResponseBody
    @RequestMapping("/selectUser")
    public List<User> selectUser() {
        return userService.selectUsers();
    }

    @RequestMapping("/regist")
    public String regist(User user) {
        userService.regist(user);
        return "html/login";
    }

    @RequestMapping("/importExcel")
    public String importExcel(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        //String path=request.getSession().getServletContext().getRealPath("upload");
        //File filePath = new File(path);
        //if (!filePath.exists()){
        //    filePath.mkdirs();
        //}
        String path = "F:\\idea_code\\test_code\\OneTask\\src\\main\\webapp\\upload";
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        List<Report> list = ExcelUtil.importExcel(file.getInputStream(), suffix, 1);
        System.out.println(list);
        int count = reportService.insert(list);
        System.out.println("插入" + count + "条数据成功");
        //try {
        //    file.transferTo(new File(path,fileName));//可以对文件名字进行一下修改，加时间戳，或使用UUID
        //    System.out.println(path);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        return "jsp/success";
    }


    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        OutputStream outputStream = null;
        try {
            Thread.sleep(8000);
            List<Report> reportList = reportService.selectAll();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(reportList);
            response.setContentType("application/ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "testX.xlsx");
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }



    /*
    @ResponseBody
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        //OutputStream out = null;
        OutputStream outputStream=null;
        ByteArrayOutputStream os=null;
        try {
            List<Report> reportList = reportService.selectAll();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(reportList);
            response.setContentType("application/ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "testX.xlsx");
            outputStream = response.getOutputStream();
            os = new ByteArrayOutputStream();
            workbook.write(os);
            byte[] bytes = os.toByteArray();
            //outputStream.write(bytes);
            //outputStream.flush();
            StreamUtils.copy(bytes,outputStream);

            //out = new FileOutputStream(new File("F:\\testX.xlsx"));
            //workbook.write(out);

            //return outputStream;
            //return "success";
        } catch (Exception e) {
            e.printStackTrace();
            //return null;
            //return "error";
        } finally {
            //if (out != null) {
            //    out.close();
            //}
            if (outputStream!=null){
                outputStream.close();
            }
        }
    }
    */



    @Autowired
    BookService bookService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/showBooks")
    public String showBooks() {
        Book book = new Book();
        book.setBookName("方法");
        book.setBookPrice(new BigDecimal(2.33445566).setScale(2,BigDecimal.ROUND_HALF_UP));
        int insert = bookService.insert(book);
        System.out.println(insert);
        List<Book> books = bookService.getBooks();
        redisTemplate.opsForValue().set("books",books);
        //return books;
        return "success";
    }


    @ResponseBody
    @GetMapping("/getBooks/{key}")
    public Object getBooks(@PathVariable String key){
       return redisTemplate.opsForValue().get(key);
    }

    @ResponseBody
    @GetMapping("/get/{key}")
    public String getRedis(@PathVariable(name="key") String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @ResponseBody
    @GetMapping("/set/{key}/{value}")
    public String getRedis(@PathVariable(name="key")String key,@PathVariable(name="value")String value){
        stringRedisTemplate.opsForValue().set(key,value);
        return "SUCCESS";
    }


}
