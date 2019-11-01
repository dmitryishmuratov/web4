package DAO;

import model.Car;
import model.DailyReport;
import model.DayReport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DailyReportDao {

    private Session session;

    public DailyReportDao(Session session) {
        this.session = session;
    }

    public List<DailyReport> getAllDailyReport() {
        Transaction transaction = session.beginTransaction();
        List<DailyReport> dailyReports = session.createQuery("FROM DailyReport").list();
        transaction.commit();
        session.close();
        return dailyReports;
    }


    public DailyReport getLastReport() {
        session.beginTransaction();
        Query query = session.createQuery("FROM DailyReport ORDER BY id DESC ");
        query.setMaxResults(1);
        DailyReport last = (DailyReport) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return last;
    }

    public void addSoldCar(Car car) {
        DayReport dayReport = DayReport.getInstance();
        dayReport.setEarnings(DayReport.getEarnings() + car.getPrice());
        dayReport.setSoldCars(DayReport.getSoldCars() + 1);
    }

    public void deleteAllReport() {
        session.beginTransaction();
        session.createQuery("DELETE FROM DailyReport").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void createNewDayReport() {
        DayReport dayReport = DayReport.getInstance();
        DailyReport dailyReport = new DailyReport(dayReport.getEarnings(), dayReport.getSoldCars());
        session.beginTransaction();
        session.save(dailyReport);
        session.getTransaction().commit();
        session.close();
    }
}
