package com.usesoft.highcharts4gwt.generator.codemodel;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.usesoft.highcharts4gwt.generator.graph.OptionSpec;

public abstract class BaseFieldBuilder implements FieldBuilder
{

    private JCodeModel codeModel;
    private JDefinedClass jClass;

    public JCodeModel getCodeModel()
    {
        return codeModel;
    }

    @Override
    public void setCodeModel(JCodeModel codeModel)
    {
        this.codeModel = codeModel;
    }

    @Override
    public void addField(OptionSpec optionSpec)
    {

        // if (optionSpec.isParent())
        // addClassField(optionSpec.getName(), optionSpec.getName());

        String returnType = optionSpec.getReturnType();

        if (returnType != null && returnType.equalsIgnoreCase("Number"))
            addNumberField(optionSpec.getTitle());
        if (returnType != null && returnType.equalsIgnoreCase("String"))
            addStringField(optionSpec.getTitle());
        if (returnType != null && returnType.equalsIgnoreCase("Boolean"))
            addBooleanField(optionSpec.getTitle());
    }

    @Override
    public void setJclass(JDefinedClass jClass)
    {
        this.jClass = jClass;
    }

    protected abstract void addNumberField(String fieldName);

    protected abstract void addStringField(String fieldName);

    protected abstract void addBooleanField(String fieldName);

    protected abstract void addObjectField(String fieldName);

    protected abstract void addFunctionField(String fieldName);

    protected abstract void addColorField(String fieldName);

    protected abstract void addMixedField(String fieldName);

    protected abstract void addCssObjectField(String fieldName);

    // <T> protected abstract void addArrayField(String fieldName, T type);

    protected abstract void addClassField(String className, String fieldName);

    protected JDefinedClass getJclass()
    {
        return jClass;
    }

}
