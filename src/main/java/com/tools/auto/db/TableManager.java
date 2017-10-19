package com.tools.auto.db;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.sql.DataSource;

import com.tools.jdbc.JdbcOperate;
import com.tools.jdbc.c3p0.C3P0PropertiesConfig;
import com.tools.utils.Attacks;
import com.tools.utils.Tools;

public class TableManager {
	
	private static JdbcOperate jdbcOperate = null;
	
	
	public TableManager(String dbpath){
		File file = new File(dbpath);
		if(file.exists()){
			new TableManager(file);
		}else{
			DataSource dataSource = new C3P0PropertiesConfig(dbpath).getDataSource();
			jdbcOperate = new JdbcOperate(dataSource);
		}
	}
	
	public TableManager(File file){
		DataSource dataSource = new C3P0PropertiesConfig(file).getDataSource();
		jdbcOperate = new JdbcOperate(dataSource);
	}
	
	public TableManager(DataSource dataSource){
		jdbcOperate = new JdbcOperate(dataSource);
	}
	
	public List<Table> getDbInfo() {
		List<String> tableNames = getTableNames();
		Table table = null;
		List<Table> tables = null;
		if(tableNames!=null && tableNames.size()>0){
			tables = new ArrayList<Table>();
			for(int i=0;i<tableNames.size();i++){
				table = new Table();
				table.setTableInfo(getTableStructure(tableNames.get(i)));
				table.setIndexInfo(getTableIndex(tableNames.get(i)));
				table.setTableName(tableNames.get(i));
				tables.add(table);
			}
		}
		return tables;
	}
	
	public Table getDbTable(String tableName) {
		Table table = new Table();
		table.setTableInfo(getTableStructure(tableName));
		table.setIndexInfo(getTableIndex(tableName));
		table.setTableName(tableName);
		return table;
	}
	
	private List<String> getTableNames() {
		String sql = "show tables";
		List<String> tables = new ArrayList<String>();
		try {
			tables = jdbcOperate.queryObjectList(sql, String.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}
	
	private List<Structure> getTableStructure(String table) {
		String sql = "show full columns from " + table;
		List<Structure> structures = new ArrayList<Structure>();
		try {
			List<Map<String, Object>> map = jdbcOperate.queryMapList(sql);
			Structure structure = null;
			for (Map<String, Object> node : map) {
				structure = new Structure();
				structure.setField(node.get("Field")+"");
				structure.setType(getType((node.get("Type")+"").split("[(]")[0]));
				structure.setIsNull(node.get("Null")+"");
				structure.setKey(node.get("Key")+"");
				structure.setDefaultValue(node.get("Default")== null ? "" : node.get("Default")+"");
				structure.setExtra(node.get("Extra")+"");
				structure.setComment(node.get("Comment")+"");
				structures.add(structure);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return structures;
	}
	
	private List<Index> getTableIndex(String table) {
		String sql = "show index from " + table;
		List<Index> indexs = new ArrayList<Index>();
		try {
			List<Map<String, Object>> map = jdbcOperate.queryMapList(sql);
			Index index = null;
			for (Map<String, Object> node : map) {
				index = new Index();
				index.setIndexName(node.get("Key_name")+"");
				index.setColumnName(node.get("Column_name")+"");
				index.setIndexType(node.get("Index_type")+"");
				index.setIsUnique(Tools.parseInt(node.get("Non_unique")+"") == 0 ? 1 : 0);
				indexs.add(index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return indexs;
	}
	
	private String getType(String type){
		String str = "";
		switch (type) {
		case "varchar":
			str = "String";
			break;
		case "char":
			str = "String";
			break;
		case "text":
			str = "String";
			break;
		case "int":
			str = "int";
			break;
		case "tinyint":
			str = "int";
			break;
		case "bigint":
			str = "long";
			break;
		case "float":
			str = "float";
			break;
		case "double":
			str = "double";
			break;
		case "decimal":
			str = "float";
			break;
		default:
			str = "String";
			break;
		}
		return str;
	}
	
	public static void main(String[] args) {
//		TableManager manager = new TableManager("db.properties");
//		Table table = manager.getDbTable("s_app");
//		System.out.println(JSON.toJSONString(table));
		
		String ename = "s_group_menu_btn".replace("s_", "");
		ename = ename.substring(0, 1).toUpperCase() + ename.substring(1);
		System.out.println(ename);
		int index = 0;
		while ((index = ename.indexOf("_")) != -1) {
			ename = ename.substring(0, index)+ename.substring(index+1, index+2).toUpperCase()+ename.substring(index+2);
		}
		System.out.println(ename);
		
		String[] tmp = "0001000100021001|90001|小小邮差Ⅰ|1-1N|夜战||".split("\\|", -1);
		System.out.println(tmp.length);
		
		String basePackge = "  com.auto.   ";
		basePackge = basePackge.trim();
		if(basePackge.endsWith(".")){
			basePackge = basePackge.substring(0, basePackge.length() - 1 );
		}
		System.out.println(basePackge);
		
		List<String> list = Stream.of("one", "two", "three", "four")
		 .filter(e -> e.length() > 3)
		 .peek(e -> System.out.println("Filtered value: " + e))
		 .map(String::toUpperCase)
		 .peek(e -> System.out.println("Mapped value: " + e))
		 .collect(Collectors.toList());
		System.out.println(list);

		System.out.println("1=1and ".toLowerCase().matches(Attacks.sqlAnd));
	}
	
}
