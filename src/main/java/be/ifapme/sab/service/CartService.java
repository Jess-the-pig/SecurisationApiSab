package be.ifapme.sab.service;

import be.ifapme.sab.api.DTO.*;
import be.ifapme.sab.model.entities.*;
import be.ifapme.sab.model.entities.enums.CartStatus;
import be.ifapme.sab.model.entities.enums.PaymentStatus;
import be.ifapme.sab.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private PaymentService paymentService;
    private OrderService orderService;
    private OrderRepository orderRepository;

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
                        item.getCart(),
                        item.getBook(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

    }


    public CartResponse createCart(UserDetails userDetails) {
        Optional<Person> user = personRepository.findByUsername(userDetails.getUsername());
        if (user.isPresent()) {
            Cart cart = new Cart();
            cart.setUser(user.get());
            cart.setStatus(CartStatus.PAID);
            cartRepository.save(cart);
            return new CartResponse(cart.getId(),cart.getStatus());
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
        cartItem.setCart(cart);
        cartItem.setBook(book);
        cartItem.setQuantity(bookRequest.getQuantity());

        cartItemRepository.save(cartItem);
    }

    public ResponseEntity<CartItemResponse> seeIteminCart(Long itemid, CustomUserDetails userDetails){
        Person user = userDetails.getPerson();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        CartItem item = cartItemRepository.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (!item.equals(cart)) {
            throw new AccessDeniedException("This item does not belong to your cart");
        }

        // 4. Mapper et retourner
        CartItemResponse response = new CartItemResponse(
                item.getCart(),
                item.getBook(),
                item.getQuantity(),
                item.getPrice()
        );

        return ResponseEntity.ok(response);
    }

    public void deleteItemFromCart(Long itemid, CustomUserDetails userDetails){
        Person user = userDetails.getPerson();

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found for user"));

        CartItem item = cartItemRepository.findById(itemid)
                .orElseThrow(() -> new EntityNotFoundException("Item not found"));

        if (!item.equals(cart)) {
            throw new AccessDeniedException("This item does not belong to your cart");
        }

        cartItemRepository.delete(item);


    }
    @Transactional
    public ResponseEntity<?> processPayementandMakeOrder(Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Panier non trouvé"));

        // Étape 2 : Traiter le paiement
        Payment payment = paymentService.processPayment(cart);

        if (payment.getStatus() != PaymentStatus.SUCCESS) {
            throw new RuntimeException("Le paiement a échoué.");
        }


        // Convertir le panier en commande
        Order order = orderService.convertCartToOrder(cart,payment);

        // Mettre à jour le statut du panier et enregistrer la commande
        cart.setStatus(CartStatus.PAID);
        cartRepository.save(cart);
        orderService.save(order);

        return ResponseEntity.ok(order);

    }

}
