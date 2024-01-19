package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.CartItem;
import ba.ibu.edu.bemytech.core.service.CartItemService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-items")
@SecurityRequirement(name = "JWT Security")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('MEMBER, ADMIN')")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-cart-item")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItem cartItem) {
        return ResponseEntity.ok(cartItemService.addCartItem(cartItem));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable String id, @RequestBody CartItem cartItem) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, cartItem));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Void> deleteCartItem(@PathVariable String id) {
        cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
