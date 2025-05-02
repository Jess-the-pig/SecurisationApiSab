package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> getAllCartItemsByCartId(Long cart_id);
}
