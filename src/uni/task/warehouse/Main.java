package uni.task.warehouse;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int numberOfMaterials = 4;

        String code;
        String name;
        int quantity;
        int lastSellDays;
        String stockGroup;
        String dateOfEntrance;
        String storagePosition;
        float humidityPercentage;

        Warehouse warehouse = new Warehouse();
        Scanner scanner = new Scanner(System.in);

        for(int i = 0;i < numberOfMaterials;i++){
            System.out.println("-------- MATERIAL Index "+i+" ----------");

            try{
                System.out.println("Enter material code (String): ");
                code = scanner.next();

                System.out.println("Enter material name (String): ");
                name = scanner.next();

                System.out.println("Enter material quantity (Integer): ");
                quantity = scanner.nextInt();

                System.out.println("Enter material last sell days (Integer): ");
                lastSellDays = scanner.nextInt();

                System.out.println("Enter material stock group (String): ");
                stockGroup = scanner.next();

                System.out.println("Enter material date of entrance (String dd/mm/yy): ");
                dateOfEntrance = scanner.next();

                System.out.println("Enter material storage position (String): ");
                storagePosition = scanner.next();

                System.out.println("Enter material humidity (Float): ");
                humidityPercentage = scanner.nextFloat();

                Material material = new Material(
                        code,
                        name,
                        quantity,
                        lastSellDays,
                        stockGroup,
                        dateOfEntrance,
                        storagePosition,
                        humidityPercentage
                );

                warehouse.addMaterials(material);

            }catch(Exception e){
                System.out.println("Error... invalid data");
            }
        }

        System.out.println("-------Print all Materials-------");
        warehouse.printAllMaterials();

        System.out.println("-------Print all Special Materials-------");
        warehouse.printAllSpecialMaterials();

        System.out.println("-------Print materials with code-------");
        warehouse.printSpecialMaterials("1009");

    }
}
