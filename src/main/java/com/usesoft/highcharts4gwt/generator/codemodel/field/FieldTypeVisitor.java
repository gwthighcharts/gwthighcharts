package com.usesoft.highcharts4gwt.generator.codemodel.field;

public interface FieldTypeVisitor<IN, OUT>
{
    OUT visitNumber(IN in);

    OUT visitBoolean(IN in);

    OUT visitString(IN in);

    OUT visitOther(IN in); // not implemented cases
}
