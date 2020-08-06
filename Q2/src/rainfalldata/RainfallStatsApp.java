package rainfalldata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;


/**
 * Rainfall data courtesy of Stats NZ:
 * http://archive.stats.govt.nz/browse_for_stats/environment/environmental-reporting-series/environmental-indicators/Home/Atmosphere-and-climate/annual-rain.aspx
 */
public class RainfallStatsApp {

    private void start() {

        try {
            System.out.println("Reading rainfall-data.csv...");
            RainfallProcessor processor = new RainfallProcessor("rainfall-data.csv");
            System.out.println("Processing complete!");
            System.out.println("Read " + processor.getNumDataPoints() + " data points.");
            System.out.println();

            printLocations(processor);
            System.out.println();

            printRainfallStats(processor);
            System.out.println();

            saveAverages(processor, "averages.txt");
            System.out.println();

            printSeasonalRainfall(processor);


        } catch (IOException e) {
            System.out.println("There was an error reading the file: " + e.getMessage());
        }

    }

    private void printLocations(RainfallProcessor processor) {
        Set<String> locations = processor.getLocations();
        System.out.printf("Data recorded for %d unique locations:", locations.size());
        System.out.println();
        for (String l : locations) {
            System.out.println("- " + l);
        }
    }

    private void printRainfallStats(RainfallProcessor processor) {

        System.out.printf("Average daily rainfall NZ-wide: %.2f millimetres", processor.getAverageRainfall());
        System.out.println();

        System.out.printf("Average daily rainfall for Auckland: %.2f millimetres", processor.getAverageRainfallFor("Auckland"));
        System.out.println();

        System.out.println();

        List<DataPoint> tenHighest = processor.getHighestRainfall(10);
        System.out.println("Ten highest daily readings in NZ:");
        for (DataPoint dp : tenHighest) {
            System.out.println("- " + dp);
        }
        System.out.println();

        List<DataPoint> tenHighestInAuckland = processor.getHighestRainfallIn("Auckland", 10);
        System.out.println("Ten highest daily readings in Auckland:");
        for (DataPoint dp : tenHighestInAuckland) {
            System.out.println("- " + dp);
        }

    }

    private void saveAverages(RainfallProcessor processor, String fileName) throws IOException {

        // Generate a PrintWriter. For each location get the average rainfall then save it to file
       try (PrintWriter writer = new PrintWriter(new FileWriter(new File(fileName)))) {
            for(String location: processor.getLocations()){
                double averageRainfall = processor.getAverageRainfallFor(location);
                writer.print("The average daily rainfall for "+ location + ": ");
                writer.printf("%.2f", averageRainfall);
                writer.print(" millimeters\n");
            }
       }

    }

    private void printSeasonalRainfall(RainfallProcessor processor) {

        System.out.printf("Average Summer rainfall: %.2f mm\n", processor.getAverageRainfallForSeason("summer"));
        System.out.printf("Average Autumn rainfall: %.2f mm\n", + processor.getAverageRainfallForSeason("autumn"));
        System.out.printf("Average Winter rainfall: %.2f mm\n", + processor.getAverageRainfallForSeason("winter"));
        System.out.printf("Average Spring rainfall: %.2f mm\n", + processor.getAverageRainfallForSeason("spring"));
    }

    public static void main(String[] args) {

        new RainfallStatsApp().start();

    }

}
