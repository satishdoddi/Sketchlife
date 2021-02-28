package com.ds.sketchlife;

import android.annotation.SuppressLint;
import android.app.*;
import android.os.*;
import android.content.*;

import java.util.HashMap;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.net.Uri;
import com.ds.sketchlife.FileUtil;
import com.ds.sketchlife.SketchwareUtil;


    class Sketchlife {


        private double number = 0;
        private double fid = 0;
        private double l = 0;
        private String new_id = "";
        private String SK_Manager_Path = "";
        private String sketchware_path = "";
        private HashMap<String, Object> clo_temp = new HashMap<>();
        private String output = "";

        private ArrayList<String> temp_id = new ArrayList<>();
        private ArrayList<String> copy_list = new ArrayList<>();







        public void _AES_Library() {
        }

        private javax.crypto.SecretKey generateKey(String pwd) throws Exception {

            final java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");

            byte[] b = pwd.getBytes("UTF-8");

            digest.update(b, 0, b.length);

            byte[] key = digest.digest();

            javax.crypto.spec.SecretKeySpec sec = new javax.crypto.spec.SecretKeySpec(key, "AES");

            return sec;
        }


        public void _zip(final String _source, final String _destination) {
            FileUtil.writeFile("Don't Remove it Thanks.\nModified By: Manish Nirmal", "This Block Added for Manage Permission");
            try {
                java.util.zip.ZipOutputStream os = new java.util.zip.ZipOutputStream(new java.io.FileOutputStream(_destination));
                zip(os, _source, null);
                os.close();
            } catch (java.io.IOException e) {
            }
        }

        private void zip(java.util.zip.ZipOutputStream os, String filePath, String name) throws java.io.IOException {
            java.io.File file = new java.io.File(filePath);
            java.util.zip.ZipEntry entry = new java.util.zip.ZipEntry((name != null ? name + java.io.File.separator : "") + file.getName() + (file.isDirectory() ? java.io.File.separator : ""));
            os.putNextEntry(entry);

            if (file.isFile()) {
                java.io.InputStream is = new java.io.FileInputStream(file);
                int size = is.available();
                byte[] buff = new byte[size];
                int len = is.read(buff);
                os.write(buff, 0, len);
                return;
            }

            java.io.File[] fileArr = file.listFiles();
            for (java.io.File subFile : fileArr) {
                zip(os, subFile.getAbsolutePath(), entry.getName());
            }
        }







        private void showMessage(Context applicationContext, String toString) {

        }





    }



