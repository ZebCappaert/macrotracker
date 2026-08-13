package diet.macrotracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import diet.macrotracker.jpa.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByNameContainingIgnoreCase(String name);
}