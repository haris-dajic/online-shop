package com.OnlineMarket.AdminService.Controllers;

import com.OnlineMarket.AdminService.ActiveParameters;
import com.OnlineMarket.AdminService.Communication.AdminServicePublisher;
import com.OnlineMarket.AdminService.Models.Market;
import com.OnlineMarket.AdminService.Models.Product;
import com.OnlineMarket.AdminService.Models.ProductAmount;
import com.OnlineMarket.AdminService.Repositories.MarketRepository;
import com.OnlineMarket.AdminService.Repositories.ProductAmountRepository;
import com.OnlineMarket.AdminService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    MarketRepository marketRepository;
    @Autowired
    ProductAmountRepository productAmountRepository;
    @Autowired
    AdminServicePublisher adminServicePublisher;
    @Autowired
    ActiveParameters activeParameters;

    @RequestMapping(method = RequestMethod.POST, value = "/product")
    public void addProduct(@RequestBody Map<String, String> data) throws Exception {
        Product newProduct = getProductData(data);
        Market selectedMarket = getSelectedMarket(data);

        Integer amount = Integer.parseInt(data.get("amount"));
        ProductAmount newProductAmount = new ProductAmount(amount, newProduct, selectedMarket);

        data.put("product_id", Integer.toString(productRepository.save(newProduct).getId()));
        data.put("product_amount_id", Integer.toString(productAmountRepository.save(newProductAmount).getId()));

        adminServicePublisher.sendProductCreatedOrUpdatedMessage(data);
        adminServicePublisher.sendProductAmountCreatedOrUpdatedMessage(data);
    }

    public Product getProductData(Map<String, String> data){
        String name = data.get("name");
        String category = data.get("category");
        double price = Double.parseDouble(data.get("price"));
        double discount = Double.parseDouble(data.get("discount"));
        String barcode = data.get("barcode");
        String description = data.get("description");
        String imageURL = data.get("imageURL");

        return new Product(name, category, price, discount, barcode, description, imageURL);

    }

    public Market getSelectedMarket(Map<String, String> data) {
        int market_id = Integer.parseInt(data.get("market_id"));
        return marketRepository.findById(market_id).get();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/product")
    public void updateProduct(Map<String, String> data) throws Exception {

        Product updatedProduct = getProductData(data);
        updatedProduct.setId(Integer.parseInt(data.get("product_id")));

        Market selectedMarket = getSelectedMarket(data);

        ProductAmount updatedProductAmount = productAmountRepository.findProductAmountByMarket_IdAndProduct_Id(selectedMarket.getId(), updatedProduct.getId());
        updatedProductAmount.setAmount(Integer.parseInt(data.get("amount")));

        productRepository.save(updatedProduct);
        data.put("product_amount_id", Integer.toString(productAmountRepository.save(updatedProductAmount).getId()));

        adminServicePublisher.sendProductCreatedOrUpdatedMessage(data);
        adminServicePublisher.sendProductAmountCreatedOrUpdatedMessage(data);
    }

    /*@RequestMapping(method = RequestMethod.PUT, value = "/product/{name}/{category}/{amount}/{price}/{discount/{barcode}/{description}/{imageURL}/{product_id}")
    public void updateProduct(@PathVariable("name") String name, @PathVariable("category") String category, @PathVariable("amount") int amount, @PathVariable("price") double price, @PathVariable("discount") double discount, @PathVariable("barcode") String barcode, @PathVariable("description") String description, @PathVariable("imageURL") String imageURL, @PathVariable("product_id") int product_id) throws Exception {
        Product selectedProduct = productRepository.findById(product_id).get();
        selectedProduct.setName(name);
        selectedProduct.setCategory(category);
        selectedProduct.setPrice(price);
        selectedProduct.setDiscount(discount);
        selectedProduct.setBarcode(barcode);
        selectedProduct.setDescription(description);
        selectedProduct.setImageURL(imageURL);

        ProductAmountInMarket selectedProductAmountInMarket = productAmountRepository.findProductAmountInMarketByMarket_IdAndProduct_Id(activeParameters.getActiveMarketID(), product_id);
        selectedProductAmountInMarket.setAmount(amount);

        productRepository.save(selectedProduct);
        productAmountRepository.save(selectedProductAmountInMarket);

        adminServicePublisher.sendProductUpdatedMessage(selectedProduct);
    }*/

    @RequestMapping(method = RequestMethod.DELETE, value = "/product/{product_id}")
    public void deleteProduct(@PathVariable("product_id") int product_id) {
        productRepository.deleteById(product_id);
        productAmountRepository.deleteAllByProduct_Id(product_id);

        adminServicePublisher.sendProductDeletedMessage(product_id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/product/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
