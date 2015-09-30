package com.jops.dbo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jcraft.jsch.UserInfo;
import com.jops.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by MKowynia on 9/28/15.
 */
@Document(collection = "node")
public class Server {

    @Id
    public String id;
    public String name;
    public List<String> roles;
    public String host;
    public String ip;
    public int port;
    public String environment;
    public List<String> packages;
    public String username;
    public String password;

    @Override
    public String toString() {
        return StringUtil.objectToJsonStr(this);
    }
}
