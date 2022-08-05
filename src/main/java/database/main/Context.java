package database.main;

import database.dao.impl.MailDaoImpl;
import database.dao.impl.UserDaoImpl;
import database.dao.inter.MailDaoInter;
import database.dao.inter.UserDaoInter;

public class Context {
    private static UserDaoInter userDao = null;
    private static MailDaoInter mailDao= null;

    public static UserDaoInter getUserDao() {
        if(userDao == null) {
            userDao = new UserDaoImpl();
        }
        return userDao;
    }

    public static MailDaoInter getMailDao() {
        if(mailDao == null) {
            mailDao = new MailDaoImpl();
        }
        return mailDao;
    }
}
