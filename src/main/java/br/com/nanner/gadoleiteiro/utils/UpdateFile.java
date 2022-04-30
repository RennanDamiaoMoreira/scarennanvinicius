package br.com.nanner.gadoleiteiro.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UpdateFile {
    public static void update() {
        try {
            File directory, archive;
            int rename = 0;
            directory = new File("../gado-leiteiro-front/src/export/");
            directory.mkdir();
            archive = new File("../gado-leiteiro-front/src/export/ip.json");

            archive.createNewFile();

            FileWriter fw = new FileWriter(archive);
             fw.write("{ \"ip\" : \""+InetAddress.getLocalHost().getHostAddress()+"\"}");
             fw.close();


        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
