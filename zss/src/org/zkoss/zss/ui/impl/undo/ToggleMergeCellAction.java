/* ToggleMergeCellAction.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/7/25, Created by Dennis.Chen
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zss.ui.impl.undo;


import org.zkoss.zss.api.CellOperationUtil;
import org.zkoss.zss.api.Range;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.model.Sheet;
/**
 * 
 * @author dennis
 *
 */
public class ToggleMergeCellAction extends AbstractCellDataStyleAction {
	private static final long serialVersionUID = 2517143762070769535L;

	public ToggleMergeCellAction(String label,Sheet sheet,int row, int column, int lastRow,int lastColumn){
		super(label,sheet,row,column,lastRow,lastColumn,RESERVE_ALL);
	}
	
	@Override
	protected void applyAction(){
		Range r = Ranges.range(_sheet,_row,_column,_lastRow,_lastColumn);
		CellOperationUtil.toggleMergeCenter(r);
	}
}
