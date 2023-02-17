package product_servlet.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product_servlet.dao.ProductDAO;
import product_servlet.dto.Product;

public class SaveProduct extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pname=req.getParameter("name");
		String pbrand=req.getParameter("brand");
		String mname=req.getParameter("manufacturername");
		String state = req.getParameter("state");
		double price = Double.parseDouble(req.getParameter("price"));
		
		ServletContext context=getServletContext();
		double cgst=Double.parseDouble(context.getInitParameter("CGST"));
		
		ServletConfig config=getServletConfig();
		double sgst1=Double.parseDouble(config.getInitParameter("SGST1"));
		double sgst2=Double.parseDouble(config.getInitParameter("SGST2"));
		
		if(state.equals("KAR")) {
			price+=(sgst1+cgst)*price;
		}
		if(state.equals("TML")) {
			price+=(sgst2+cgst)*price;
		}
		
		Product product = new Product();
		product.setMname(mname);
		product.setPbrand(pbrand);
		product.setPname(pname);
		product.setPrice(price);
		product.setState(state);
		
		ProductDAO dao=new ProductDAO();
		dao.SaveProduct(product);
	}
}
