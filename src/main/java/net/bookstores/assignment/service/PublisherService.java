package net.bookstores.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bookstores.assignment.dao.PublisherDao;
import net.bookstores.assignment.entities.Publisher;

@Service
public class PublisherService {
    @Autowired
    PublisherDao publisherDao;

    public List<Publisher> findAll() {
        return publisherDao.findAll();
    }

    public Optional<Publisher> findById(Integer id) {
        return publisherDao.findById(id);
    }

    public Publisher create(Publisher publisher) {
        if (publisherDao.existsByName(publisher.getName())) {
            throw new IllegalArgumentException(publisher.getName() + " đã tồn tại trong hệ thống");
        }
        return publisherDao.save(publisher);
    }

    public Publisher update(Publisher publisher) {
        if (publisherDao.existsByName(publisher.getName())) {
            throw new IllegalArgumentException(publisher.getName() + " đã tồn tại trong hệ thống");
        }
        return publisherDao.save(publisher);
    }

    public void deleteById(Integer id) {
        publisherDao.deleteById(id);
    }
}
