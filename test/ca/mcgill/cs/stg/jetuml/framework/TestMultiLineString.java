package ca.mcgill.cs.stg.jetuml.framework;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMultiLineString
{
	@Test
	public void testConstruction()
	{
		MultiLineString string = new MultiLineString();
		assertEquals(false, string.isUnderlined());
		assertEquals(false, string.isBold());
		assertEquals(MultiLineString.CENTER, string.getJustification());
		assertEquals("", string.getText());
		
		string = new MultiLineString(true);
		assertEquals(false, string.isUnderlined());
		assertEquals(true, string.isBold());
		assertEquals(MultiLineString.CENTER, string.getJustification());
		assertEquals("", string.getText());
	}
	
	@Test
	public void testConvertToHtml1() // empty text
	{
		MultiLineString string = new MultiLineString();
		assertEquals("<html></html>", string.convertToHtml().toString());
	}
	
	@Test
	public void testConvertToHtml2() // simple one-line text
	{
		MultiLineString string = new MultiLineString();
		string.setText("Ğinterfaceğ");
		assertEquals("<html>&nbsp;Ğinterfaceğ&nbsp;</html>", string.convertToHtml().toString());
	}
	
	@Test
	public void testConvertToHtml3() // simple one-line text, bold
	{
		MultiLineString string = new MultiLineString(true);
		string.setText("Ğinterfaceğ");
		assertEquals("<html>&nbsp;<b>Ğinterfaceğ</b>&nbsp;</html>", string.convertToHtml().toString());
	}
	
	@Test
	public void testConvertToHtml4() // simple one-line text, bold, underline
	{
		MultiLineString string = new MultiLineString(true);
		string.setText("Ğinterfaceğ");
		string.setUnderlined(true);
		assertEquals("<html>&nbsp;<u><b>Ğinterfaceğ</b></u>&nbsp;</html>", string.convertToHtml().toString());
	}
	
	@Test
	public void testConvertToHtml5() // two-line text, bold, underline
	{
		MultiLineString string = new MultiLineString(true);
		string.setText("Ğinterfaceğ\nFoo");
		string.setUnderlined(true);
		assertEquals("<html>&nbsp;<u><b>Ğinterfaceğ</b></u>&nbsp;<br>&nbsp;<u><b>Foo</b></u>&nbsp;</html>", string.convertToHtml().toString());
	}
	
	@Test
	public void testConvertToHtml6() // two-line text, embedded html
	{
		MultiLineString string = new MultiLineString();
		string.setText("Ğinterfaceğ\n<b>Foo</b>");
		assertEquals("<html>&nbsp;Ğinterfaceğ&nbsp;<br>&nbsp;&lt;b&gt;Foo&lt;/b&gt;&nbsp;</html>", string.convertToHtml().toString());
	}
}
