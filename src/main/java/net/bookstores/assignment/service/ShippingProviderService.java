package net.bookstores.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.ShippingProviderDao;
import net.bookstores.assignment.entities.ShippingProvider;

@Service
public class ShippingProviderService {
    @Autowired
    ShippingProviderDao shippingProviderDao;

    public void create(String name, String hotline, Float shipingFee, Boolean isActive) {
        ShippingProvider shippingProvider = ShippingProvider.builder().name(name).hotline(hotline)
                .shippingFee(shipingFee).isActive(isActive).build();
        shippingProviderDao.save(shippingProvider);
    }

    public List<ShippingProvider> findAll() {
        return shippingProviderDao.findAll();
    }

    public void update(Integer id, String name, String hotline, Float shipingFee, Boolean isActive) {
        ShippingProvider shippingProvider = ShippingProvider.builder().providerId(id).name(name).hotline(hotline)
                .shippingFee(shipingFee).isActive(isActive).build();
        shippingProviderDao.save(shippingProvider);
    }

    public void deleteById(Integer id) {
        shippingProviderDao.deleteById(id);
    }

    public Optional<ShippingProvider> findById(Integer id) {
        return shippingProviderDao.findById(id);
    }
}
