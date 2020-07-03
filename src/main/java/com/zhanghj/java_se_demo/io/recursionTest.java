package main.java.com.zhanghj.java_se_demo.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class recursionTest {
    public static void main(String[] args) {
        List<Department> departmentList = new ArrayList<>();

        departmentList.add(new Department(1, "白胡子", 0));
        departmentList.add(new Department(2, "不死鸟", 1));
        departmentList.add(new Department(3, "艾斯", 1));
        departmentList.add(new Department(4, "龙", 0));
        departmentList.add(new Department(5, "路飞", 4));
        departmentList.add(new Department(6, "索隆", 5));
        departmentList.add(new Department(7, "娜美", 5));
        departmentList.add(new Department(8, "罗宾", 5));
        departmentList.add(new Department(9, "乌索普", 5));
        departmentList.add(new Department(10, "小丑", 100));//小丑的长官Id不存在，所以tree中没有它的信息
        List<Department> tree = makeTree(departmentList, 0);
        System.out.println(tree);
        System.out.println("-------------------------->");
    }

    private static List<Department> makeTree(List<Department> departmentList, int pId) {
        //子类
        List<Department> children = departmentList.stream().filter(x -> x.getParentId() == pId).collect(Collectors.toList());
        //后辈中的非子类
        List<Department> successor = departmentList.stream().filter(x -> x.getParentId() != pId).collect(Collectors.toList());
        children.forEach(x -> {
            makeTree(successor, x.getId()).forEach(y -> x.getChildren().add(y));
        });
        return children;
    }
}

class Department {
    private int id;
    private String name;
    private int parentId;
    private List<Department> children = new ArrayList<>();

    public Department(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}