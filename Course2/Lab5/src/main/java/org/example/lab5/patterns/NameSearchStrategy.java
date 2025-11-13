package org.example.lab5.patterns;

import org.example.lab5.model.Computer;

import java.util.List;
import java.util.stream.Collectors;

public class NameSearchStrategy implements SearchStrategy {
    @Override
    public List<Computer> search(List<Computer> computers, String query) {
        return computers.stream()
                .filter(computer -> computer.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
