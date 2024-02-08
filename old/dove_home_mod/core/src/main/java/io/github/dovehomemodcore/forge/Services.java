package io.github.dovehomemodcore.forge;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.api.IncompatibleEnvironmentException;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Services implements ITransformationService {

    public static final List<String> modids = new ArrayList<>();
    @Override
    public @NotNull String name() {
        return "dovehomemodcore";
    }

    @Override
    public void initialize(IEnvironment environment) {
        File file = new File(System.getProperty("user.dir"), "mods");
        String[] strings = {
                "https://cdn.modrinth.com/data/F56tBubk/versions/nuxhlsqL/expandedstorage-8.3.4+1.19.2-forge.jar"
        };
        downloads(file, strings);

    }

    public static void downloads(File dir, String... urls)  {
        for (String url : urls) {
            try {
                download(dir, url);
            } catch (Exception ignored) {}
        }
    }

    public static void download(File dir, String url) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.connect();
        int contentLength = conn.getContentLength();
        System.out.println("file lenght ----->" + contentLength);
        BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
        String[] split = url.split("/");
        File file = new File(dir, split[split.length - 1]);
        if (!file.exists()) {
            try (OutputStream out = new FileOutputStream(file)) {
                int size;
                int len = 0;
                byte[] buf = new byte[1024];
                while ((size = bin.read(buf)) != -1) {
                    len += size;
                    out.write(buf, 0, size);
                    System.out.println(MessageFormat.format("download{0}%", (len / contentLength) * 100));
                }
                out.flush();
            }
        }
        bin.close();
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices) throws IncompatibleEnvironmentException {

    }

    @Override
    public @NotNull List<ITransformer> transformers() {
        return List.of(new Core());
    }
}
