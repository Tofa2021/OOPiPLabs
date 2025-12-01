package org.example.lab5.patterns;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(String message) {
        for (Listener listener : listeners) {
            listener.update(message);
        }
    }
}
