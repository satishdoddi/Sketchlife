package com.ds.sketchlife;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import com.ds.sketchlife.Sketchlife;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ds.sketchlife.FileUtil;
import com.ds.sketchlife.SketchwareUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class DownloadFile extends AsyncTask<String, Integer, String> {


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
        String FILE_PATH = null;

        DownloadFile(String file){
            this.FILE_PATH = file;
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            // ON DOWNLOAD START

        }
    private static void mkdirs(java.io.File outdir, String path) {
        java.io.File d = new java.io.File(outdir, path);
        if (!d.exists())
            d.mkdirs();
    }

    private static String dirpart(String name) {
        int s = name.lastIndexOf(java.io.File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }

    public void _Decrypt(final String _path) {
        try {
            javax.crypto.Cipher instance = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bytes = "sketchwaresecure".getBytes();
            instance.init(2, new javax.crypto.spec.SecretKeySpec(bytes, "AES"), new javax.crypto.spec.IvParameterSpec(bytes));
            java.io.RandomAccessFile randomAccessFile = new java.io.RandomAccessFile(_path, "r");
            byte[] bArr = new byte[((int) randomAccessFile.length())];
            randomAccessFile.readFully(bArr);
            output = new String(instance.doFinal(bArr));
        } catch (Exception e) {

        }
    }
    public void _new_id_generate() {
        FileUtil.listDir(FileUtil.getExternalStorageDir().concat("/.sketchware/mysc/list/"), temp_id);
        number = 0;
        fid = 601;
        l = temp_id.size();
        for (int i = 0; i < ((int) l); i++) {
            if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/.sketchware/mysc/list/".concat(String.valueOf((long) (fid)))))) {
                fid += 1.0d;
            } else {
                fid += 1.0d;
                l += 1.0d;
            }
            new_id = String.valueOf((long) (fid));
        }
    }

    public void _Encrypt(final String _path) {
        try {
            javax.crypto.Cipher instance = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] bytes = "sketchwaresecure".getBytes();
            instance.init(1, new javax.crypto.spec.SecretKeySpec(bytes, "AES"), new javax.crypto.spec.IvParameterSpec(bytes));
            new java.io.RandomAccessFile(_path, "rw").write(instance.doFinal(output.getBytes()));
        } catch (Exception e) {

        }
    }
        @Override
        protected String doInBackground(String[] p1){
            // BACKGROUND DOWNLOAD PROGRESS
            java.io.InputStream input = null;
            java.io.OutputStream output = null;
            java.net.HttpURLConnection connection = null;
            try {
                java.net.URL url = new java.net.URL(p1[0]);
                connection = (java.net.HttpURLConnection)url.openConnection();
                connection.connect();
                if (connection.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage();
                }
                int fileLength = connection.getContentLength();
                input = connection.getInputStream();
                output = new java.io.FileOutputStream(FILE_PATH);
                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    if (fileLength > 0)
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (java.io.IOException ignored) {
                }
                if (connection != null)
                    connection.disconnect();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            // ON DOWNLOAD SUCCESS

            _import_project(FILE_PATH);
        }

        private void _import_project(String file_path) {
            new AsyncTask<String, String, String>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }
                @SuppressLint("StaticFieldLeak")
                @Override
                protected String doInBackground(String... arg0) {
                    try {
                        String _path = "";
                        if (_path.endsWith(".zip")) {
                            _UnZip(_path, SK_Manager_Path.concat("/zips/"));
                            if (FileUtil.isExistFile(SK_Manager_Path.concat("zips/files/mysc/list/project"))) {
                                _new_id_generate();
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("mysc/list/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("resources/fonts/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("resources/icons/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("resources/images/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("resources/sounds/".concat(new_id)));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/resource/layout/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/resource/values/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/assets/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/java/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/arm64-v8a/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/armeabi/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/armeabi-v7a/"))));
                                FileUtil.makeDir(sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/x86/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/data/"), sketchware_path.concat("data/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/mysc/list/"), sketchware_path.concat("mysc/list/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/fonts/"), sketchware_path.concat("resources/fonts/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/icons/"), sketchware_path.concat("resources/icons/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/images/"), sketchware_path.concat("resources/images/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/sounds/"), sketchware_path.concat("resources/sounds/".concat(new_id)));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/layout/"), sketchware_path.concat("data/".concat(new_id.concat("/files/resource/layout/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/resources/values/"), sketchware_path.concat("data/".concat(new_id.concat("/files/resource/values/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/assets/"), sketchware_path.concat("data/".concat(new_id.concat("/files/assets/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/java/"), sketchware_path.concat("data/".concat(new_id.concat("/files/java/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/arm64-v8a/"), sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/arm64-v8a/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/armeabi/"), sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/armeabi/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/armeabi-v7a/"), sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/armeabi-v7a/"))));
                                _Copy(SK_Manager_Path.concat("zips/files/x86/"), sketchware_path.concat("data/".concat(new_id.concat("/files/native_libs/x86/"))));
                                _Decrypt(sketchware_path.concat("mysc/list/".concat(new_id.concat("/project"))));
                                clo_temp = new HashMap<>();
                                clo_temp = new Gson().fromJson(output, new TypeToken<HashMap<String, Object>>(){}.getType());
                                clo_temp.put("sc_id", new_id);
                                output = new Gson().toJson(clo_temp);
                                _Encrypt(sketchware_path.concat("mysc/list/".concat(new_id.concat("/project"))));
                            }
                            else {
                                FileUtil.deleteFile(SK_Manager_Path.concat("zips/files"));

                            }
                        }
                        else {

                        }
                    } catch(Exception e) {}
                    return null;
                }


                @Override
                protected void onPostExecute(String result) {
                    super.onPostExecute(result);
                    //
                    //

                    FileUtil.deleteFile(SK_Manager_Path.concat("zips/files"));
                }
            }.execute();
        }


    public void _dirs() {
        SK_Manager_Path = FileUtil.getExternalStorageDir().concat("/.Sketchlife/");
        sketchware_path = FileUtil.getExternalStorageDir().concat("/.sketchware/");
        if (!FileUtil.isExistFile(SK_Manager_Path)) {
            FileUtil.makeDir(SK_Manager_Path);
        }
    }
    public void _Copy(final String _F, final String _T) {
        FileUtil.makeDir(_T.concat("/"));
        copy_list.clear();
        FileUtil.listDir(_F.concat("/"), copy_list);
        double number = 0;
        for (int _repeat11 = 0; _repeat11 < (int) (copy_list.size()); _repeat11++) {
            if (FileUtil.isFile(copy_list.get((int) (number)))) {
                FileUtil.copyFile(copy_list.get((int) (number)), _T.concat("/".concat(Uri.parse(copy_list.get((int) (number))).getLastPathSegment())));
            }
            number++;
        }
    }
    private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException {
        byte[] buffer = new byte[4096];
        java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
        int count = -1;
        while ((count = in.read(buffer)) != -1)
            out.write(buffer, 0, count);
        out.close();
    }


    public void _UnZip(final String _fileZip, final String _destDir) {
        try {
            java.io.File outdir = new java.io.File(_destDir);
            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
            java.util.zip.ZipEntry entry;
            String name, dir;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                if (entry.isDirectory()) {
                    mkdirs(outdir, name);
                    continue;
                }

                /* this part is necessary because file entry can come before
                 * directory entry where is file located
                 * i.e.:
                 * /foo/foo.txt
                 * /foo/
                 */

                dir = dirpart(name);
                if (dir != null)
                    mkdirs(outdir, dir);

                extractFile(zin, outdir, name);
            }
            zin.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

}

