package org.zkoss.zss.issue;

import java.util.Locale;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.zkoss.zss.Setup;
import org.zkoss.zss.Util;
import org.zkoss.zss.api.Range;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.model.Book;
import org.zkoss.zss.api.model.CellData;
import org.zkoss.zss.api.model.CellData.CellType;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.model.SAutoFilter.NFilterColumn;
import org.zkoss.zss.model.SBook;
import org.zkoss.zss.model.SBooks;
import org.zkoss.zss.model.SCustomFilter;
import org.zkoss.zss.model.SCustomFilters;
import org.zkoss.zss.model.SDynamicFilter;
import org.zkoss.zss.model.SSheet;
import org.zkoss.zss.range.SRange;
import org.zkoss.zss.range.SRanges;
import org.zkoss.zss.api.Exporters;

public class Issue1226ImportExportDynamicFilterTest {
	
	@BeforeClass
	public static void setUpLibrary() throws Exception {
		Setup.touch();
	}
	
	@Before
	public void startUp() throws Exception {
		Setup.pushZssLocale(Locale.TAIWAN);
	}
	
	@After
	public void tearDown() throws Exception {
		Setup.popZssLocale();
	}

	/**
	 * Test a book with print area and export it and it should not throw exception
	 * @throws IOException 
	 */
	@Test
	public void testImportExportDynamicFilter() throws IOException {
		ByteArrayInputStream is = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try  {
			Book book = Util.loadBook(this, "book/1226-dynamic-filter.xlsx");
			Assert.assertTrue("No Exception", true);
			try {
				Exporters.getExporter("xlsx").export(book, os);
			} finally {
				os.close();
			}
			is = new ByteArrayInputStream(os.toByteArray());
			Book book2 = Util.loadBook(is, "1226-dynamic-filter.xlsx");
			Sheet sheet = book2.getSheetAt(0);
			NFilterColumn fc = sheet.getInternalSheet().getAutoFilter().getFilterColumn(0, false);
			
			SDynamicFilter cfilters = fc.getDynamicFilter();
			Assert.assertNotNull(cfilters);
			Assert.assertEquals("belowAverage", cfilters.getType());
			Assert.assertEquals(5.5, cfilters.getValue(), 0.00000000000001);
			Assert.assertNull(cfilters.getMaxValue());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue("Exception when import or export \"issue/book/1226-dynamic-filter.xlsx\":\n" + e, false);
		} finally {
			is.close();
		}
	}
}
