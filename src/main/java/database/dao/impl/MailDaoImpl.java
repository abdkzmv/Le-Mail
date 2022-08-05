package database.dao.impl;

import database.main.Context;
import database.dao.inter.AbstractDao;
import database.dao.inter.MailDaoInter;
import database.entity.Mail;
import database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MailDaoImpl extends AbstractDao implements MailDaoInter {
    @Override
    public Mail getMail(ResultSet rs) {
        try {
            Integer id = rs.getInt("id");
            User from = Context.getUserDao().getByUsername(rs.getString("from_user"));
            User to = Context.getUserDao().getByUsername(rs.getString("to_user"));
            String subject = rs.getString("title");
            String text = rs.getString("message");
            return new Mail(id,from,to,subject,text);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mail> getAllMails() {
        try(Connection c = connect()) {
            List<Mail> mails = new ArrayList<>();
            Statement stmt = c.createStatement();
            stmt.execute("select * from mail");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                Mail m = getMail(rs);
                mails.add(m);
            }
            return mails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mail> getAllMailsFrom(User u) {
        try(Connection c = connect()) {
            List<Mail> mails = new ArrayList<>();
            PreparedStatement stmt = c.prepareStatement("select * from mail where from_user = ?");
            stmt.setString(1,u.getUsername());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                Mail m = getMail(rs);
                mails.add(m);
            }
            return mails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Mail> getAllMailsTo(User u) {
        try(Connection c = connect()) {
            List<Mail> mails = new ArrayList<>();
            PreparedStatement stmt = c.prepareStatement("select * from mail where to_user = ?");
            stmt.setString(1,u.getUsername());
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while(rs.next()) {
                Mail m = getMail(rs);
                mails.add(m);
            }
            return mails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addMail(Mail m) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into mail (from_user, to_user, title, message) values (?,?,?,?)");
            stmt.setString(1,m.getFrom().getUsername());
            stmt.setString(2,m.getTo().getUsername());
            stmt.setString(3,m.getSubject());
            stmt.setString(4,m.getText());
            return stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Mail generateMail(User from, User to, String title, String subject) {
        return new Mail(from,to,title,subject);
    }
}
