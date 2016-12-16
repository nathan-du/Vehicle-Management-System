package com.nathan.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Nathan on 16/4/10.
 */
public class Client {
    byte[] buffer = new byte[200];
    static Socket sc;
    static
    {
        try
        {
            sc = new Socket("127.0.0.1", 12345);
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Client()
    {

    }

    /**
     * get true or false
     * @param type
     * @return
     */
    public boolean send(String type)
    {
        try
        {
            InputStream in = sc.getInputStream();
            OutputStream out = sc.getOutputStream();
            String content = type;//the content to be sent
            out.write(content.getBytes());//send
            int count = in.read(buffer);
            String returnContent = new String(buffer, 0, count);
            if(returnContent.equals("true"))
            {
                return true;
            }
            return false;
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * return content
     * @param type
     * @return
     */
    public String sendSearch(String type)
    {
        String returnContent = null;
        try
        {
            InputStream in = sc.getInputStream();
            OutputStream out = sc.getOutputStream();
            String content = type;// the content to be sent
            out.write(content.getBytes());//send
            int count = in.read(buffer);
            returnContent = new String(buffer, 0, count);
            return returnContent;
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        return returnContent;
    }

}
