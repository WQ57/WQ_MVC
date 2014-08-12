package com.wq.common.util;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.wq.web.servlet.exception.WqMvcException;

/**
 * Xml工具类.
 * 
 * @author qingwu
 * @date 2014-8-7 下午3:48:53
 */
public class XmlUtil {

	/**
	 * 获得xml文档对象.
	 * 
	 * @param protocolXML
	 *            文档位置
	 * @return
	 * @author qingwu
	 * @date 2014-8-7 下午3:56:06
	 */
	public static Document getXmlDocument(InputStream in) {
		try {
			DocumentBuilder builder;
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			return builder.parse(in);
		} catch (ParserConfigurationException e) {
			throw new WqMvcException(e);
		} catch (SAXException e) {
			throw new WqMvcException(e);
		} catch (IOException e) {
			throw new WqMvcException(e);
		}
	}

}
