package com.example.level6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<Menu> menus;
    private int selectedMenu, selectedMenuItem, cartSelection, selectedOrderMenu;
    private final Scanner sc = new Scanner(System.in);
    private Cart cart = new Cart();
    private boolean isRunning = true;

    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {
        while (isRunning) {
            kioskInit();
            if (isExitCondition(selectedMenu)) {
                System.out.println("프로그램을 종료합니다.");
                isRunning = false;
            } else {
                cartInit();
            }
        }
    }

    private void cartInit() {
        settingCart();
        handleCartWithItems();
    }

    private void settingCart() {
        while (true) {
            try {
                displaySelectedOrderMenu(selectedMenu, selectedMenuItem);
                cartSelection = getUserInput(sc, "위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인\t2. 취소\n");
                if (cartSelection == 2) return;
                cart.add(menus.get(selectedMenu-1), selectedMenuItem);
                cart.showAddedItem();
                cart.showAllCartItems();
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleCartWithItems() {
        System.out.println("아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.");
        showMenu();
        showOrderMenu();
        getOrder();
    }

    private void getOrder() {
        while (true) {
            try {
                selectedOrderMenu = getUserInput(sc, "");

                if (isExitCondition(selectedOrderMenu)) {
                    handleExit();
                    return;
                }

                if (isValidMainMenu(selectedOrderMenu)) {
                    handleMainMenuSelection(selectedOrderMenu);
                    break;
                }

                if (selectedOrderMenu == 4) {
                    // Order
                    cart.order();
                } else if (selectedOrderMenu == 5) {
                    // Cancel
                    System.out.println("주문을 취소하고 메인 메뉴로 돌아갑니다.");
                    return;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleMainMenuSelection(int option) {
        selectedMenu = option;
        settingMenuItem();
        cartInit();
    }

    private boolean isExitCondition(int option) {
        return option == 0;
    }

    private void handleExit() {
        selectedMenu = 0;
        isRunning = false;
        System.out.println("프로그램을 종료합니다.");
    }

    private void kioskInit() {
        settingMenu();
        if (isExitCondition(selectedMenu)) return;
        settingMenuItem();
    }

    private void settingMenuItem() {
        showMenuItems(selectedMenu);
        getMenuItem();
        displaySelectedMenuItems(selectedMenu, selectedMenuItem);
    }

    private void getMenuItem() {
        while (true) {
            try {
                selectedMenuItem = getUserInput(sc, "메뉴를 선택하세요: ");
                if (isExitCondition(selectedMenuItem)) return;
                else if (!isValidMenuItem(selectedMenu, selectedMenuItem)) {
                    System.out.println("올바른 메뉴 아이템 번호를 입력해주세요.");
                    return;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void settingMenu() {
        showMenu();
        getMenu();
    }

    private void getMenu() {
        while (true) {
            try {
                selectedMenu = getUserInput(sc, "메인 메뉴를 선택하세요: ");
                if (isExitCondition(selectedMenu)) return;
                if (!isValidMainMenu(selectedMenu)) {
                    System.out.println("올바른 메뉴 번호를 입력해주세요.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 메시지 출력 후 사용자 입력이 숫자인지 검사
    private int getUserInput(Scanner sc, String message) {
        System.out.print(message);
        String input = sc.nextLine();
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
        return Integer.parseInt(input);
    }

    private boolean isValidMenuItem(int menuIndex, int itemIndex) {
        return itemIndex > 0 && itemIndex <= menus.get(menuIndex - 1).getMenuItems().size();
    }

    private boolean isValidMainMenu(int input) {
        return input > 0 && input <= menus.size();
    }

    private void showMenu() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < menus.size(); i++) {
            System.out.printf("%d. %s", i + 1, menus.get(i).getCategory());
            System.out.println();
        }
        System.out.println("0. 종료      | 종료");
    }

    private void showOrderMenu() {
        System.out.println("\n[ ORDER MENU ]");
        System.out.println("4. Orders       | 장바구니를 확인 후 주문합니다.");
        System.out.println("5. Cancel       | 진행중인 주문을 취소합니다.");
    }

    private void showMenuItems(int input) {
        System.out.printf("[ %s MENU ]", menus.get(input - 1).getCategory());
        System.out.println();
        int size = menus.get(input - 1).getMenuItems().size();
        for (int i = 0; i < size; i++) {
            MenuItem menuItem = menus.get(input - 1).getMenuItems().get(i);
            System.out.printf("%d. %s   | W %.1f | %s%n", i + 1,
                    menuItem.getName(), menuItem.getPrice(), menuItem.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    private void displaySelectedMenuItems(int i1, int i2) {
        MenuItem menuItem = menus.get(i1 - 1).getMenuItems().get(i2 - 1);
        System.out.printf("선택한 메뉴: %s | W %.1f | %s%n",
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getDescription());
    }

    private void displaySelectedOrderMenu(int i1, int i2) {
        MenuItem menuItem = menus.get(i1 - 1).getMenuItems().get(i2 - 1);
        System.out.printf("\"%s | W %.1f | %s\"%n",
                menuItem.getName(),
                menuItem.getPrice(),
                menuItem.getDescription());
    }
}
