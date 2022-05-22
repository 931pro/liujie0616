package com.LiuJie.Controller;

import com.LiuJie.Dao.ProductDao;
import com.LiuJie.Model.Category;
import com.LiuJie.Model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShopServlet", value = "/shop")
public class ShopServlet extends HttpServlet {
    Connection con=null;

    @Override
    public void init() throws ServletException {
        super.init();
        con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category>categoryList=Category.findAllCategory(con);
            request.setAttribute("categoryList",categoryList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ProductDao productDao=new ProductDao();
       if(request.getParameter("categoryId")==null){
           //show all products
           try {
               List<Product>productList=productDao.findAll(con);
               request.setAttribute("productList",productList);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }else{
           int catId=Integer.parseInt(request.getParameter("categoryId"));
           try {
               List<Product>productList=productDao.findByCategoryId(catId,con);
               request.setAttribute("productList",productList);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
       request.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
