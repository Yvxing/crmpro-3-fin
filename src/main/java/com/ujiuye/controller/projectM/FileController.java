package com.ujiuye.controller.projectM;

import com.ujiuye.pojo.Attachment;
import com.ujiuye.service.poject.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/attachment")
public class FileController {
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 显示附件列表
     * @return
     */
    @RequestMapping("/showAllAttachment")
    @ResponseBody
    public List<Attachment> showAllAttachment(){
        return attachmentService.getAllAttachment();
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @RequestMapping("/look")
    public ModelAndView getOneToPage(int id){
        ModelAndView mv = new ModelAndView();
        Attachment one = attachmentService.getOne(id);

        mv.addObject("item",one);
        mv.setViewName("/project-file-look.jsp");
        return mv;
    }

    /**
     * 删除,若能够找到对应文件则删除
     * @param ids
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/delbyIds")
    public void  deldeldel (String ids, HttpServletResponse resp) throws IOException {
        if (ids.equals("")||ids==null){
            return;
        }
        attachmentService.del(ids);
        resp.getWriter().print(true);
    }

    /**
     * 附件添加
     * @param file
     * @param attachment
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping("/addatt")
    public String addAtt(MultipartFile file,Attachment attachment, HttpServletResponse resp) throws IOException {
        //文件处理
        String originalFilename = file.getOriginalFilename();       //获得文件名
        String path = "e://attachment/"+originalFilename;
        File file1 = new File(path);                    //设置上传的文件的保存路径
        if (!file1.exists()){             //文件路径非空创建
            file1.mkdirs();
        }
        file.transferTo(file1);     //文件上传到指定路径

        attachment.setPath(path);

        attachmentService.addAttachment(attachment);
        return "/project-file.jsp";
    }


    /**
     * 导出excel
     * @param ids
     * @param resp
     */
    @RequestMapping("/daochu")
    public void daochu(String ids, HttpServletResponse resp) throws IOException {
        attachmentService.daochu(ids);
        resp.getWriter().print(true);
    }


    /**
     * 去到编辑附件
     */
    @RequestMapping("/toupdate")
    @ResponseBody
    public ModelAndView update(int id){
        ModelAndView mv = new ModelAndView();
        Attachment attachment = attachmentService.toupdateAtt(id);
        mv.addObject("item",attachment);
        mv.setViewName("/project-file-edit.jsp");
        return mv;
    }


    /**
     * 修改文件路径
     * @param id
     * @param resp
     * @return
     */
    @RequestMapping("/delpath")
    @ResponseBody
    public ModelAndView delpath(int id,HttpServletResponse resp){
        ModelAndView mv = new ModelAndView();
        attachmentService.updatePath(id);
        Attachment attachment = attachmentService.toupdateAtt(id);
        mv.addObject("item",attachment);
        mv.setViewName("/project-file-edit.jsp");
        return mv;
    }

    /**
     * 更新
     * @param attachment
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping("/update")
    public String update(Attachment attachment,MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String path = "e://AttachmentFile/"+originalFilename;
        File file1 = new File(path);
        if (!file1.exists()){
            file1.mkdirs();
        }
        file.transferTo(file1);
        //获取文件的名称，书写路径，判断是否有文件的父级目录，没有就创建,转存文件，更新文件路径和其他信息

        attachment.setPath(path);

        attachmentService.update(attachment);
        return "/project-file.jsp";
    }

    /**
     * 文件下载
     * @param path
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(String path, HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String xzpath = req.getSession().getServletContext().getRealPath(path);     //服务器下的已上传文件的路径

        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));//此处的文件路径中设置为了本地，实际应该使用xzpath
        path = URLEncoder.encode(path,"utf-8");             //文件名的转码，避免乱码

        resp.addHeader("Content-Disposition","attachment;path="+path);          //设置文件下载头

        resp.setContentType("multipart/form-data");             //自动判断下载的文件的类型

        //输入输出缓冲流的下载
        BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());

        int len=0;
        while ((len = bis.read())!=-1){
            bos.write(len);
        }
        bos.close();
        bis.close();
    }


}
