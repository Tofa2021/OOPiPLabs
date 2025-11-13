package org.example.lab5.patterns;

import org.example.lab5.model.Computer;

import java.util.List;

public interface SearchStrategy {
    List<Computer> search(List<Computer> computers, String query);
}
