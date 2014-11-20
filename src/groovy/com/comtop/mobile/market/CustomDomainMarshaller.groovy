package com.comtop.mobile.utils;

import grails.converters.JSON;
import org.codehaus.groovy.grails.web.converters.ConverterUtil;
import org.codehaus.groovy.grails.web.converters.exceptions.ConverterException;
import org.codehaus.groovy.grails.web.converters.marshaller.ObjectMarshaller;
import org.codehaus.groovy.grails.web.json.JSONWriter;
import org.springframework.beans.BeanUtils;

/**
 * Created by zhaoqunqi on 2014/11/20.
 */
public class CustomDomainMarshaller implements ObjectMarshaller<JSON> {
    static EXCLUDED=['metaClass','class','version']

    public boolean supports(Object object) {
        return false
    }

    public void marshalObject(Object o, JSON json) throws ConverterException {
        JSONWriter writer = json.getWriter();
        try {
            writer.object();
            def properties = BeanUtils.getPropertyDescriptors(o.getClass());
            println o.getClass().getName()
            for (property in properties) {
                String name = property.getName();
                println " ss  ${name}"
                if (!EXCLUDED.contains(name)) {
                    def readMethod = property.getReadMethod();
                    if (readMethod != null) {
                        def value = readMethod.invoke(o, (Object[]) null);
                        writer.key(name);
                        json.convertAnother(value);
                    }
                }
            }
            writer.endObject();
        } catch (Exception e) {
            throw new ConverterException("Exception in CustomDomainMarshaller", e);
        }
    }
}
