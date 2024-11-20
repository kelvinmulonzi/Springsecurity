package com.example.demo.Controllers;
//
import com.example.demo.Entity.ProductEntity;
import com.example.demo.Service.UService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
public class Controler {

    @Autowired
    UService uService;

    @GetMapping("/api/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    public ProductEntity getItem(@RequestParam int id) {
        return uService.getProduct(id);
    }

    @PutMapping("/api/items")
    public ProductEntity updateItem(@RequestBody ProductEntity item) {
        return uService.updateProduct(item);
    }

    @GetMapping("/api")
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    public List<ProductEntity> getAllItems() {
        return uService.getAllProducts();
    }

    @PostMapping("/api/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductEntity addItem(@RequestBody ProductEntity item) {
        return uService.saveProduct(item);
    }

    @DeleteMapping("/api/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductEntity deleteItem(@RequestBody ProductEntity item) {
        return uService.deleteProduct(item);
    }
}



//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//@RestController
//@RequestMapping("api/v1/employees")
//public class Controler {
//
//    @GetMapping
//    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//    public String getAllEmployees(){
//        return "You Received All Employees List";
//    }
//
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String saveEmployees(){
//        return "You saved a Employee";
//    }
//
//    @PutMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    public String updateEmployees(){
//        return "You updated a Employee";
//    }
//
//}