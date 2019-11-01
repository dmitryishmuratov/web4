package model;

public class DayReport {
    private static DayReport dayReport;

    private static Long earnings;

    private static Long soldCars ;

    private DayReport(Long earnings, Long soldCars) {
        this.earnings = earnings;
        this.soldCars = soldCars;
    }

    public DayReport() {
    }

    public static DayReport getInstance() {
        if(earnings == null || soldCars == null) {
           reset();
        }
        return dayReport;
    }

    public static Long getEarnings() {
        return earnings;
    }

    public static void setEarnings(Long earnings) {
        DayReport.earnings = earnings;
    }

    public static Long getSoldCars() {
        return soldCars;
    }

    public static void setSoldCars(Long soldCars) {
        DayReport.soldCars = soldCars;
    }

    public static void reset() {
        earnings = 0L;
        soldCars = 0L;
    }

    @Override
    public String toString() {
        return "DayReport{}";
    }
}
