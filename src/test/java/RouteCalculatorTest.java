import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndex;
    RouteCalculator calculator;

    @Override
    protected void setUp() throws Exception {

        stationIndex = new StationIndex();

        Line line1 = new Line(1, "First");
        Line line2 = new Line(2, "second");
        Line line3 = new Line(3, "third");

        Station stationVasil = new Station("Васильеостровская", line1);
        Station stationNevskyPr = new Station("Невский проспект", line1);
        Station stationMayakovskaya = new Station("Маяковская", line1);
        Station stationSquareVosstaniya = new Station("Площадь Восстания", line2);
        Station stationLigovskyi = new Station("Лиговский проспект", line2);

        createStationIndex();
        calculator = new RouteCalculator(stationIndex);

        route = new ArrayList<>();
        route.add(stationVasil);
        route.add(stationNevskyPr);
        route.add(stationMayakovskaya);
        route.add(stationSquareVosstaniya);
        route.add(stationLigovskyi);

    }

    private void createStationIndex() {
        stationIndex = new StationIndex();
        Line line1 = new Line(1, "First");
        Line line2 = new Line(2, "second");
        Line line3 = new Line(3, "third");

        Station stationPrimorsk = new Station("Приморская", line1);
        Station stationVasil = new Station("Васильеостровская", line1);
        Station stationNevskyPr = new Station("Невский проспект", line1);
        Station stationMayakovskaya = new Station("Маяковская", line1);
        Station stationSquareVosstaniya = new Station("Площадь Восстания", line2);
        Station stationLigovskyi = new Station("Лиговский проспект", line2);
        Station stationPushkin = new Station("Пушкинская", line2);
        Station stationZvenigorod = new Station("Звенигородская", line3);
        Station stationObvodnoi = new Station("Обводной канал", line3);

        line1.addStation(stationPrimorsk);
        line1.addStation(stationVasil);
        line1.addStation(stationNevskyPr);
        line1.addStation(stationMayakovskaya);
        line2.addStation(stationSquareVosstaniya);
        line2.addStation(stationLigovskyi);
        line2.addStation(stationPushkin);
        line3.addStation(stationZvenigorod);
        line3.addStation(stationObvodnoi);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addStation(stationPrimorsk);
        stationIndex.addStation(stationVasil);
        stationIndex.addStation(stationNevskyPr);
        stationIndex.addStation(stationMayakovskaya);
        stationIndex.addStation(stationSquareVosstaniya);
        stationIndex.addStation(stationLigovskyi);
        stationIndex.addStation(stationPushkin);
        stationIndex.addStation(stationZvenigorod);
        stationIndex.addStation(stationObvodnoi);

        List<Station> connect1 = Arrays.asList(stationMayakovskaya, stationSquareVosstaniya);
        List<Station> connect2 = Arrays.asList(stationPushkin, stationZvenigorod);
        stationIndex.addConnection(connect1);
        stationIndex.addConnection(connect2);

    }

    @Test
    public void testCalulateDuration() {

        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11.0;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteOnTheLine() {
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Васильеостровская"));
        expected.add(stationIndex.getStation("Невский проспект"));
        expected.add(stationIndex.getStation("Маяковская"));

        Station from = stationIndex.getStation("Васильеостровская");
        Station to = stationIndex.getStation("Маяковская");
        List<Station> actual = calculator.getShortestRoute(from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteWithOneConnection() {
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Маяковская"));
        expected.add(stationIndex.getStation("Площадь Восстания"));
        expected.add(stationIndex.getStation("Лиговский проспект"));
        expected.add(stationIndex.getStation("Пушкинская"));

        Station from = stationIndex.getStation("Маяковская");
        Station to = stationIndex.getStation("Пушкинская");
        List<Station> actual = calculator.getShortestRoute(from, to);

        assertEquals(expected, actual);
    }

    @Test
    public void testGetRouteWithTwoConnection() {
        List<Station> expected = new ArrayList<>();
        expected.add(stationIndex.getStation("Маяковская"));
        expected.add(stationIndex.getStation("Площадь Восстания"));
        expected.add(stationIndex.getStation("Лиговский проспект"));
        expected.add(stationIndex.getStation("Пушкинская"));
        expected.add(stationIndex.getStation("Звенигородская"));
        expected.add(stationIndex.getStation("Обводной канал"));

        Station from = stationIndex.getStation("Маяковская");
        Station to = stationIndex.getStation("Обводной канал");
        List<Station> actual = calculator.getShortestRoute(from, to);

        assertEquals(expected, actual);

    }


    @Override
    protected void tearDown() throws Exception {

    }
}

