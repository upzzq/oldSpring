/**
 * jquery easyui ��չ
 */

/**
 * 评查统计专用
 * @param jq datagrid
 * @param url Excel模板路径
 */
function export_TjExcel(jq,url){
	try{
		var opts = $(jq).datagrid('options');
		var selRows = $(jq).datagrid("getSelected");
		var oExcel = new ActiveXObject("Excel.Application");		
		if(url == ""){
			var oExcelDoc   = oExcel.Workbooks.Add();
		}else{
			var oExcelDoc   = oExcel.Workbooks.Open(url);
		}
	   
		var oExcelSheet = oExcelDoc.Worksheets(1);
		oExcelSheet.Range("A:O").HorizontalAlignment = -4108; 
		oExcelSheet.PageSetup.Orientation = 2;
		oExcel.Visible = true;
		var values = [];
		var indexPoint = 1;
		for (var i = 1; i < opts.columns[0].length; i++) {
			if(opts.columns[0][i].hidden || opts.columns[0][i].noExport)
        		continue ;
			oExcelSheet.Cells(1,indexPoint).Value = eval("'" + opts.columns[0][i].title + "'");
			var value = eval("'" + opts.columns[0][i].field + "'");
			values[indexPoint-1] = value;
			indexPoint++;
		}
		for (var p in selRows) {
			for (var i = 0; i < values.length; i++) {
				if(p == values[i]){
					var name = selRows[p];
					oExcelSheet.Cells(2,i+1).Value = name;
					break;
				}
			}
		}
	}catch(e){alert(e);}
}

$.extend($.fn.datagrid.methods, {
	/**
	 * ����excel
	 */
	exportExcel:function(jq,title){
		try{
			var oExcel      = new ActiveXObject("Excel.Application");
			var oExcelDoc   = oExcel.Workbooks.Add();
			var oExcelSheet = oExcelDoc.Worksheets(1);			
			oExcel.Visible = true;
			var opts = $(jq).datagrid("options");
			var rows = $(jq).datagrid('getRows');
			if(rows == null|| rows.length <= 0)
				return ;
			var columns = opts.columns[0] ;
            var column;
            oExcelSheet.Cells(1,1).Value = "序号";
            for(var i=0;i<columns.length;i++){
            	column = columns[i];
            	if(column.hidden || column.noExport)
            		continue ;
            	
            	oExcelSheet.Cells(1,i+2).Value  = column.title;
            }
            var row ;            
            for (var i=0;i<rows.length;i++) {
				row = rows[i];
				oExcelSheet.Cells(i+2,1).Value = i+1;
				for(var j=0;j<columns.length;j++){
	            	column = columns[j];
	            	if(column.hidden || column.noExport)
	            		continue ;
	            	var val = row[column.field];	            	
	            	oExcelSheet.Cells(i+2,j+2).Value  = ""+(!val?"":val);
	            }
			}
            for(var i=0;i<columns.length;i++)
            {
            	oExcelSheet.Columns(i+1).EntireColumn.AutoFit;
            }
		}catch(e){alert(e);}
	},
	exportExcel_Heng:function(jq,title){
		try{
			var oExcel      = new ActiveXObject("Excel.Application");
			var oExcelDoc   = oExcel.Workbooks.Add();
			var oExcelSheet = oExcelDoc.Worksheets(1);
			oExcelSheet.PageSetup.Orientation = 2;
			oExcel.Visible = true;
			var opts = $(jq).datagrid("options");
			var rows = $(jq).datagrid('getRows');
			if(rows == null|| rows.length <= 0)
				return ;
			var columns = opts.columns[0] ;
            var column;
            oExcelSheet.Cells(1,1).Value = "序号";
            for(var i=0;i<columns.length;i++){
            	column = columns[i];
            	if(column.hidden || column.noExport)
            		continue ;
            	
            	oExcelSheet.Cells(1,i+2).Value  = column.title;
            }
            var row ;
            for (var i=0;i<rows.length;i++) {
				row = rows[i];
				oExcelSheet.Cells(i+2,1).Value = i+1;
				for(var j=0;j<columns.length;j++){
	            	column = columns[j];
	            	if(column.hidden || column.noExport)
	            		continue ;
	            	var val = row[column.field];	
	            	if(val == 0){
	            		oExcelSheet.Cells(i+2,j+2).Value = val;
	            	}else{
	            	oExcelSheet.Cells(i+2,j+2).Value  = ""+(!val?"":val);
	            	}
	            }
			}
            for(var i=0;i<columns.length;i++)
            {
            	oExcelSheet.Columns(i+1).EntireColumn.AutoFit;
            }
		}catch(e){alert(e);}
	},
	/**
	 * ����word
	 * @param title ����
	 * @param includeRownum ���к�
	 */
	exportWord:function(jq,title,includeRownum){
		try{
			if(!includeRownum)
				includeRownum = true ; 
			var oWord    = new ActiveXObject("Word.Application");
			var oWordDoc = oWord.Documents.Add();        
			var oTables  = oWordDoc.Tables;
			oWord.Visible = true;
			
			var opts = $(jq).datagrid("options");
			var rows = $(jq).datagrid("getRows");;
			if(rows == null|| rows.length <= 0)
				return ;
			var columns = opts.columns[0] ;
			var expColumns = [];
			if(includeRownum){
				expColumns.push({title:"行号"});
			}
			var column ;
            for(var i=0;i<columns.length;i++){
            	column = columns[i];
            	if(column.hidden==true || column.noExport)
            		continue ;
            	expColumns.push(column);
            }
            var oTable   = oTables.Add(oWordDoc.Range(0,0),1,expColumns.length);
            
            var expColumn;
            for(var i=0;i<expColumns.length;i++){
            	expColumn = expColumns[i];
            	oTable.Cell(1,i+1).Range  = expColumn.title;
            }
            
            oWordDoc.Range(oWord.Selection.End,oWord.Selection.End).Select();
            
            var row ;
            for (var i=0;i<rows.length;i++) {
				row = rows[i];
				oWord.Selection.InsertRowsBelow();
				oTable.Cell(i+2,1).Range = i ;
				for(var j=0;j<expColumns.length;j++){
					expColumn = expColumns[j];
					var val = row[expColumn.field];
					oTable.Cell(i+2,j+1).Range = ""+(!val?"":val);
	            }
				break ;
			}
            
            oWord.WindowState = 1;            
            oWord.Activate();                 
            oWord.NormalTemplate.Saved = true;
		}catch(e){alert(e);}
	}
});
