package com.revature.controllers;

import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.revature.beans.Address;
import com.revature.beans.Client;
import com.revature.beans.Invoice;
import com.revature.beans.Product;

@Controller
public class AjaxController implements ServletContextAware, InitializingBean
{
	@Autowired
	private ServletContext servletContext; //instance var
	
	private List<Client> clients = new Vector<Client>();

	@RequestMapping(
			method=RequestMethod.GET, 
			value="getAll.do", 
			produces="application/json")
	@ResponseBody	// write return value directly to HTTP response
					// in the specified content-type (produces=content-type)
	public List<Client> getClients()
	{
		return clients;
	}

	@RequestMapping(value="regClient.do", method=RequestMethod.GET)
	public ModelAndView registerClient(
						@ModelAttribute("client") @Valid Client client,
						BindingResult bindingResult,
						HttpServletRequest request,
						HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Client> clients = 
				(Vector<Client>)this.servletContext.getAttribute("clients");
		clients.add(client);
		this.servletContext.setAttribute("Clients", clients); //update peeps
		ModelAndView mv = new ModelAndView();
		mv.setViewName("clientView"); // view.jsp IRVR
		mv.addObject("success", "Successfully added a Client!"); // request-scoped variables
		request.getSession().setAttribute("user", "Dan Pickles");
		return mv;
	}
	
	@RequestMapping(value="regInvoice.do", method=RequestMethod.GET)
	public ModelAndView registerInvoice(
			@ModelAttribute("Invoice") @Valid Invoice invoice,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Invoice> invoices = 
			(Vector<Invoice>)this.servletContext.getAttribute("people");
		invoices.add(invoice);
		this.servletContext.setAttribute("Invoices", invoices); //update peeps
		ModelAndView mv = new ModelAndView();
		mv.setViewName("invoiceView"); // view.jsp IRVR
		mv.addObject("success", "Successfully added invoice!"); // request-scoped variables
		request.getSession().setAttribute("user", "Dan Pickles");
		return mv;
	}
	
	@RequestMapping(value="regProduct.do", method=RequestMethod.GET)
	public ModelAndView registerProduct(
			@ModelAttribute("Product") @Valid Product product,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response)
	{
		if(bindingResult.hasErrors())
		{
			return new ModelAndView("Whoops!");
		}
		
		@SuppressWarnings("unchecked")
		Vector<Product> products = 
			(Vector<Product>)this.servletContext.getAttribute("product");
		products.add(product);
		this.servletContext.setAttribute("Products", products); //update peeps
		ModelAndView mv = new ModelAndView();
		mv.setViewName("productView"); // view.jsp IRVR
		mv.addObject("success", "Successfully added product!"); // request-scoped variables
		request.getSession().setAttribute("user", "Dan Pickles");
		return mv;
	}
	
	public Client getClientBy(Object obj)  //This class will be used to sort client lists...hopefully it works as planned
	{
		String classType = String.valueOf(obj.getClass());
		
		classType = classType.substring(classType.lastIndexOf('.')+1);
		
		switch (classType)
		{
		case "Address":	//Do stuff
			break;
		case "String":	//Do stuff
			break;
		case "Integer":	//Do stuff
			break;
		default:break;
		}
		
		return null;
	}
	
	public void afterPropertiesSet() throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public void setServletContext(ServletContext arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String args[])
	{
		new AjaxController().getClientBy(new Address());
	}
	
	
	
}
