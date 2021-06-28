package Compulsory;

import com.jcraft.jsch.*;

public class STFTPClient {
    private String host = "fenrir.info.uaic.ro";
    private int port = 22;
    private Session session = null;

    public STFTPClient() {
    }

    public void connect() throws JSchException{
        JSch jsch = new JSch();

        session = jsch.getSession("vlad.enia",host,port);
        session.setPassword("");


        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
    }

    public void upload(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.put(source, destination);
        sftpChannel.exit();
    }


    public void download(String source, String destination) throws JSchException, SftpException {
        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(source, destination);
        sftpChannel.exit();

    }

    public void disconnect() {
        if (session != null) {
            session.disconnect();
        }
    }
}
