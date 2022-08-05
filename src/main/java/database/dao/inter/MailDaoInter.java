package database.dao.inter;

import database.entity.Mail;
import database.entity.User;

import java.sql.ResultSet;
import java.util.List;

public interface MailDaoInter {
    Mail getMail(ResultSet rs);
    List<Mail> getAllMails();
    List<Mail> getAllMailsFrom(User u);
    List<Mail> getAllMailsTo(User u);
    boolean addMail(Mail m);
    Mail generateMail(User from, User to, String title, String subject);
}
