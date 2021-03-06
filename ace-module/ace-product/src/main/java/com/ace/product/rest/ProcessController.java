package com.ace.product.rest;

import com.ace.auth.client.annotation.IgnoreUserToken;
import com.ace.common.msg.ObjectRestResponse;
import com.ace.common.msg.TableResultResponse;
import com.ace.common.rest.BaseController;
import com.ace.common.util.Query;
import com.ace.product.biz.ProcessBiz;
import com.ace.product.biz.UpLoadBiz;
import com.ace.product.entity.Process;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by CDZ on 2018/11/15.
 */
@RestController
@RequestMapping("process")
public class ProcessController extends BaseController<ProcessBiz,Process> {
    @Autowired
    ProcessBiz processBiz;

    @Autowired
    UpLoadBiz upLoadBiz;

    @Value("${ftp.fileName}")
    private String fileName;


    /**
     * 工艺明细
     * @param id
     * @return
     */
    @RequestMapping(value = "/getProcess/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse getProcess(@PathVariable int id){
        Process process = baseBiz.selectById(id);
        return baseBiz.get(process);
    }

    /**
     * 查看历史版本
     * @param
     * @return
     */
    @GetMapping("/historyVersion/page")
    public TableResultResponse<Map<String,Object>> historyVersion(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        Process process = new Process();
        if(params.get("u9Coding") != null && !params.get("u9Coding").equals("")){
            process.setU9Coding(params.get("u9Coding").toString());
        }
        return baseBiz.historyVersion(query,process);
    }

    /**
     * 工艺信息查询（仅列出最新版本的列表）
     * @param params
     * @return
     */
    @GetMapping("/maxVersion/page")
    public TableResultResponse<Map<String,Object>> selectProcessForMaxVersion(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        Process process = new Process();
        if(params.get("customer") != null && !params.get("customer").equals("")){
            process.setCustomer(params.get("customer").toString());
        }if(params.get("u9Coding") != null && !params.get("u9Coding").equals("")){
            process.setU9Coding(params.get("u9Coding").toString());
        }if(params.get("status") != null && !params.get("status").equals("")){
            process.setStatus(Integer.valueOf(params.get("status").toString()));
        }
        return processBiz.selectProcessForMaxVersion(query,process);
    }

    @GetMapping("/historyProcess/page")
    public TableResultResponse<Map<String,Object>> selectProcessn(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        Process process = new Process();
        if(params.get("customer") != null && !params.get("customer").equals("")){
            process.setCustomer(params.get("customer").toString());
        }if(params.get("u9Coding") != null && !params.get("u9Coding").equals("")){
            process.setU9Coding(params.get("u9Coding").toString());
        }if(params.get("version") != null && !params.get("version").equals("")){
            process.setStatus(Integer.valueOf(params.get("version").toString()));
        }
        return processBiz.selectProcess(query,process);
    }

    /**
     * 工艺信息编码模糊查询
     * @param
     * @return
     */
    @GetMapping("/u9Conding")
    public ObjectRestResponse selectProcessU9Conding(){
       return processBiz.selectProcessU9Conding();
    }

    /**
     * 新增u9编码模糊查询
     * @return
     */
    @GetMapping("/saveU9Conding")
    public ObjectRestResponse saveProcessU9Conding(){
        return processBiz.saveProcessU9Conding();
    }

    /**
     * 手动新增工艺信息
     * @param process
     * @return
     */
    @PostMapping("/save")
    @Transactional
    @ResponseBody
    public ObjectRestResponse saveProcess(@RequestBody Process process){
        return baseBiz.saveProcess(process);
    }

    /**
     * 手动编辑工艺信息
     * @param process
     * @return
     */
    @PutMapping("/update/{id}")
    @Transactional
    @ResponseBody
    public ObjectRestResponse updateProcess(@RequestBody Process process,@PathVariable Integer id){
        Boolean bool = baseBiz.updateProcess(process,id);
        if(!bool){
            process.setId(id);
            baseBiz.updateById(process);
        }
        return new ObjectRestResponse<Process>();
    }

    /**
     *
     *
     * 前端格式
     {
        "list":[
                    {
                        "id":"1",
                        "status":0
                    },
                   {
                        "id":"2",
                        "status":0
                    }
              ]

     }
     批量作废
     * @param
     * @return
     */
    @PutMapping("/updateInvalid")
    @ResponseBody
    @Transactional
    public ObjectRestResponse updateInvalid(@RequestBody Map<String,List> map) {
        return processBiz.updateInvalid(map);
    }

    /**
     * 恢复上一个版本
     * @param id
     * @return
     */
    @PutMapping("/updateRegain/{id}")
    @Transactional
    @ResponseBody
    public ObjectRestResponse updateRegain(@PathVariable("id") Integer id) {
        Process proces = baseBiz.selectById(id);
        return processBiz.updateRegain(proces);
    }



    /**
     * Excel 导入
     * @return
     */
    @PostMapping("/excelInport")
    @ResponseBody
    @Transactional
    public ObjectRestResponse ExcelInport(MultipartFile myFile) throws IOException, InvalidFormatException {
        System.out.println(myFile.getInputStream());
//        String path = "D:\\工艺信息导入模板.xlsx";
        return processBiz.ExcelInport(myFile.getOriginalFilename());
    }

    // ftp下载模板
    @RequestMapping(value="/ftpDownload", method = RequestMethod.GET)
    @Transactional
    @IgnoreUserToken
    public void Ftpdownload( HttpServletResponse response) throws IOException {
        InputStream inputStream = upLoadBiz.Ftpdownload(new String(fileName.getBytes("ISO8859-1"),"UTF-8"));
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition", "attachment;fileName=" +   fileName );   // 设置文件名
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
    }

    //ftp处理文件上传
    @RequestMapping(value="/ftpUploadImg", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody boolean uploadImg(@RequestParam("myfile") MultipartFile myFile) throws IOException {
        System.out.println(myFile.getOriginalFilename());
        System.out.println(myFile.getName());
        String filePath="D:\\马.jpg";
        // TODO Auto-generated method stub
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       return upLoadBiz.upLoadImg("马.jpg",inputStream);
    }

}
