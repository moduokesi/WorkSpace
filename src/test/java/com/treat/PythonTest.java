package com.treat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class PythonTest {

    @Test
    public String Diagnose(String message) {
        message = "你好";
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\diagnose.py";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript);
        processBuilder.redirectErrorStream(true); // 重定向错误流到输入流

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            OutputStream outputStream = process.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));

            // 将参数作为JSON字符串写入标准输入
            String inputJson = message;
            // 将消息文本编码为UTF-8并写入

            byte[] inputBytes = inputJson.getBytes(StandardCharsets.UTF_8);
            outputStream.write(inputBytes);
            outputStream.close();

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            String result = output.toString();
            System.out.println(result);

            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test
    public void demo2(){
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\separate_oragans_stl.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + "1234567890" + "\\outfile\\dataset\\" + "0b2be9e0-886b-4144-99f0-8bb4c6eaa848.nii.gz";
        String saveDir = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + "1234567890" + "\\outfile\\output\\";

        // 构建命令行参数
        String[] command = new String[]{"python", pythonScript, "--image_path", imageFilePath, "--save_dir", saveDir};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // 读取Python脚本的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo3() throws IOException, InterruptedException {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\multi_organs_volume_measurement.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + "1234567890" + "\\outfile\\dataset\\" + "1d2eb6ec-dd72-4926-ab9e-df9f58837b0a.nii.gz";

        // 构建命令行参数
        String[] command = new String[]{"python", pythonScript, "--image_path", imageFilePath};

        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();

            // 读取Python脚本的输出
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void demo4() throws IOException, InterruptedException {
        String pythonScript = "D:\\Workspaces\\Project\\treatpython\\multi_organs_surface_measurement.py";
        String imageFilePath = "D:\\Workspaces\\Project\\treattest\\treatdata\\" + "1234567890" + "\\outfile\\dataset\\" + "1f88054e-698a-4dd7-855a-11b44b1f0474.nii.gz";

        // 构建命令行参数
        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScript, "--image_path", imageFilePath);
        processBuilder.redirectErrorStream(true); // Redirect error stream to input stream

        try {
            Process process = processBuilder.start();

            // 获取输出结果
            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("Python脚本执行完毕，退出码：" + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
