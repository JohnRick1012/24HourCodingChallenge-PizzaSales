package com.HourCodingChallenge.PizzaSales.service;

import com.HourCodingChallenge.PizzaSales.model.OrderDetails;
import com.HourCodingChallenge.PizzaSales.model.Orders;
import com.HourCodingChallenge.PizzaSales.model.PizzaTypes;
import com.HourCodingChallenge.PizzaSales.model.Pizzas;
import com.HourCodingChallenge.PizzaSales.repository.OrderDetailsRepository;
import com.HourCodingChallenge.PizzaSales.repository.OrdersRepository;
import com.HourCodingChallenge.PizzaSales.repository.PizzaTypesRepository;
import com.HourCodingChallenge.PizzaSales.repository.PizzasRepository;
import jakarta.transaction.Transactional;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImportSalesReportService {

    private static final Logger logger = LogManager.getLogger(ImportSalesReportService.class);

    @Autowired
    private PizzasRepository pizzasRepository;

    @Autowired
    private PizzaTypesRepository pizzaTypesRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Transactional
    public void importSalesReportFiles() {

        try {

            importPizzasFile();
            importPizzaTypeFile();
            importOrdersFile();
            importOrderDetailsFile();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void importPizzasFile() throws IOException {

            try {
                BufferedReader reader = new BufferedReader(new FileReader(""));
                String line;
                List<Pizzas> pizzasList = new ArrayList<>();

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length >= 4) {
                        Long pizzaId = Long.parseLong(data[0].trim());
                        String size = data[1].trim();
                        double price = Double.parseDouble(data[2].trim());

                        List<PizzaTypes> pizzaTypes = new ArrayList<>();
                        for (int pt = 3; pt < data.length; pt++) {
                            Long pizzaTypeId = Long.parseLong(data[pt].trim());
                            PizzaTypes pizzaType = pizzaTypesRepository.findById(pizzaTypeId).orElse(null);
                            if (pizzaType != null) {
                                pizzaTypes.add(pizzaType);
                            }
                        }

                        Pizzas pizzas = new Pizzas(pizzaId, size, price, pizzaTypes);
                        pizzasList.add(pizzas);
                    }
                }

                reader.close();
                pizzasRepository.saveAll(pizzasList);
            } catch (FileNotFoundException fe) {
                logger.error("File Not Found: " + fe);
            } catch (IOException ie) {
                logger.error("There is an exception occurred: " + ie);
            }
    }

    private void importPizzaTypeFile() throws IOException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            String line;
            List<PizzaTypes> pizzaTypesList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                PizzaTypes pizzaTypes = new PizzaTypes();
                pizzaTypes.setPizzaTypeId(Long.parseLong(data[0]));
                pizzaTypes.setName(data[1]);
                pizzaTypes.setCategory(data[2]);
                pizzaTypes.setIngredients(data[3]);

                pizzaTypesList.add(pizzaTypes);
            }

            reader.close();

            pizzaTypesRepository.saveAll(pizzaTypesList);
        } catch (FileNotFoundException fe) {
            logger.error("File Not Found: " + fe);
        } catch (IOException ie) {
            logger.error("There is an exception occurred: " + ie);
        }

    }

    private void importOrdersFile() throws IOException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            String line;
            List<Orders> ordersList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Orders orders = new Orders();
                orders.setOrderId(Long.parseLong(data[0]));
                orders.setDate(LocalDate.parse(data[1]));
                orders.setTime(LocalTime.parse(data[2]));

                ordersList.add(orders);
            }

            reader.close();

            ordersRepository.saveAll(ordersList);

        } catch (FileNotFoundException fe) {
            logger.error("File Not Found: " + fe);
        } catch (IOException ie) {
            logger.error("There is an exception occurred: " + ie);
        }

    }

    private void importOrderDetailsFile() throws IOException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(""));
            String line;
            List<OrderDetails> orderDetailsList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) {
                    Long orderDetailsId = Long.parseLong(data[0].trim());
                    int quantity = Integer.parseInt(data[1].trim());

                    List<Orders> ordersList = new ArrayList<>();
                    for(int o = 2; o < data.length; o++) {
                        Long ordersId = Long.parseLong(data[o].trim());
                        Orders order = ordersRepository.findById(ordersId).orElse(null);
                        if (order != null) {
                            ordersList.add(order);
                        }
                    }

                    List<Pizzas> pizzasList = new ArrayList<>();
                    for (int pl = 3; pl < data.length; pl++) {
                        Long pizzasId = Long.parseLong(data[pl].trim());
                        Pizzas pizzas = pizzasRepository.findById(pizzasId).orElse(null);
                        if(pizzas != null) {
                            pizzasList.add(pizzas);
                        }
                    }

                    OrderDetails orderDetails = new OrderDetails(orderDetailsId, quantity, ordersList, pizzasList);
                    orderDetailsList.add(orderDetails);
                }
            }
            reader.close();

            orderDetailsRepository.saveAll(orderDetailsList);

        } catch (FileNotFoundException fe) {
            logger.error("File Not Found: " + fe);
        } catch (IOException ie) {
            logger.error("There is an exception occurred: " + ie);
        }

    }


}
