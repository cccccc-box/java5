package net.bookstores.assignment.service;

import net.bookstores.assignment.entities.Discount;
import net.bookstores.assignment.dao.DiscountDao; // Đảm bảo import đúng package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private DiscountDao discountDao;

    // Lấy danh sách tất cả mã giảm giá
    public List<Discount> getAllDiscounts() {
        return discountDao.findAll();
    }

    // Tìm mã giảm giá theo ID
    public Optional<Discount> getDiscountById(Integer id) {
        return discountDao.findById(id);
    }

    // Thêm mới mã giảm giá
    public Discount createDiscount(Discount discount) {
        return discountDao.save(discount);
    }

    // Cập nhật mã giảm giá
    public Discount updateDiscount(Integer id, Discount newDiscount) {
        return discountDao.findById(id).map(d -> {
            d.setCode(newDiscount.getCode());
            d.setDescription(newDiscount.getDescription());
            d.setStartDate(newDiscount.getStartDate());
            d.setEndDate(newDiscount.getEndDate());
            d.setQuantity(newDiscount.getQuantity());
            d.setIsActive(newDiscount.getIsActive());
            return discountDao.save(d);
        }).orElseThrow(() -> new RuntimeException("Mã giảm giá không tồn tại!"));
    }
    
    // Xóa mã giảm giá
    public void deleteDiscount(Integer id) {
        discountDao.deleteById(id);
    }
}
