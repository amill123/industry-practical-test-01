package rainfalldata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * This class reads in a data file containing daily rainfall, in millimetres, for several NZ locations. It contains
 * methods which give useful information based on this data.
 */
public class RainfallProcessor {

    /**
     * Stores all rainfall data.
     */
    private List<DataPoint> data;

    /**
     * All locations for which there is data.
     */
    private Set<String> locations;

    /**
     * Creates a new {@link RainfallProcessor}.
     */
    public RainfallProcessor(String fileName) throws IOException {
        processFile(fileName);
    }

    /**
     * Gets the total number of data points read in from the file.
     */
    public int getNumDataPoints() {
        return data.size();
    }

    /**
     * Gets all locations for which there is data.
     */
    public Set<String> getLocations() {

        return locations;
    }

    /**
     * Gets the average daily rainfall across all locations.
     */
    public double getAverageRainfall() {
        //Loop through the data list and sum up all the rainfall quantities then divide by the total number of datapoints
        double totalRainfall = 0.0;
        for (int i = 0; i < data.size(); i++) {
            totalRainfall = totalRainfall+ data.get(i).getRainfallMillimeters();
        }

        return totalRainfall/getNumDataPoints();
    }

    /**
     * Gets the average daily rainfall for the given location.
     */
    public double getAverageRainfallFor(String location) {


        double totalRainfallAtLocation = 0.0;
        int numberOfDataPointsForLocation = 0;

        //Loop through the data list and check that the location matches the given location. If it does increase the total rainfall.
        //Increase the datapoints for the location each time the rainfall at the location is added to the total.
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getLocation().equals(location)){
                totalRainfallAtLocation = totalRainfallAtLocation + data.get(i).getRainfallMillimeters();
                numberOfDataPointsForLocation++;
            }
        }
        return totalRainfallAtLocation/numberOfDataPointsForLocation;
    }

    /**
     * Gets the given number of {@link DataPoint}s corresponding to the highest recorded rainfall, sorted highest to lowest.
     *
     * @param num the number of data points to return
     * @return the <code>num</code> data points with the highest rainfall, sorted highest to lowest.
     */
    public List<DataPoint> getHighestRainfall(int num) {

        //Create a comparator to compare datapoints and sort according to rainfall
        Comparator<DataPoint> dataCompare = new Comparator<>() {
            @Override
            public int compare(DataPoint dataPoint, DataPoint t1) {
                return Double.valueOf(t1.getRainfallMillimeters()).compareTo(dataPoint.getRainfallMillimeters());
            }
        };
        Collections.sort(data, dataCompare);
        //Create a new list and loop through the number of datapoints which have already been sorted by rainfall
        List<DataPoint> highestRainfall = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            highestRainfall.add(data.get(i));
        }
        return highestRainfall;

    }

    /**
     * Gets the given number of {@link DataPoint}s corresponding to the highest recorded rainfall in the
     * given location, sorted highest to lowest.
     */
    public List<DataPoint> getHighestRainfallIn(String location, int num) {
        List<DataPoint> locationData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i).getLocation().equals(location)){
                locationData.add(data.get(i));
            }
        }

        Comparator<DataPoint> dataCompare = new Comparator<>() {
            @Override
            public int compare(DataPoint dataPoint, DataPoint t1) {
                return Double.valueOf(t1.getRainfallMillimeters()).compareTo(dataPoint.getRainfallMillimeters());
            }
        };
        Collections.sort(locationData, dataCompare);
        List<DataPoint> highestRainfall = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            highestRainfall.add(locationData.get(i));
        }
        return highestRainfall;

    }

    public double getAverageRainfallForSeason(String season){
        List<DataPoint> locationSeasonalData = new ArrayList<>();
        String matchSeason = null;
        //loop through the data list and get the month of every datapoint and sort into season
        for (int i = 0; i < data.size(); i++) {
            int month = data.get(i).getDate().getMonthValue();
            switch(month) {
                case 12:
                case 1:
                case 2:
                    matchSeason = "summer";
                    break;
                case 3:
                case 4:
                case 5:
                    matchSeason = "autumn";
                    break;
                case 6:
                case 7:
                case 8:
                    matchSeason = "winter";
                    break;
                case 9:
                case 10:
                case 11:
                    matchSeason = "spring";
                    break;
            }
            //if the season of the entry matches the requested season then add it to a list of datapoints
            if(season.toLowerCase().equals(matchSeason)){
                locationSeasonalData.add(data.get(i));
            }
        }

        //In the future you could change the getAverageRainfall method so that it accepts an input argument
        //of type List<Datapoint> which would enable you to change what it takes the average of. I.e. per season
        //Rather than duplicating the code. I was unsure if I was allowed to touch the method signature - so decided against it.
        double totalSeasonalRainfall = 0.0;
        for (int i = 0; i < locationSeasonalData.size(); i++) {
            totalSeasonalRainfall = totalSeasonalRainfall+ locationSeasonalData.get(i).getRainfallMillimeters();
        }
        //ensure to use the new list's size to get a reduced number of data points
        return totalSeasonalRainfall/(locationSeasonalData.size());

    }

    /**
     * Reads in the given file, and uses the info contained within to populate the data and locations collections.
     */
    private void processFile(String fileName) throws IOException {
        //create a new arraylist and tree set
        data = new ArrayList<>();

        locations = new TreeSet<>();
        //open up a buffered reader. If there is still lines to read pass the line through the createDataPoint method to generate new DataPoint and add it to the list.
        //Also get the location and add it to the treeset
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = null;
            while((line = reader.readLine()) != null){
                DataPoint dataPoint = createDataPoint(line);
                data.add(dataPoint);
                locations.add(dataPoint.getLocation());
            }

        }


    }

    /**
     * Converts the given string (one line from the read file) into a {@link DataPoint} object.
     */
    private DataPoint createDataPoint(String line) {
        //Split at each comma and then assign each value to their appropriate variable and then generate a new DataPoint
        String[] splitLine = line.split(",");
        String date = splitLine[0];
        double rainfall = Double.parseDouble(splitLine[1]);
        String location = splitLine[2];
        DataPoint dataPoint = new DataPoint(date, rainfall, location);
        return dataPoint;
    }

}
