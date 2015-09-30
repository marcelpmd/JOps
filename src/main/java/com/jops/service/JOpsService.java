package com.jops.service;

import com.jcraft.jsch.*;
import com.jops.dbo.Server;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by MKowynia on 9/28/15.
 */
@Service
public class JOpsService {

    private Logger log = LoggerFactory.getLogger(JOpsService.class);

    List<Server> servers;
    Session session = null;


    public JOpsService(){}

    public JOpsService(List<Server> servers){
        this.servers = servers;

    }


    public List<String> listAvailablePackageVersions(String pkgName, String repo){
        String command = "yum list available --showduplicates --disablerepo='*' --enablerepo="+pkgName+ "| grep -i "+repo+"| awk '{ print $2; }'";
        return runLocalCommand(command);
    }

    public List<String> testCommand(Server server){
        connectToServer(server);
        String repo_name = "hnav-agnostic";
        String pkg_name = "TWC-OPS-CONSOLE";
        String command = "yum list available --showduplicates --disablerepo='*' --enablerepo="+repo_name+ "| grep -i "+pkg_name+"| awk '{ print $2; }'";
        return runRemoteCommand(command);

    }

    public List<String> run(String command){
        List<String> consoleOutput = null;
        for(Server server : servers){
            connectToServer(server);
            consoleOutput.addAll(runRemoteCommand(command));
        }
        return consoleOutput;
    }

    public List<String> run(Server server, String command){
        connectToServer(server);
        return runRemoteCommand(command);
    }

    public void console(Server server){
        connectToServer(server);
        runRemoteShell();

    }

    private List<String> runLocalCommand(String command){
        Runtime r = Runtime.getRuntime();
        Process p = null;
        List<String> consoleLines = null;
        InputStream in = null;
        try {
            p = r.exec(command);
            p.waitFor();
            consoleLines = IOUtils.readLines(p.getInputStream());
            System.out.println(consoleLines);


        } catch (Exception e) {
            log.error("Failed to run the following command: {}", command, e);
        }
        finally {
            IOUtils.closeQuietly(in);
        }
        return consoleLines;
    }


    private List<String> runRemoteCommand(String command){
        List<String> consoleLines = null;
        InputStream in = null;
        Channel channel = null;
        try{
            channel= session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);
            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);
            in = channel.getInputStream();
            channel.connect();
            while(true){
                consoleLines = IOUtils.readLines(in);
                if(channel.isClosed()){
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee){
                }
            }
            System.out.println(consoleLines);

        }
        catch(Exception e){
            log.error("Failed to run the following command: {}", command, e);
        }finally {
            IOUtils.closeQuietly(in);
            channel.disconnect();
            session.disconnect();
        }
        return consoleLines;
    }
    private void connectToServer(String username, String password, String ip){
        Server server = new Server();
        server.username = username;
        server.password = password;
        server.ip = ip;
        connectToServer(server);
    }

    private void runRemoteShell(){
        Channel channel = null;
        try{
            channel= session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out, true);
            InputStream in=channel.getInputStream();
            channel.connect(30000);
            byte[] tmp=new byte[1024];
            while(true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                }
            }
        }
        catch(Exception e){
            log.error("Unable to start a console session ... ", e);
        }finally {

            channel.disconnect();
            session.disconnect();
        }
    }

    private void connectToServer(Server server){
        try{
            JSch jsch=new JSch();
            session = jsch.getSession(server.username,server.ip);
            session.setPassword(server.password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(30000);

        }catch (Exception e){
            log.error("Connection to server failed",e);
        }

    }

    public class MyUserInfo
            implements UserInfo, UIKeyboardInteractive {
        public String getPassword(){ return "mystro"; }
        public boolean promptYesNo(String str){ return false; }
        public String getPassphrase(){ return null; }
        public boolean promptPassphrase(String message){ return false; }
        public boolean promptPassword(String message){ return false; }
        public void showMessage(String message){ }
        public String[] promptKeyboardInteractive(String destination,
                                                  String name,
                                                  String instruction,
                                                  String[] prompt,
                                                  boolean[] echo){
            return null;
        }
    }

}
