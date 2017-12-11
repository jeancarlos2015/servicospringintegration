/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@RestController(value = "/")
public class TestController {
 
    @RequestMapping
    public String get() {
        return ("Barramento carregado!");
    }
}
