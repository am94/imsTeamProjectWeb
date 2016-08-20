package com.revature.controllers;

import java.util.List;
import java.util.Set;
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

import com.revature.beans.Client;
import com.revature.beans.Invoice;
import com.revature.beans.Product;
import com.revature.dataAccess.ManagementDAO;

@Controller
public class AjaxController implements ServletContextAware, InitializingBean
{
	@Autowired
	private ServletContext servletContext; //instance var

/*	private List<Client> clients = new Vector<Client>();
	private List<Client> invoices = new Vector<Client>();
	private List<Client> products = new Vector<Client>();*/
	
	@SuppressWarnings("unchecked")
	private List<Client> clients = (List<Client>) new ManagementDAO().getAllClients();
	@SuppressWarnings("unchecked")
	private List<Invoice> invoices = (List<Invoice>) new ManagementDAO().getInvoices();
	@SuppressWarnings("unchecked")
	private List<Product> products = (List<Product>) new ManagementDAO().getAllProducts();

	@RequestMapping(
			method=RequestMethod.GET, 
			value="getAll.do", 
			produces="application/json")
	@ResponseBody	// write return value directly to HTTP response
					// in the specified content-type (produces=content-type)
	
	/* =================================================================
	 * ================!!!!!CLIENT CONTOLLER STUFFS!!!!!================
	 * =================================================================
	 */
	
	public List<Client> getClients()
	{
		return clients;
	}

	@RequestMapping(value="clientInfo.do", method=RequestMethod.GET)
	public Client getClientInfo(HttpServletRequest request, HttpServletResponse response)  //This class will be used to sort client lists...hopefully it works as planned
	{
		Client info = new ManagementDAO().getClient(request.getParameter("name"));

		request.setAttribute("id", info.getId());
		request.setAttribute("name", info.getName());
		request.setAttribute("email", info.getEmail());
		request.setAttribute("pocName", info.getPocName());
		request.setAttribute("phone", info.getPhone());
		request.setAttribute("fax", info.getFax());
		request.setAttribute("address", info.getAddress());
		request.setAttribute("address", info.getClientType());
		
		return null;
	}

	@RequestMapping(value="clientList.do", method=RequestMethod.GET)
	public ModelAndView showClients(
						HttpServletRequest request,
						HttpServletResponse response)
	{
		int type = 0;
		if(request.getParameter("clientType").equals("Outgoing")) type = 1;
		
		@SuppressWarnings("unchecked")
		Vector<Client> clients = (Vector<Client>) new ManagementDAO().getClients(type);
		
		this.servletContext.setAttribute("Clients", clients); //update clients
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("clientView"); // view.jsp IRVR
		mv.addObject("clientView", clients); // request-scoped variables
		
		return mv;
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
		Vector<Client> clients = (Vector<Client>)this.servletContext.getAttribute("clients");
		clients.add(client);
		
		this.servletContext.setAttribute("Clients", clients); //update peeps
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("clientView"); // view.jsp IRVR
		mv.addObject("clientView", client); // request-scoped variables
		
		request.getSession().setAttribute("user", client.getName());
		return mv;
	}

	/* =================================================================
	 * ================!!!!!CLIENT CONTOLLER STUFFS!!!!!================
	 * =================================================================
	 */
	

	/* ==================================================================
	 * ================!!!!!INVOICE CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */
	
	public List<Invoice> getInvoices()
	{
		return invoices;
	}
	
	@RequestMapping(value="getInvoice.do", method=RequestMethod.GET)
	public ModelAndView getInvoiceByClient(
			HttpServletRequest request,
			HttpServletResponse response)
	{		
		Set<Invoice> invoices = new ManagementDAO().getClientInvoices(request.getParameter("clientName"));

		this.servletContext.setAttribute("Invoices", invoices); //update invoices
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("invoiceView"); // view.jsp IRVR
		mv.addObject("invoiceView", invoices); // request-scoped variables
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
		return mv;
	}

	/* ==================================================================
	 * ================!!!!!INVOICE CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */

	
	/* ==================================================================
	 * ================!!!!!PRODUCT CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */
	
	public List<Product> getProducts()
	{
		return products;
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
		return mv;
	}

	
	/* ==================================================================
	 * ================!!!!!PRODUCT CONTOLLER STUFFS!!!!!================
	 * ==================================================================
	 */
	
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

	}
	
	
	
}
