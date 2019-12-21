package com.ujiuye.service.poject;

import com.ujiuye.dao.AnalysisMapper;
import com.ujiuye.dao.AttachmentMapper;
import com.ujiuye.dao.ProjectMapper;
import com.ujiuye.pojo.Analysis;
import com.ujiuye.pojo.AnalysisExample;
import com.ujiuye.pojo.Project;
import com.ujiuye.pojo.ProjectExample;
import com.ujiuye.utils.AnalysisBean;
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
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;
    @Autowired
    private ProjectMapper projectMapper;


    /**
     * 获取全部信息
     * @return
     */
    public List<AnalysisBean> getAll() {
        List<AnalysisBean> lists = new ArrayList<AnalysisBean>();
        List<Analysis> analyses = analysisMapper.selectByExample(null);
        for (Analysis a: analyses) {
            AnalysisBean ab = new AnalysisBean();
            ab.setAnalysis(a);
            ab.setProject(projectMapper.selectByPrimaryKey(a.getId()));
            lists.add(ab);
        }

        return lists;
    }

    /**
     * 添加分析类
     * @param analysis
     */
    public void addAnalysis(Analysis analysis) {
        analysisMapper.insertSelective(analysis);
    }

    /**
     * 根据id查询analysis
     * @param id
     * @return
     */
    public Analysis getAnalysisById(int id) {
        Analysis analysis = analysisMapper.selectByPrimaryKey(id);
        return analysis;
    }

    /**
     * 更新数据
     * @param analysis'
     * @return
     */
    public void updateAnlisysById(Analysis analysis) {
        analysisMapper.updateByPrimaryKeySelective(analysis);
    }

    /**
     * 删除
     * @param ids
     */
    public void delById(String ids) {
        String[] split = ids.split(",");
        for (String s: split) {
            analysisMapper.deleteByPrimaryKey(Integer.parseInt(s));
        }
    }

    /**
     * 搜索
     * @param cid
     * @param keyword
     * @return
     */
    public List<AnalysisBean> search(String cid, String keyword) {
        List<AnalysisBean> lists = new ArrayList<AnalysisBean>();
        if (cid !=null && keyword!=null){
            if (cid=="1"){
                //选择1  为项目名称
                ProjectExample projectExample = new ProjectExample();
                projectExample.createCriteria().andPnameEqualTo(keyword);

                List<Project> projects = projectMapper.selectByExample(projectExample);
                AnalysisExample analysisExample = new AnalysisExample();
                AnalysisExample.Criteria criteria = analysisExample.createCriteria();
                for (Project p: projects) {
                    criteria.andIdEqualTo(p.getPid());
                    List<Analysis> analyses = analysisMapper.selectByExample(analysisExample);
                    for (Analysis a:analyses) {
                        AnalysisBean ab = new AnalysisBean();
                        ab.setProject(p);
                        ab.setAnalysis(a);
                        lists.add(ab);
                    }
                }
            }else{//根据标题查询
                AnalysisExample analysisExample = new AnalysisExample();
                analysisExample.createCriteria().andTitleEqualTo(keyword);

                ProjectExample projectExample = new ProjectExample();
                ProjectExample.Criteria criteria = projectExample.createCriteria();
                List<Analysis> analyses = analysisMapper.selectByExample(analysisExample);
                for (Analysis a: analyses) {
                    criteria.andPidEqualTo(a.getId());
                    List<Project> projects = projectMapper.selectByExample(projectExample);
                    for (Project p: projects) {
                        AnalysisBean ab = new AnalysisBean();
                        ab.setAnalysis(a);
                        ab.setProject(p);
                        lists.add(ab);
                    }
                }
            }
        }

        return lists;
    }

    /**
     * 导出excel文件
     * @param ids
     */
    public void daochu(String ids) {
        // 创建工作薄
        HSSFWorkbook hwb = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = hwb.createSheet("analysis");
        // 获取数据库需要导出的数据，获取数据库对应表的字段
        // 获取字段
        List<String> list = projectMapper.getSheetName("analysis");       //需要修改表名
        // 获取要导出的数据
        String[] split = ids.split(",");
        List<Map<String,String>> mlist = new ArrayList<Map<String,String>>();


        for(int i = 0; i < split.length; i++){
            Map<String,String> map = new HashMap<String, String>();
            Analysis analysis = analysisMapper.selectByPrimaryKey(Integer.parseInt(split[i]));
            map.put("id",analysis.getId()+"");
            map.put("proname",analysis.getProname()+"");
            map.put("title",analysis.getTitle()+"");
            map.put("simpledis",analysis.getSimpledis()+"");
            map.put("detaileddis",analysis.getDetaileddis()+"");
            map.put("addtime",analysis.getAddtime()+"");
            map.put("updatetime",analysis.getUpdatetime()+"");
            map.put("remark",analysis.getRemark()+"");
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
                // excel的第一行应该是数据库的字段名称
//                if(i == 0){
//                    cell.setCellValue(list.get(j)); // 写入字段
//                }else{
                cell.setCellValue(mlist.get(i-1).get(list.get(j)));
//                }
            }
        }
        // 把excel保存到指定的位置
        try {
            //文件若有需要，则需要修改
            String name = "analysis.xls";
            String path = "e://crmpro//";
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


}
