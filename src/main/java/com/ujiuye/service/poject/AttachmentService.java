package com.ujiuye.service.poject;


import com.ujiuye.pojo.Attachment;

import java.util.List;

public interface AttachmentService {

    /**
     * 附件管理的列表展示
     * @return
     */
    List<Attachment> getAllAttachment();

    /**
     * 查看详情
     * @param id
     * @return
     */
    Attachment getOne(int id);

    /**
     * 删除
     * @param ids
     */
    void del(String ids);

    /**
     * 去s更新
     * @param id
     */
    Attachment toupdateAtt(int id);

    /**
     * 附件添加
     * @param attachment
     */
    void addAttachment(Attachment attachment);

    /**
     * 导出
     * @param ids
     */
    void daochu(String ids);

    /**
     * 修改完成，修改数据库
     * @param attachment
     */
    void update(Attachment attachment);

    /**
     * 修改当前数据的文件路径为已移除
     * @param id
     */
    void updatePath(int id);
}
