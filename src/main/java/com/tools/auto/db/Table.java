package com.tools.auto.db;

import java.util.List;

public class Table {

	private String tableName; //表名
	private List<Structure> tableInfo; //表结构
	private List<Index> indexInfo; //表索引
	private List<List<TableData>> tableData; //表数据
	private String entityName; //bean名称
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Structure> getTableInfo() {
		return tableInfo;
	}
	public void setTableInfo(List<Structure> tableInfo) {
		this.tableInfo = tableInfo;
	}
	public List<Index> getIndexInfo() {
		return indexInfo;
	}
	public void setIndexInfo(List<Index> indexInfo) {
		this.indexInfo = indexInfo;
	}
	public List<List<TableData>> getTableData() {
		return tableData;
	}
	public void setTableData(List<List<TableData>> tableData) {
		this.tableData = tableData;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	
}
