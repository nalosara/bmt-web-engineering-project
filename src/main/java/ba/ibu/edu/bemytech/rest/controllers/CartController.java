package ba.ibu.edu.bemytech.rest.controllers;

import ba.ibu.edu.bemytech.core.model.Cart;
import ba.ibu.edu.bemytech.core.service.CartService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@SecurityRequirement(name = "JWT Security")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    @PreAuthorize("hasAuthority('MEMBER, ADMIN')")
    public ResponseEntity<List<Cart>> getAllCarts() {
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/add-cart")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.addCart(cart));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/cartByUsername/{username}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<List<Cart>> getCartsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(cartService.getCartsByUsername(username));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Cart> updateCart(@PathVariable String id, @RequestBody Cart cart) {
        return ResponseEntity.ok(cartService.updateCart(id, cart));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    @PreAuthorize("hasAuthority('MEMBER')")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
