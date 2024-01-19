package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.Cart;
import ba.ibu.edu.bemytech.core.model.Contact;
import ba.ibu.edu.bemytech.core.model.Product;
import ba.ibu.edu.bemytech.core.repository.CartRepository;
import ba.ibu.edu.bemytech.rest.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart addCart(Cart payload){
        return cartRepository.save(payload);
    }

    public List<Cart> getCartsByUsername(String username) {
        List<Cart> carts = cartRepository.findCartByUsername(username);

        if (carts.isEmpty()) {
            throw new ResourceNotFoundException("No carts found for the given username: " + username);
        }

        return carts;
    }


    public Cart updateCart(String id, Cart payload){
        Optional<Cart> cart = cartRepository.findById(id);
        if(cart.isEmpty())
            throw new ResourceNotFoundException("Unable to find entity with provided id");

        payload.setId(cart.get().getId());
        return cartRepository.save(payload);
    }

    public void deleteCart(String id){
        cartRepository.deleteById(id);
    }
}
