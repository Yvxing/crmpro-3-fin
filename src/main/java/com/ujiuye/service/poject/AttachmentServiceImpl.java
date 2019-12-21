package com.ujiuye.service.poject;

import com.ujiuye.dao.AttachmentMapper;
import com.ujiuye.dao.CustomerMapper;
import com.ujiuye.dao.ProjectMapper;
import com.ujiuye.pojo.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerMapper customerMapper;


    /**
     * 附件的列表展示
     * @return
     */
    public List<Attachment> getAllAttachment() {
        List<Attachment> attachments = attachmentMapper.selectByExample(null);
        for (Attachment at: attachments) {
            //遍历附件集合，根据外键给其相应的project对象的相应信息
            ProjectExample projectExample = new ProjectExample();
            projectExample.createCriteria().andPidEqualTo(at.getProFk());
            List<Project> projects = projectMapper.selectByExample(projectExample);

            at.setProject(projects.get(0));
        }
        return attachments;
    }

    /**
     * 找到当前选中的选项所需要的信息
     * 三表联查
     * @param id
     * @return
     */
    public Attachment getOne(int id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);

        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andPidEqualTo(attachment.getProFk());
        List<Project> projects = projectMapper.selectByExample(projectExample);
        for (Project p:projects) {
            CustomerExample customerExample = new CustomerExample();
            customerExample.createCriteria().andIdEqualTo(p.getComname());
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            p.setCustomer(customers.get(0));
        }
        attachment.setProject(projects.get(0));

        return attachment;
    }

    /**
     * 删除
     * @param ids
     */
    public void del(String ids) {
        String[] split = ids.split(",");
        for (String s: split) {
            attachmentMapper.deleteByPrimaryKey(Integer.parseInt(s));
            Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(s));
            File file = new File(attachment.getPath());
            if (file.exists()){
                file.delete();
            }
        }
    }

    /**
     * 去修改，准备
     * @param id
     */
    public Attachment toupdateAtt(int id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        Project project = projectMapper.selectByPrimaryKey(attachment.getProFk());
        attachment.setProject(project);
        return attachment;
    }


    /**
     * 添加
     * @param attachment
     */
    public void addAttachment(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }

    /**
     * 导出
     * @param ids
     */
    public void daochu(String ids) {
        // 创建工作薄
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = hwb.createSheet("attachment");
        // 获取数据库需要导出的数据，获取数据库对应表的字段
        // 获取字段
        List<String> list = projectMapper.getSheetName("attachment");       //要导出的数据库中的表
        // 获取要导出的数据
        String[] split = ids.split(",");
        List<Map<String,String>> mlist = new ArrayList<Map<String,String>>();


        for(int i = 0; i < split.length; i++){
            Map<String,String> map = new HashMap<String, String>();
            Attachment attachment = attachmentMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
            map.put("id",attachment.getId()+"");
            map.put("pro_fk",attachment.getProFk()+"");
            map.put("attname",attachment.getAttname());
            map.put("attdis",attachment.getAttdis());
            map.put("remark",attachment.getRemark());
            map.put("path",attachment.getPath());
            mlist.add(map);
        }

        // 导出标题头
        HSSFRow row1 = sheet.createRow(0);
        for(int x = 0; x < list.size(); x++){
            HSSFCell cell1 = row1.createCell(x);
            cell1.setCellValue(list.get(x));
        }
        // 把数据添加到excel
        for(int i = 1; i <= mlist.size(); i++){ // 获取数据
            // 创建行
            HSSFRow row = sheet.createRow(i);
            for(int j = 0; j < list.size(); j++){  // 获取字段
                // 创建列
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(mlist.get(i-1).get(list.get(j)));
            }
        }
        // 把excel保存到指定的位置
        try {
            //文件若有需要，则需要修改
            String name = "attachment.xls";
            String path = "e://AttachmenFile/";
            File file1 = new File(path);
            //父级目录不存在则先创建
            if (!file1.exists()){
                file1.mkdirs();
            }
            File file = new File(path+name);
            hwb.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 保存修改状态
     * @param attachment
     */
    public void update(Attachment attachment) {
        attachmentMapper.updateByPrimaryKeySelective(attachment);
    }

    /**
     * 文件路径的更新
     * @param id
     */
    public void updatePath(int id) {
        Attachment attachment = attachmentMapper.selectByPrimaryKey(id);
        attachment.setPath("文件已移除");
        attachmentMapper.updateByPrimaryKey(attachment);
    }
}
