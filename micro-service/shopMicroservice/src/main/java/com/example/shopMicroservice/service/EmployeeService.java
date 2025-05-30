package com.example.shopMicroservice.service;

import com.example.shopMicroservice.domain.dto.EmployeeDTO;
import com.example.shopMicroservice.domain.entity.Employee;
import com.example.shopMicroservice.domain.entity.Shop;
import com.example.shopMicroservice.domain.mapper.ShopMapper;
import com.example.shopMicroservice.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ShopMapper shopMapper;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(int id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id: " + id);
        }
        return employeeOptional.get();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public void deleteById(int employeeId){
        employeeRepository.deleteById(employeeId);
    }
    public void deleteByUserId(int userId){
        employeeRepository.deleteByUserId(userId);
    }

    public EmployeeDTO findByUserId(int userId){
        Optional<Employee> employeeOptional = employeeRepository.findByUserId(userId);
        if(employeeOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with user_id: " + userId);
        }
        Employee employee = employeeOptional.get();
        EmployeeDTO employeeDTO= EmployeeDTO.builder()
                .id(employee.getId())
                .user_id(employee.getUser_id())
                .shop(shopMapper.shopEntityToDto(employee.getShop()))
                .build();
        System.out.println(employeeDTO);
        return employeeDTO;
    }
}
