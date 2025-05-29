package com.example.shopMicroservice.controller;

import com.example.shopMicroservice.domain.dto.EmployeeDTO;
import com.example.shopMicroservice.domain.dto.ShopDTO;
import com.example.shopMicroservice.domain.entity.Employee;
import com.example.shopMicroservice.service.EmployeeService;
import com.example.shopMicroservice.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/all")
    public List<ShopDTO> getAllShops() {
        return shopService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getShopById(@PathVariable int id) {
        return ResponseEntity.ok(shopService.findById(id));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @GetMapping("/employee/user/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByUserId(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.findByUserId(id));
    }

    @PostMapping("/employee")
    public void save(@RequestBody Employee employee){
        employeeService.save(employee);
    }

}
