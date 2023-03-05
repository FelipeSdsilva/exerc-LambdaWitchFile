package application;

import entities.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        double sum = 0;

        System.out.print("Enter with a file path: ");
        String strPath = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {

            String line = br.readLine();
            List<Product> products = new ArrayList<>();

            while (line != null) {
                String[] vec = line.split(",");
                String name = vec[0];
                Double price = Double.parseDouble(vec[1]);

                products.add(new Product(name, price));

                line = br.readLine();
            }


            sum = products.stream().map(value -> value.getPrice()).reduce(0.0, (x, y) -> x + y);

            double avg = sum / products.size();

            List<String> names = products.stream().filter(product -> product.getPrice() < avg)
                    .map(product -> product.getName())
                    .sorted((o1, o2) -> -o1.toUpperCase().compareTo(o2.toUpperCase())).toList();

            System.out.println("Average price products: " + String.format("%.2f", avg));

            names.forEach(System.out::println);

        } catch (IOException e) {

        }
    }
}