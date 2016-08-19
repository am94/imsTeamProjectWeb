package com.revature.data;

import java.util.Date;
import java.util.Set;

import com.revature.beans.Address;
import com.revature.beans.CategoryDescription;
import com.revature.beans.Client;
import com.revature.beans.ClientType;
import com.revature.beans.Invoice;
import com.revature.beans.InvoicePK;
import com.revature.beans.Order;
import com.revature.beans.Product;
import com.revature.beans.State;
import com.revature.dataAccess.DataLayerAccess;

public class BusinessLayer {
	
	private DataLayerAccess dla;
	
	/////////////////////////// BEGIN ADD CLIENT //////////////////////////
	
	//Needs to have a State Name passed in to get State.
	//Use returned state object for making the Address
	public State getState(String stateName){
		State state = dla.getState(stateName);
		return state;
	}
	
	//Pass an Address object to add the Address
	public void addAddress(Address address){
		dla.createObject(address);
	}
	
	//Get the clientTypeId object chosen from the Drop-down list
	public ClientType getClientType(int clientTypeId){
		ClientType clientType = dla.getClientType(clientTypeId);
		
		return clientType;
	}
	
	//Now add the Client! WOO!
	//Probably should change to boolean for a check
	public void addClient(Client client){
		dla.createObject(client);
	}

	////////////////////////// END ADD CLIENT ///////////////////////////
	
	
	///////////////////////// BEGIN UPDATE AND 'DELETE' CLIENT ////////////////////////////////
	
	//retrieve list of clients
	//Use to refresh the clients on the web side
	public Set<Client> getClients(){
		Set<Client> clients = dla.allClients();
		
		return clients;
	}
	
	//pass the changed client back
	public void updateClient(Client client){
		dla.createObject(client);
	}
	
	//pass to 'delete' client
	public void deleteClient(Client client){
		dla.terminateClient(client);
	}
	
	///////////////////////// END UPDATE AND 'DELETE' CLIENT //////////////////////////////////
	
	
	///////////////////////// BEGIN ADD PRODUCTS //////////////////////////////////////////////

	//get the Category Description based on the Drop Down list
	public Set<CategoryDescription> getCatDesc(String categoryChoice){
		Set<CategoryDescription> catDesc = dla.getCatChoice(categoryChoice);
		
		return catDesc;
	}
	
	//use a form to create a new product, pass the created object back
	//can change this to send back a boolean for verification of adding the item
	public void addProduct(Product product){
		dla.createObject(product);
	}
	
	//////////////////////// END ADD PRODUCTS ////////////////////////////////////////////////
	
	
	/////////////////////// BEGIN UPDATE AND 'DELETE' PRODUCTS //////////////////////////////////////
	
	//get all products
	public Set<Product> getAllProducts(){
		Set<Product> products = dla.getProducts();
		
		return products;
	}
	
	//update product
	public void updateProduct(Product product){
		dla.createObject(product);
	}
	
	//'delete' product
	public void deleteProduct(Product product){
		dla.deleteProduct(product);
	}
	
	////////////////////// END UPDATE AND 'DELETE' PRODUCTS /////////////////////////////////////////
	
	
	////////////////////// BEGIN GENERATING REPORTS ////////////////////////////////////////////////
	
	///grab all reports (for now), and work on them on the front end. 
	public Set<Invoice> getInvoices(){
		Set<Invoice> invoices = dla.getAllInvoices();
		
		return invoices;
		
	}
	
	public Set<Order> getClientOrders(int clientId){
		Set<Order> orders = dla.getClientInvoices(clientId);
		
		return orders;
	}
	
	////////////////////// END GENERATING REPORTS /////////////////////////////////////////////////
	
	
	/////////////////// BEGIN CREATING INCOMING AND OUTGOING INVOICES ///////////////////////////////
	

	// pass clientType for the drop-down list
	public Set<ClientType> getClientTypes(){
		Set<ClientType> clientTypes = dla.clientTypeList();
		
		return clientTypes;
	}
	

	//get Client list for drop down based on the user's choice
	public Set<Client> getClientList(int clientTypeId){
		Set<Client> clients = dla.clientList(clientTypeId);
		
		return clients;
	}
	
	//choose Client and get list of items
	//Called in methods further up pass the quantity amount back to
	//change quantity of product, make sure to send back a negative 
	//number to subtract and positive to add to stock
	public void stockChange(Product product, int quantity){
		product.setStock(product.getStock() + quantity);
		dla.createObject(product);
	}
	
	
	//create invoice
	//set date upside of the object to retrieve the 
	// correct order number, so that the invoice can be properly made
	// just need to subtract or add stock value of product; depending on the return
	public void createOrder(Order order){
		dla.createObject(order);
	}
	
//	//re-grab orderObj as the new orderId has changed
	public Order getNewOrder(Date date){
		Order order = dla.getNewOrder(date);
		
		return order;
	}
	
	//Use new Order Object to make Invoice PK
	//Pass it down to be created
	public void createIPK(InvoicePK obj){
		 dla.createObject(obj);
		
	}
	
	//make invoice with InvoicePK and the product
	//pass it down to be stored
	public void createInvoice(Invoice invoice){
		dla.createObject(invoice);
	}
	
	
	////////////////////// END CREATING INCOMING AND OUTGOING INVOICES //////////////////////////////
}
