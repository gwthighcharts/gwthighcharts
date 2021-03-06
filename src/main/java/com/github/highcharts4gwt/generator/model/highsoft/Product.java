package com.github.highcharts4gwt.generator.model.highsoft;

import javax.annotation.CheckForNull;

public enum Product
{
    Highcharts
    {
        @CheckForNull
        @Override
        public <IN, OUT> OUT accept(ProductVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitHighcharts(in);
        }

        @Override
        public String getProductPackageName()
        {
            return "highcharts";
        }
    },
    Highmaps
    {
        @CheckForNull
        @Override
        public <IN, OUT> OUT accept(ProductVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitHighmaps(in);
        }

        @Override
        public String getProductPackageName()
        {
            return "highmaps";
        }
    },
    Highstock
    {
        @CheckForNull
        @Override
        public <IN, OUT> OUT accept(ProductVisitor<IN, OUT> visitor, IN in)
        {
            return visitor.visitHighstock(in);
        }

        @Override
        public String getProductPackageName()
        {
            return "highstock";
        }
    };

    @CheckForNull
    public abstract <IN, OUT> OUT accept(ProductVisitor<IN, OUT> visitor, IN in);

    public abstract String getProductPackageName();

}
