package com.example.level6;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Cart {
    private final Map<MenuItem, Integer> items = new HashMap<>();
    private int index;

    public void add(Menu menu, int index) {
        // Key = 아이템, value = 수량
        MenuItem item = menu.getMenuItems().get(index - 1);
        // 이미 장바구니에 있다면 수량만 증가
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    public List<MenuItem> getItems() {
        return new ArrayList<>(items.keySet());
    }

    public void showAddedItem() {
        for (MenuItem item : items.keySet()) {
            System.out.println(item.getName() + "이(가) 장바구니에 추가되었습니다.");
        }
    }

    public void showAllCartItems() {
        for (MenuItem item : items.keySet()) {
            System.out.printf("%s | W %.1f | %s ---- %d개%n",
                    item.getName(),
                    item.getPrice(),
                    item.getDescription(),
                    items.get(item));
        }
    }

     public void order() {
         System.out.println("[ Orders ]");
         showAllCartItems();
         System.out.println("[ Total ]");
         System.out.println("W " + getTotalPrice());
         clearCart();
     }

    private double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items.keySet()) {
            total += item.getPrice() * items.get(item);
        }
        return total;
    }

    private void clearCart() {
        items.clear();
    }
}
