package com.example.secureweb.controller;

import com.example.secureweb.model.Producto;
import com.example.secureweb.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tienda")
    public String tienda(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "tienda";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "admin/dashboard";
    }

    @PostMapping("/admin/producto/agregar")
    public String agregarProducto(@ModelAttribute Producto producto) {
        productoService.agregarProducto(producto);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/producto/editar")
    public String editarProducto(@ModelAttribute Producto producto) {
        productoService.actualizarProducto(producto);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/admin/dashboard";
    }
}