package com.example.crud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.model.Bill;
import com.example.crud.model.Customer;
import com.example.crud.model.PaymentMethod;
import com.example.crud.model.Product;
import com.example.crud.model.ShoppingCart;
import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import com.example.crud.repository.BillRepository;
import com.example.crud.repository.ShoppingCartRepository;
import com.example.crud.request.BillRequest;
import com.example.crud.response.BillResponse;
import com.example.crud.response.ShoppingCartResponse;
import com.example.crud.service.BillService;
import com.example.crud.service.CustomerService;
import com.example.crud.service.PaymentMethodService;
import com.example.crud.service.ProductService;
import com.example.crud.service.ShoppingCartService;
import com.example.crud.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class BillController {
	
	@Autowired
    private BillService billService;

	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PaymentMethodService paymentMethodService;
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

    @RequestMapping(value = "/bills", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<BillResponse>> findAllBill(@RequestParam(value = "customerName", required = false, defaultValue = "") String customerName) {
        List<Bill> bills = billRepository.findByBill(customerName);
        if (bills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<BillResponse> billResponses = new ArrayList<BillResponse>();
        
        Iterator<Bill> iter = bills.iterator();
        while (iter.hasNext()) {
        	Bill bill= iter.next();
        	BillResponse billResponse =transferRequest(bill);
        	billResponses.add(billResponse);
        }
        
        return new ResponseEntity<>(billResponses, HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BillResponse> getBillById(
            @PathVariable("id") Integer id) {
        Optional<Bill> bill = billService.findById(id);

        if (!bill.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        
        BillResponse billResponse =transferRequest(bill.get());
        return new ResponseEntity<>(billResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/bill",
            method = RequestMethod.POST)
    public ResponseEntity<BillResponse> createBill(@RequestBody BillRequest billRequest) {
    	Bill bill= new Bill();
    	bill.setCreatedDate(new Date());
    	bill.setUpdatedDate(new Date());
    	bill.setCustomerId(billRequest.getCustomerId());
    	bill.setPaymentMethodId(billRequest.getPaymentMethodId());
    	bill.setTaxCode(billRequest.getTaxCode());
    	bill.setUserId(billRequest.getUserId());
    	List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
    	shoppingCartList = billRequest.getShoppingCartList();    	
    	billService.save(bill);
    	Integer idBill= billRepository.getMaxId();
    	
    	  Iterator<ShoppingCart> iter = shoppingCartList.iterator(); 
    	  Double totalPriceBill = 0D;
    	  while (iter.hasNext()) { 
    		  ShoppingCart shoppingCart=iter.next();
    		  shoppingCart.setBillId(idBill);
    		  Optional<Product> product = productService.findById(shoppingCart.getProductId());
    		  Double price  = product.get().getPrice();
    		  Double totalPrice  = price * shoppingCart.getAmount();
    		  shoppingCart.setTotalPrice(totalPrice);
    		  shoppingCartService.save(shoppingCart);
    		  totalPriceBill = totalPriceBill + shoppingCart.getTotalPrice();
          }
    	
    	
    	bill.setTotalPrice(totalPriceBill);    	
        billService.save(bill);
        BillResponse billResponse =transferRequest(bill);
        return new ResponseEntity<>(billResponse, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<BillResponse> updateBill(
            @PathVariable("id") Integer id,
            @RequestBody BillRequest billRequest) {
        Optional<Bill> currentBillOpt  = billService.findById(id);
        Bill bill = currentBillOpt.get();
      	
    	
    	bill.setUpdatedDate(new Date());
    	bill.setCustomerId(billRequest.getCustomerId());
    	bill.setPaymentMethodId(billRequest.getPaymentMethodId());
    	bill.setTaxCode(billRequest.getTaxCode());
    	bill.setUserId(billRequest.getUserId());
    	List<ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
    	shoppingCartList = billRequest.getShoppingCartList();    	
    	billService.save(bill);
    	
    	
    	  Iterator<ShoppingCart> iter = shoppingCartList.iterator(); 
    	  Double totalPriceBill = 0D;
    	  while (iter.hasNext()) { 
    		  ShoppingCart shoppingCart=iter.next(); 
    		  Optional<Product> product = productService.findById(shoppingCart.getProductId());
    		  Double price  = product.get().getPrice();
    		  Double totalPrice  = price * shoppingCart.getAmount();
    		  shoppingCart.setTotalPrice(totalPrice);
    		  shoppingCartService.save(shoppingCart);
    		  totalPriceBill = totalPriceBill + shoppingCart.getTotalPrice();
          }
    	
    	
    	bill.setTotalPrice(totalPriceBill);    	
        billService.save(bill);
        BillResponse billResponse =transferRequest(bill);
        return new ResponseEntity<>(billResponse, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bill/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<UserRole> deleteBill(
            @PathVariable("id") Integer id) {
        Optional<Bill> bill = billService.findById(id);
        if (!bill.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        billService.remove(bill.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    public BillResponse transferRequest(Bill bill) {
    	
    	BillResponse billResponse =  new BillResponse ();
    	billResponse.setId(bill.getId());
    	billResponse.setCreatedDate(bill.getCreatedDate());
    	billResponse.setUpdatedDate(bill.getUpdatedDate());
    	billResponse.setTaxCode(bill.getTaxCode());
    	billResponse.setTotalPrice(bill.getTotalPrice());
    	
    	
    	Optional<Customer> customer = customerService.findById(bill.getCustomerId());
    	billResponse.setCustomerName(customer.get().getName());
    	
    	Optional<User> user = userService.findById(bill.getUserId());
    	billResponse.setUserName(user.get().getUsername());
    	
    	Optional<PaymentMethod> paymentMethod = paymentMethodService.findById(bill.getPaymentMethodId());
    	billResponse.setPaymentMethodName(paymentMethod.get().getName());
    	
    	List <ShoppingCart> shoppingCartList = new ArrayList<ShoppingCart>();
    	List <ShoppingCartResponse> shoppingCartResList = new ArrayList<ShoppingCartResponse>();
    	shoppingCartList=shoppingCartRepository.findShoppingCart(bill.getId());
    	Iterator<ShoppingCart> iter = shoppingCartList.iterator(); 
    	while (iter.hasNext()) { 
    		ShoppingCart shoppingCart = iter.next();
    		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
    		shoppingCartResponse.setId(shoppingCart.getId());
    		shoppingCartResponse.setBillId(shoppingCart.getBillId());
    		shoppingCartResponse.setAmount(shoppingCart.getAmount());
    		shoppingCartResponse.setTotalPrice(shoppingCart.getTotalPrice());
    		
    		Optional<Product> product=productService.findById(shoppingCart.getProductId());
    		shoppingCartResponse.setProductName(product.get().getName());
    		
    		shoppingCartResList.add(shoppingCartResponse);
    	}
    	billResponse.setShoppingCartList(shoppingCartResList);
    	
    	return billResponse;
    }
}

