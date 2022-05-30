package com.LiuJie.Controller;

import com.LiuJie.Dao.ProductDao;
import com.LiuJie.Model.Category;
import com.LiuJie.Model.Product;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProductServlet", value = "/admin/addProduct")
@MultipartConfig(maxFileSize = 16177215)
public class AddProductServlet extends HttpServlet {
    Connection con=null;
    @Override
    public void init() throws ServletException {
        super.init();
            con=(Connection) getServletContext().getAttribute("con");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> list=Category.findAllCategory(con);
            request.setAttribute("categoryList",list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("../WEB-INF/views/admin/addProduct.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //GET ALL PRAMETERS
        String productName=request.getParameter("productName");
        double price=request.getParameter("price")!=null?Double.parseDouble(request.getParameter("price")):0.0;
        int categoryId =request.getParameter("categoryId")!=null?Integer.parseInt(request.getParameter("categoryId")):8;
        String productDescription =request.getParameter("productDescription");
        InputStream inputStream=null;
        Part fileParts=request.getPart("picture");
        if(fileParts!=null){
            inputStream=fileParts.getInputStream();
        }
        //SET INTO MODEL
        Product product=new Product();
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setPicture(inputStream);
        product.setPrice(price);
        product.setCategoryId(categoryId);
        //CALL SAME IN DAO
        ProductDao productDao=new ProductDao();
        try {
            int n=productDao.save(product,con);
            if(n>0){
                response.sendRedirect("productList");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
