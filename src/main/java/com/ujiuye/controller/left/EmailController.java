package com.ujiuye.controller.left;

import com.ujiuye.pojo.Email;
import com.ujiuye.pojo.Employee;
import com.ujiuye.service.left.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RequestMapping("/mail")
@Controller
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;


    /**
     * 下拉选择收件人
     * @return
     */
    @RequestMapping("/showEmpAndArchives")
    @ResponseBody
    public List<Employee> showEmpAndArchives(){
        return  emailService.getEmployeeAndArchives();
    }

    @RequestMapping("/addAndTurn")
    public String addAndTurn(Email email, MultipartFile file,String reemp) throws ParseException, MessagingException {
        email.setEmpFk(1);
        String[] split = reemp.split(",");
        email.setEmpFk2(Integer.parseInt(split[0]));
        DateFormat df = new SimpleDateFormat("y-M-d h:m:s");
        String format = df.format(new Date());
        Date sendtime = df.parse(format);
        email.setSendtime(sendtime);

        emailService.addEmail(email);           //添加完成

        //发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "GBK");

        helper.setFrom("447420902@qq.com");
        helper.setTo(split[1]);
        helper.setSubject(email.getTitle());
        helper.setText(email.getEmailcontent());

        helper.addAttachment(file.getOriginalFilename(),file);
        javaMailSender.send(mimeMessage);

        System.out.println("发送与保存信息完成");
        return "/message-give.jsp";
    }

    @RequestMapping("/showDetail")
    @ResponseBody
    public List<Email> showall(){
        return emailService.showGivePage();
    }

}
