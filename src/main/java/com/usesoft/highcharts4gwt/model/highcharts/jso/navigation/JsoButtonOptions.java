
package com.usesoft.highcharts4gwt.model.highcharts.jso.navigation;

import com.google.gwt.core.client.JavaScriptObject;
import com.usesoft.highcharts4gwt.model.highcharts.api.navigation.ButtonOptions;

public class JsoButtonOptions
    extends JavaScriptObject
    implements ButtonOptions
{


    protected JsoButtonOptions() {
    }

    public final native String align()
        throws RuntimeException /*-{
        return this["align"] = (this["align"] || {});
    }-*/
    ;

    public final native JsoButtonOptions align(String align)
        throws RuntimeException /*-{
        this["align"] = align;
        return this;
    }-*/
    ;

    public final native boolean enabled()
        throws RuntimeException /*-{
        return this["enabled"] = (this["enabled"] || {});
    }-*/
    ;

    public final native JsoButtonOptions enabled(boolean enabled)
        throws RuntimeException /*-{
        this["enabled"] = enabled;
        return this;
    }-*/
    ;

    public final native Number height()
        throws RuntimeException /*-{
        return this["height"] = (this["height"] || {});
    }-*/
    ;

    public final native JsoButtonOptions height(Number height)
        throws RuntimeException /*-{
        this["height"] = height;
        return this;
    }-*/
    ;

    public final native Number symbolSize()
        throws RuntimeException /*-{
        return this["symbolSize"] = (this["symbolSize"] || {});
    }-*/
    ;

    public final native JsoButtonOptions symbolSize(Number symbolSize)
        throws RuntimeException /*-{
        this["symbolSize"] = symbolSize;
        return this;
    }-*/
    ;

    public final native Number symbolStrokeWidth()
        throws RuntimeException /*-{
        return this["symbolStrokeWidth"] = (this["symbolStrokeWidth"] || {});
    }-*/
    ;

    public final native JsoButtonOptions symbolStrokeWidth(Number symbolStrokeWidth)
        throws RuntimeException /*-{
        this["symbolStrokeWidth"] = symbolStrokeWidth;
        return this;
    }-*/
    ;

    public final native Number symbolX()
        throws RuntimeException /*-{
        return this["symbolX"] = (this["symbolX"] || {});
    }-*/
    ;

    public final native JsoButtonOptions symbolX(Number symbolX)
        throws RuntimeException /*-{
        this["symbolX"] = symbolX;
        return this;
    }-*/
    ;

    public final native Number symbolY()
        throws RuntimeException /*-{
        return this["symbolY"] = (this["symbolY"] || {});
    }-*/
    ;

    public final native JsoButtonOptions symbolY(Number symbolY)
        throws RuntimeException /*-{
        this["symbolY"] = symbolY;
        return this;
    }-*/
    ;

    public final native String text()
        throws RuntimeException /*-{
        return this["text"] = (this["text"] || {});
    }-*/
    ;

    public final native JsoButtonOptions text(String text)
        throws RuntimeException /*-{
        this["text"] = text;
        return this;
    }-*/
    ;

    public final native String verticalAlign()
        throws RuntimeException /*-{
        return this["verticalAlign"] = (this["verticalAlign"] || {});
    }-*/
    ;

    public final native JsoButtonOptions verticalAlign(String verticalAlign)
        throws RuntimeException /*-{
        this["verticalAlign"] = verticalAlign;
        return this;
    }-*/
    ;

    public final native Number width()
        throws RuntimeException /*-{
        return this["width"] = (this["width"] || {});
    }-*/
    ;

    public final native JsoButtonOptions width(Number width)
        throws RuntimeException /*-{
        this["width"] = width;
        return this;
    }-*/
    ;

    public final native Number y()
        throws RuntimeException /*-{
        return this["y"] = (this["y"] || {});
    }-*/
    ;

    public final native JsoButtonOptions y(Number y)
        throws RuntimeException /*-{
        this["y"] = y;
        return this;
    }-*/
    ;

}
