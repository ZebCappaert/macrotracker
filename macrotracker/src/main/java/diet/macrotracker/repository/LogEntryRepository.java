package diet.macrotracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import diet.macrotracker.jpa.LogEntry;
import diet.macrotracker.jpa.User;

import java.time.LocalDate;
import java.util.List;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {

    List<LogEntry> findByUserAndEntryDate(User user, LocalDate entryDate);
}
