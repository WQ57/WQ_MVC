package com.wq.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.w3c.dom.Document;

public class XmlUtilTest {

	@Test
	public void testGetDocumnet() throws MalformedURLException,
			FileNotFoundException {
		URL pathUrl = ClassLoaderUtil.getExtendResource("WqMvc.xml");
		InputStream in = new FileInputStream(new File(pathUrl.getPath()));
		Document doc = XmlUtil.getXmlDocument(in);
	}

}
