package com.wisoft.template.generator;

import com.wisoft.template.entity.Table;
import com.wisoft.template.handler.MvcHandler;
import com.wisoft.template.handler.TableHandler;

import java.util.List;

public class Genterator {
    public static void main(String[] args) {
        TableHandler tableHandler = new TableHandler();
        tableHandler.addExceptTable("gen_table");//排除某一个表
        MvcHandler mvcHandler = new MvcHandler();
        List<Table> tableList = tableHandler.getTables();
        for (Table table : tableList) {
            mvcHandler.executeModule(table);
            mvcHandler.executeService(table);
            mvcHandler.executeServiceImpl(table);
            mvcHandler.executeMapper(table);
            mvcHandler.executeClazzMapper(table);
        }
    }
}
