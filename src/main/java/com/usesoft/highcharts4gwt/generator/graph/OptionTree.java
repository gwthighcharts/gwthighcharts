package com.usesoft.highcharts4gwt.generator.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A tree of options. It contains a root and different maps and lists to be able to generate the classes (from leaves to root of the tree). Leaves are fields.
 * @author rquillevere
 */
public class OptionTree
{
    public OptionTree(OptionSpec root)
    {
        this.root = root;
        this.parentToChildrenRelations = new HashMap<OptionSpec, List<OptionSpec>>();
        this.childToParentRelations = new HashMap<OptionSpec, OptionSpec>();
        this.leaves = new ArrayList<OptionSpec>();
        this.all = new ArrayList<OptionSpec>();
    }

    public OptionSpec getRoot()
    {
        return root;
    }

    public Map<OptionSpec, List<OptionSpec>> getParentToChildrenRelations()
    {
        return parentToChildrenRelations;
    }

    public void computeAndAddRelation(OptionSpec option, List<OptionSpec> options)
    {
        all.add(option);

        setLeavesInfo(option);

        setDepthInfo(option);

        if (OptionUtils.isRoot(option))
            return;

        OptionSpec parent = OptionUtils.findParent(option, options);

        if (parent == null)
            return;

        setParentChildrenInfo(option, parent);
    }

    public HashMap<OptionSpec, OptionSpec> getChildToParentRelations()
    {
        return childToParentRelations;
    }

    public ArrayList<OptionSpec> getLeaves()
    {
        return leaves;
    }

    public ArrayList<OptionSpec> getAll()
    {
        return all;
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this).add("root", getRoot()).toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (obj instanceof OptionTree)
            return Objects.equal(getRoot(), ((OptionTree) obj).getRoot());
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(getRoot());
    }

    public int getDepth()
    {
        return depth;
    }

    public Map<Integer, List<OptionSpec>> getOptionsByDepth()
    {
        return optionsByDepth;
    }

    private void setLeavesInfo(OptionSpec option)
    {
        if (!option.isParent())
            leaves.add(option);
    }

    private void setParentChildrenInfo(OptionSpec option, OptionSpec parent)
    {
        childToParentRelations.put(option, parent);

        List<OptionSpec> children = parentToChildrenRelations.get(parent);

        if (children == null)
        {
            List<OptionSpec> list = new ArrayList<OptionSpec>();
            list.add(option);
            parentToChildrenRelations.put(parent, list);
        }
        else
            children.add(option);
    }

    private void setDepthInfo(OptionSpec option)
    {
        int depth2 = OptionUtils.depth(option);
        List<OptionSpec> list = optionsByDepth.get(depth2);
        if (list == null)
            list = new ArrayList<OptionSpec>();

        list.add(option);
        optionsByDepth.put(depth2, list);

        this.depth = depth2 > this.depth ? depth2 : this.depth;
    }

    private final OptionSpec root;

    private final ArrayList<OptionSpec> leaves;
    private final ArrayList<OptionSpec> all;

    private final Map<OptionSpec, List<OptionSpec>> parentToChildrenRelations;

    private final HashMap<OptionSpec, OptionSpec> childToParentRelations;

    private int depth = 0;
    private final Map<Integer, List<OptionSpec>> optionsByDepth = new HashMap<Integer, List<OptionSpec>>();

}