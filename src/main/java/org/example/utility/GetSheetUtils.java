package org.example.utility;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetSheetUtils {
    private static final String APPLICATION_NAME = "SpreadsheetConnector";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    public static String spreadsheetId = "1atUZNeZ9IEyk-iHQcEfRv_-TUW5JN1JYIYYnKzkNDWk";
    private static final Logger LOGGER = Logger.getLogger(GetSheetUtils.class.getName());

    /**
     * Returns a Sheets service instance.
     *
     * @return Sheets service instance.
     * @throws GeneralSecurityException If there is a security exception.
     * @throws IOException If there is an IO exception.
     */
    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, TestSetup.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Fetches data from a specified range in the Google Sheet.
     *
     * @param range The range to fetch data from.
     * @return List of rows with data.
     * @throws IOException If there is an IO exception.
     * @throws GeneralSecurityException If there is a security exception.
     */
    public static List<List<Object>> getData(String range) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
        List<List<Object>> values = response.getValues();

        if (values == null || values.isEmpty()) {
            LOGGER.log(Level.INFO, "No data found.");
            return Collections.emptyList();
        } else {
            return values;
        }
    }

    /**
     * Clears data from a specified range in the Google Sheet.
     *
     * @param range The range to clear data from.
     * @throws IOException If there is an IO exception.
     * @throws GeneralSecurityException If there is a security exception.
     */
    public static void clearGoogleSheet(String range) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        ClearValuesRequest requestBody = new ClearValuesRequest();
        Sheets.Spreadsheets.Values.Clear request = service.spreadsheets().values().clear(spreadsheetId, range, requestBody);
        request.execute();
        LOGGER.log(Level.INFO, "Data cleared in range: " + range);
    }

    /**
     * Writes data to a specified range in the Google Sheet.
     *
     * @param range The range to write data to.
     * @param values The data to write.
     * @throws IOException If there is an IO exception.
     * @throws GeneralSecurityException If there is a security exception.
     */
    public static void writeData(String range, List<List<Object>> values) throws IOException, GeneralSecurityException {
        Sheets service = getSheetsService();
        ValueRange body = new ValueRange().setValues(values);
        UpdateValuesResponse result = service.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
        LOGGER.log(Level.INFO, "{0} cells updated.", result.getUpdatedCells());
    }

    public static void writeData(String range, String value) throws Exception {
        ValueRange valueRange = new ValueRange();
        List<Object> list = new ArrayList<>();
        list.add(value);
        List<List<Object>> rangeData = new ArrayList<>();
        rangeData.add(list);
        valueRange.setValues(rangeData);
        // Adding a delay of 500 milliseconds
        Thread.sleep(500);
        Sheets.Spreadsheets.Values.Update request = getSheetsService().spreadsheets().values()
                .update(spreadsheetId, range, valueRange);
        request.setValueInputOption("USER_ENTERED");
        request.execute();
    }
}
