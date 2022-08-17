package com.helloworldcoin.util;

import java.io.*;

/**
 *
 * @author x.king xdotking@gmail.com
 */
public class FileUtil {

    public static String newPath(String parent, String child) {
        return new File(parent,child).getAbsolutePath();
    }

    public static void makeDirectory(String path) {
        File file = new File(path);
        if(file.exists()){
            return;
        }
        boolean isMakeDirectorySuccess = file.mkdirs();
        if(!isMakeDirectorySuccess){
            SystemUtil.errorExit("create directory failed. path is "+path + ".",null);
        }
    }

    public static void deleteDirectory(String path) {
        File file = new File(path);
        if(file.isDirectory()){
            File[] childrenFiles = file.listFiles();
            if(childrenFiles != null){
                for (File childFile:childrenFiles){
                    deleteDirectory(childFile.getAbsolutePath());
                }
            }
        }
        boolean isDeleteDirectorySuccess = file.delete();
        if(!isDeleteDirectorySuccess){
            SystemUtil.errorExit("delete directory failed. path is "+path + ".",null);
        }
    }

    public static String read(String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            String text = "";
            String line;
            while((line = br.readLine()) != null){
                //don't append newline symbol ('\r'、'\n'、'\r\n' etc).
                text += line;
            }
            return text;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
