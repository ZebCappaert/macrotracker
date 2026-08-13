package diet.macrotracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import diet.macrotracker.jpa.FoodItem;
import diet.macrotracker.repository.FoodItemRepository;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodItemRepository foodItemRepository;

    public FoodController(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItems() {
        return ResponseEntity.ok(foodItemRepository.findAll());
    }

    // Nieuw: Product toevoegen via API
    @PostMapping
    public ResponseEntity<FoodItem> createFoodItem(@RequestBody FoodItem foodItem) {
        FoodItem savedFood = foodItemRepository.save(foodItem);
        return ResponseEntity.ok(savedFood);
    }
}