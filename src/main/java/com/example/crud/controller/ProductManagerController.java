package com.example.crud.controller;

import com.example.crud.constants.ResponseCode;
import com.example.crud.model.Provider;
import com.example.crud.repository.ProductRepository;
import com.example.crud.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.crud.model.Product;
import com.example.crud.service.ProductService;
import com.example.crud.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductManagerController {
	
	@Autowired
    private ProductService productService;

	@Autowired
    private ProductRepository productRepository ;



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> products = productService.findAllProduct();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(
            @PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);

        if (!product.isPresent()) {
            return new ResponseEntity<>(product.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product",
            method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	productService.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product product) {
        Optional<Product> currentProduct = productService.findById(id);

        if (!currentProduct.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentProduct.get().setName(product.getName());
        currentProduct.get().setPrice(product.getPrice());
      currentProduct.get().setDescription(product.getDescription());

      productService.save(currentProduct.get());
        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/product/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(
            @PathVariable("id") Integer id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(product.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/product/search", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public ResponseEntity<Response> getListProducts(
            @RequestParam(value = "name", required = false, defaultValue = "") String name
    ) {
        Response response = new Response();
        List<Product> products = null;
        try {
            products = productRepository.findByProduct(name);
            response.setCode(ResponseCode.SUCCESS);
            response.setData(products);
        }catch(Exception e) {
//            logger.info("ERROR getProviders method GET : " + e.toString());
            System.out.println("ERROR getProviders method GET : " + e.toString());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}