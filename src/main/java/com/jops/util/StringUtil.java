package com.jops.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by MKowynia on 9/28/15.
 */
public class StringUtil {
    public static String objectToJsonStr(Object obj) {
        try {
            ObjectWriter ow = new ObjectMapper().writer();
            return ow.writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}
