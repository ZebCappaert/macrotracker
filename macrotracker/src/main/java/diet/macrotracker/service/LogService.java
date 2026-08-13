package diet.macrotracker.service;

import org.springframework.stereotype.Service;

import diet.macrotracker.jpa.FoodItem;
import diet.macrotracker.jpa.LogEntry;
import diet.macrotracker.jpa.User;
import diet.macrotracker.repository.FoodItemRepository;
import diet.macrotracker.repository.LogEntryRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogService {

    private final LogEntryRepository logEntryRepository;
    private final FoodItemRepository foodItemRepository;

    public LogService(LogEntryRepository logEntryRepository, FoodItemRepository foodItemRepository) {
        this.logEntryRepository = logEntryRepository;
        this.foodItemRepository = foodItemRepository;
    }

    public LogEntry addLog(User user, Long foodId, double amount) {
        FoodItem food = foodItemRepository.findById(foodId)
                .orElseThrow(() -> new RuntimeException("Product niet gevonden"));

        LogEntry log = new LogEntry(user, food, amount, LocalDate.now());
        return logEntryRepository.save(log);
    }

    public List<LogEntry> getLogsForUserToday(User user) {
        return logEntryRepository.findByUserAndEntryDate(user, LocalDate.now());
    }

    public List<LogEntry> getLogsForUserAndDate(User user, LocalDate date) {
        return logEntryRepository.findByUserAndEntryDate(user, date);
    }
}