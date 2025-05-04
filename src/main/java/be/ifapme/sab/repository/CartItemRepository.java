package be.ifapme.sab.repository;

import be.ifapme.sab.model.entities.Cart;
import be.ifapme.sab.model.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> getAllCartItemsByCartId(Long cart_id);
}
