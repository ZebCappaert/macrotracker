package diet.macrotracker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import diet.macrotracker.jpa.LogEntry;
import diet.macrotracker.jpa.User;
import diet.macrotracker.service.AuthService;
import diet.macrotracker.service.LogService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;
    private final AuthService authService;

    public LogController(LogService logService, AuthService authService) {
        this.logService = logService;
        this.authService = authService;
    }

    // Haal logs op voor een specifieke datum (of vandaag als er geen datum wordt
    // meegegeven)
    @GetMapping
    public ResponseEntity<List<LogEntry>> getLogsByDate(@RequestParam(required = false) LocalDate date) {
        User currentUser = authService.getLoggedInUser();
        LocalDate targetDate = (date != null) ? date : LocalDate.now();

        List<LogEntry> logs = logService.getLogsForUserAndDate(currentUser, targetDate);
        return ResponseEntity.ok(logs);
    }

    // Voeg een nieuwe log-entry toe
    @PostMapping
    public ResponseEntity<LogEntry> addLog(@RequestBody AddLogRequest request) {
        User currentUser = authService.getLoggedInUser();
        LogEntry newLog = logService.addLog(currentUser, request.getFoodItemId(), request.getAmountInGrams());
        return ResponseEntity.ok(newLog);
    }
}

// DTO voor het inkomende request
class AddLogRequest {
    private Long foodItemId;
    private double amountInGrams;

    public Long getFoodItemId() {
        return foodItemId;
    }

    public void setFoodItemId(Long foodItemId) {
        this.foodItemId = foodItemId;
    }

    public double getAmountInGrams() {
        return amountInGrams;
    }

    public void setAmountInGrams(double amountInGrams) {
        this.amountInGrams = amountInGrams;
    }
}