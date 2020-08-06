package rainfalldata;

import java.time.LocalDate;

/**
 * Represents information about the amount of rainfall at a certain location on a certain date.
 */
public class DataPoint {

    /**
     * The date on which this data was captured.
     */
    private LocalDate date;

    /**
     * The amount of rainfall recorded.
     */
    private double rainfallMillimeters;

    /**
     * The location where this data was captured.
     */
    private String location;

    /**
     * Creates a new data point.
     *
     * This constructor should be used from within RainfallProcessor's createDataPoint() method.
     *
     * @param date                A string representing the date on which the data was captured.
     * @param rainfallMillimeters A double representing the amount of rainfall recorded.
     * @param location            A string representing the location where the data was captured.
     */
    public DataPoint(String date, double rainfallMillimeters, String location) {
        this(LocalDate.parse(date), rainfallMillimeters, location);
    }

    public DataPoint(LocalDate date, double rainfallMillimeters, String location) {
        this.date = date;
        this.rainfallMillimeters = rainfallMillimeters;
        this.location = location;
    }

    /**
     * Gets the date on which this data was captured.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the amount of rainfall recorded.
     */
    public double getRainfallMillimeters() {
        return rainfallMillimeters;
    }

    /**
     * Gets the location where this data was captured.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns a string representation of this data point.
     * <p>
     * The string is of the form: "31.52mm, recorded @ Auckland on 2019-12-20".
     */
    @Override
    public String toString() {
        return String.format("%.2f mm, recorded @ %s on %s", rainfallMillimeters, location, date);
    }
}
