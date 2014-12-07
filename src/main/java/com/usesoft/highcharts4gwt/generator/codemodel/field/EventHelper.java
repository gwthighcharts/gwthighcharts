package com.usesoft.highcharts4gwt.generator.codemodel.field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.usesoft.highcharts4gwt.generator.graph.Option;

public class EventHelper
{
    public static final String HANDLER_SUFFIX = "Handler";
    public static final String EVENT_SUFFIX = "Event";
    public static final String ON_PREFIX = "on";
    public static final String GET_SERIES_METHOD_NAME = "getSeries";

    public static final Pattern SERIES_EVENTS_PATTERN = Pattern.compile(".*plotOptions\\.\\w+\\.events.*");

    private EventHelper()
    {
    }

    public static EventType getType(String optionFullName)
    {
        Matcher matcher = SERIES_EVENTS_PATTERN.matcher(optionFullName);
        if (matcher.matches())
        {
            return EventType.Series;
        }

        return EventType.DoNotTreat;
    }

    public static String getEventNamePrefix(Option option)
    {
        // plotOptions.gauge.events.afterAnimate
        String fullname = option.getFullname();

        int i = fullname.indexOf(".events");

        // plotOptions.gauge
        String v1 = fullname.substring(0, i);

        // gauge
        int i2 = v1.lastIndexOf(".");
        String v2 = "";
        if (i2 != -1)
        {
            v2 = v1.substring(i2 + 1, v1.length());
            v2 = v2.substring(0, 1).toUpperCase() + v2.substring(1);
        }
        else
        {
            v2 = v1.substring(0, 1).toUpperCase() + v1.substring(1);
        }

        // GaugeClickEvent
        String eventName = v2 + option.getTitle().substring(0, 1).toUpperCase() + option.getTitle().substring(1);
        return eventName;
    }
}