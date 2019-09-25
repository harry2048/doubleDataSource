package com.example.demo.service.impl;

import com.example.demo.mysqlMapper.MysqlMapper;
import com.example.demo.oracleMapper.OracleMapper;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private MysqlMapper mysqlMapper;

    @Autowired
    private OracleMapper oracleMapper;

    @Transactional(transactionManager = "mysqlTransactionManager")
    @Override
    public Map<String, Object> insertMysql(String name, Integer age) {
        int count = mysqlMapper.insertMysql(name, age);
        Map<String, Object> result = new HashMap<>();
        if (count < 0) {
            result.put("msg", "新增失败");
            return result;
        }
        result.put("msg", "mysql新增成功");
        return result;
    }

    @Transactional(transactionManager = "oracleTransactionManager")
    @Override
    public Map<String, Object> insertOracle(String name, Integer age) {
        int count = oracleMapper.insertOracle(name, age);
        Map<String, Object> result = new HashMap<>();
        if (count < 0) {
            result.put("msg", "新增失败");
            return result;
        }
        result.put("msg", "新增成功");
        return result;
    }

    @Override
//    @Transactional(transactionManager="mysqlTransactionManager")
    @Transactional(transactionManager = "oracleTransactionManager")
    public Map<String, Object> insertMysqlAndOrcl(String name, Integer age) {
        insertMysql(name, age);
        int count = oracleMapper.insertOracle(name, age);
        int x = 1 / 0;
        Map<String, Object> result = new HashMap<>();
        result.put("msg", count);
//        result.put("msg", count+count2);
        return result;
    }

//    @Override
//    public Map<String, Object> testQuery(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "test.txt";
//        String path = "D:\\"+fileName;
//        File file = new File(path );
//        if (!file.exists()) {
//            writeList(file);
//        }else {
//            file.delete();
//            writeList(file);
//        }
//        long start = System.currentTimeMillis();
//        downloadList(path,fileName,response);
//        long end = System.currentTimeMillis();
//        System.out.println(end-start);
//        return null;
//    }
    //    private void downloadList(String path, String fileName, HttpServletResponse response) {
//        try {
//            // 下载
//            File file = new File("D:\\迅雷下载\\JDK8.zip");
//            response.setContentType("text/html;charset=utf-8");
//            response.setHeader("content-disposition","attachment;filename="+fileName);
//
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream("D:\\迅雷下载\\JDK8.zip"));
//            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
//            byte[] bytes = new byte[(int) file.length()];
//            while (in.read(bytes, 0, bytes.length)!=-1) {
//                out.write(bytes,0,bytes.length);
//            }
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Map<String, Object> testQuery(HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "test.txt";
        String fileName = "test.txt";

        long start = System.currentTimeMillis();
        downloadList(fileName, response);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return null;
    }

    private void downloadList(String fileName, HttpServletResponse response) {
        try {
            // 下载
            response.setContentType("text/plain; charset=utf-8");  //定义输出类型，为txt文件。
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            ServletOutputStream out2 = response.getOutputStream();
//            BufferedOutputStream writer = new BufferedOutputStream(out);
            PrintWriter out = new PrintWriter(out2);
            List<Map<String, Object>> list = mysqlMapper.testQuery();
            StringBuilder sb = new StringBuilder();
            sb.append(3 + "\r");
            for (Map<String, Object> map : list) {
                Iterator<Object> iterator = map.values().iterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next());
                }
                sb.append("\r");
            }
            out.print(sb.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void writeList(File file) {
        try {
            List<Map<String, Object>> list = mysqlMapper.testQuery();
            file.createNewFile();
            Writer out = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(out);
            for (Map<String, Object> map : list) {
                Iterator<Object> iterator = map.values().iterator();
                while (iterator.hasNext()) {
                    bw.write(iterator.next().toString());
                }
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
