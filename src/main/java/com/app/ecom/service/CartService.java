package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.model.CartItem;
import com.app.ecom.model.Product;
import com.app.ecom.model.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class CartService {
    public final UserRepository userRepository;
    public final ProductRepository productRepository;
    public final CartItemRepository cartItemRepository;
    public boolean addToCart(String userId, CartItemRequest cartItemRequest){
        // checking whether the user is valid
        Optional<User> userOpt =  userRepository.findById(Long.valueOf(userId));
        if(userOpt.isEmpty())
            return false;

        User user = userOpt.get();
        // Checking whether product is valid
        Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProductId());
        if(productOpt.isEmpty())
            return false;
        Product product = productOpt.get();

        // Check whether the requested quantity is available
        if(product.getStockQuantity() < cartItemRequest.getQuantity())
            return false;

        //Check whether the item is already picked by the user in the cart
        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(user,product);
        if(existingCartItem != null){
            // update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);
        }else{
            // create a cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setUser(user);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteCartItem(String userId, String productId) {
        Optional<User> userOpt =  userRepository.findById(Long.valueOf(userId));
        Optional<Product> productOpt = productRepository.findById(Long.valueOf(productId));
        if(userOpt.isPresent() && productOpt.isPresent()){
            cartItemRepository.deleteByUserAndProduct(userOpt.get(),productOpt.get());
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
       /* Optional<User> userOpt =  userRepository.findById(Long.valueOf(userId));
        if(userOpt.isPresent())
            return cartItemRepository.findByUser(userOpt.get()) ;
        return new ArrayList<CartItem>();*/


        return userRepository.findById(Long.valueOf(userId))
                .map(cartItemRepository::findByUser)
                .orElseGet(List::of);


    }
}
