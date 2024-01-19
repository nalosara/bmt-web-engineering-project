package ba.ibu.edu.bemytech.core.service;

import ba.ibu.edu.bemytech.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.bemytech.core.model.CartItem;
import ba.ibu.edu.bemytech.core.repository.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItems(){
        return cartItemRepository.findAll();
    }

    public CartItem addCartItem(CartItem payload){
        return cartItemRepository.save(payload);
    }


    public CartItem updateCartItem(String id, CartItem payload){
        Optional<CartItem> cartItem = cartItemRepository.findById(id);
        if(cartItem.isEmpty())
            throw new ResourceNotFoundException("Unable to find entity with provided id");

        payload.setId(cartItem.get().getId());
        return cartItemRepository.save(payload);
    }

    public void deleteCartItem(String id){
        cartItemRepository.deleteById(id);
    }
}
