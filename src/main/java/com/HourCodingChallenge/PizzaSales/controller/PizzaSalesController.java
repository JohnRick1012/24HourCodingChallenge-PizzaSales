package com.HourCodingChallenge.PizzaSales.controller;

import com.HourCodingChallenge.PizzaSales.model.PizzaTypes;
import com.HourCodingChallenge.PizzaSales.service.ImportSalesReportService;
import com.HourCodingChallenge.PizzaSales.service.SalesReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sales")
public class PizzaSalesController {

    @Autowired
    private ImportSalesReportService importSalesReportService;

    private final SalesReportService salesReportService;

    public PizzaSalesController(SalesReportService salesReportService) {
        this.salesReportService = salesReportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importSalesReportFiles() {
        try {
            importSalesReportService.importSalesReportFiles();
            return ResponseEntity.ok("Pizza sales data imported successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import sales data. ERROR: " + e.getMessage());
        }
    }

    @GetMapping("/top-sales")
    public ResponseEntity<Double> getTotalSales() {
        double totalSales = salesReportService.calculateTotalSales();
        return ResponseEntity.ok(totalSales);
    }

    @GetMapping("/monthly-sales")
    public ResponseEntity<Map<String, Double>> getMonthlySales() {
        Map<String, Double> monthlySales = salesReportService.calculateMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }

    @GetMapping("/popular-pizza-types")
    public ResponseEntity<Map<String, Integer>> getPopularPizzaTypes() {
        Map<String, Integer> popularPizzaTypes = salesReportService.getPopularPizzaType();
        return ResponseEntity.ok(popularPizzaTypes);
    }
}
