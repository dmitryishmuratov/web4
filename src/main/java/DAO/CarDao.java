package DAO;

import model.Car;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class CarDao {

    private Session session;

    public CarDao(Session session) {
        this.session = session;
    }

    public Car getCarByParameter(String brand, String model, String licensePlate) {
        session.beginTransaction();
        Query query = session.createQuery("FROM Car C WHERE  C.brand = :brand and C.model = :model and C.licensePlate = :licensePlate");
        query.setParameter("brand", brand);
        query.setParameter("model", model);
        query.setParameter("licensePlate", licensePlate);
        List<Car> list = query.list();
        session.getTransaction().commit();
        session.close();
        return list.get(0);
    }

    public List<Car> getCarByBrand(String brand) {
        session.beginTransaction();
        Query query = session.createQuery("FROM Car C WHERE C.brand = 'brand'");
        List list = query.list();
        session.getTransaction().commit();
        session.close();
        return list;
    }

    public void addCar(Car car) {
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCar(Car car) {
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAllCars() {
        session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getAllCars() {
        session.beginTransaction();
        List<Car> list = new ArrayList<>(session.createQuery("FROM Car").list());
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
