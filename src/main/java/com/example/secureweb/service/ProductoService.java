package com.example.secureweb.service;

import com.example.secureweb.model.Producto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductoService {
    private final ConcurrentHashMap<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public ProductoService() {
        // Agregar algunos productos de ejemplo
        agregarProducto(new Producto(counter.incrementAndGet(), "Sartén Antiadherente", 
            "Sartén de 28cm con recubrimiento antiadherente", 29.99, "sarten.jpg"));
        agregarProducto(new Producto(counter.incrementAndGet(), "Olla a Presión", 
            "Olla a presión de acero inoxidable 6L", 49.99, "olla.jpg"));
    }

    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos.values());
    }

    public Producto obtenerPorId(Long id) {
        return productos.get(id);
    }

    public Producto agregarProducto(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(counter.incrementAndGet());
        }
        productos.put(producto.getId(), producto);
        return producto;
    }

    public void eliminarProducto(Long id) {
        productos.remove(id);
    }

    public Producto actualizarProducto(Producto producto) {
        productos.put(producto.getId(), producto);
        return producto;
    }
}