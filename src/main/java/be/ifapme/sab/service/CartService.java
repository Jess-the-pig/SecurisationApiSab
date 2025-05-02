package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.*;
import be.ifapme.sab.model.entities.*;
import be.ifapme.sab.repository.BookRepository;
import be.ifapme.sab.repository.CartItemRepository;
import be.ifapme.sab.repository.CartRepository;
import be.ifapme.sab.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private CartRepository cartRepository;
    private PersonRepository personRepository;
    private CartItemRepository cartItemRepository;
    private BookRepository bookRepository;

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<CartItemResponse> seeAllItems(CartRequest cartRequest){
        Long cartId = cartRequest.getCart_id();

        // Récupérer les items du panier
        List<CartItem> cartItems = cartItemRepository.getAllCartItemsByCartId(cartId);

        // Mapper les CartItem -> CartItemResponse (à adapter selon ta classe CartItemResponse)
        return cartItems.stream()
                .map(item -> new CartItemResponse(
                        item.getItem_id(),
                        item.getItem_quantity()
                ))
                .toList();

    }


    public CartResponse createCart(UserDetails userDetails) {
        Optional<Person> user = personRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            Cart cart = new Cart();
            cart.setUser(user.get());
            cart.setCart_payed(Boolean.FALSE);
            cartRepository.save(cart);
            return new CartResponse(cart.getId(),cart.getCart_payed());
        }
        return null;
    }

    public void storeItem(BookRequest bookRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Person user = personRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        Book book = bookRepository.findByIsbn(bookRequest.getIsbn())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart_id(cart.getId());
        cartItem.setItem_id(book.getId());
        cartItem.setItem_quantity(bookRequest.getQuantity());

        cartItemRepository.save(cartItem);
    }

    public ResponseEntity<CartItemResponse> seeIteminCart(Long itemid, CustomUserDetails userDetails){
        Person user = userDetails.getPerson();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        CartItem item = cartItemRepository.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (!item.getItem_id().equals(cart.getId())) {
            throw new AccessDeniedException("This item does not belong to your cart");
        }

        // 4. Mapper et retourner
        CartItemResponse response = new CartItemResponse(
                item.getId(),
                item.getItem_quantity()
        );

        return ResponseEntity.ok(response);
    }

    public void deleteItemFromCart(Long itemid, CustomUserDetails userDetails){
        Person user = userDetails.getPerson();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        CartItem item = cartItemRepository.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (!item.getItem_id().equals(cart.getId())) {
            throw new AccessDeniedException("This item does not belong to your cart");
        }

        cartItemRepository.delete(item);


    }

}
