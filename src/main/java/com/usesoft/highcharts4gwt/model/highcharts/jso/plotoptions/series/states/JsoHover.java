
package com.usesoft.highcharts4gwt.model.highcharts.jso.plotoptions.series.states;

import com.google.gwt.core.client.JavaScriptObject;
import com.usesoft.highcharts4gwt.model.highcharts.api.plotoptions.series.states.Hover;
import com.usesoft.highcharts4gwt.model.highcharts.api.plotoptions.series.states.hover.Marker;

public class JsoHover
    extends JavaScriptObject
    implements Hover
{


    protected JsoHover() {
    }

    public final native boolean enabled()
        throws RuntimeException /*-{
        return this["enabled"] = (this["enabled"] || {});
    }-*/
    ;

    public final native JsoHover enabled(boolean enabled)
        throws RuntimeException /*-{
        this["enabled"] = enabled;
        return this;
    }-*/
    ;

    public final native Number lineWidth()
        throws RuntimeException /*-{
        return this["lineWidth"] = (this["lineWidth"] || {});
    }-*/
    ;

    public final native JsoHover lineWidth(Number lineWidth)
        throws RuntimeException /*-{
        this["lineWidth"] = lineWidth;
        return this;
    }-*/
    ;

    public final native Number lineWidthPlus()
        throws RuntimeException /*-{
        return this["lineWidthPlus"] = (this["lineWidthPlus"] || {});
    }-*/
    ;

    public final native JsoHover lineWidthPlus(Number lineWidthPlus)
        throws RuntimeException /*-{
        this["lineWidthPlus"] = lineWidthPlus;
        return this;
    }-*/
    ;

    public final native Marker marker()
        throws RuntimeException /*-{
        return this["marker"] = (this["marker"] || {});
    }-*/
    ;

    public final native JsoHover marker(Marker marker)
        throws RuntimeException /*-{
        this["marker"] = marker;
        return this;
    }-*/
    ;

}
