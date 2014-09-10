
package com.usesoft.highcharts4gwt.model.highcharts.mock.exporting.buttons;

import com.usesoft.highcharts4gwt.model.highcharts.api.exporting.buttons.ContextButton;

public class MockContextButton
    implements ContextButton
{

    private String symbol;
    private Number x;

    public String symbol() {
        return symbol;
    }

    public MockContextButton symbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public Number x() {
        return x;
    }

    public MockContextButton x(Number x) {
        this.x = x;
        return this;
    }

}
