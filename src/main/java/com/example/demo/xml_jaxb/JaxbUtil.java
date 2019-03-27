package com.example.demo.xml_jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JaxbUtil {

    private static JAXBContext jaxbContext;

    //xml转java对象
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(String xml, Class<T> c) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }

    //java对象转xml
    public static String beanToXml(Object obj) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        //Marshaller.JAXB_FRAGMENT:是否省略xml头信息,true省略，false不省略
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, new StringWriter());
        return writer.toString();
    }

}
