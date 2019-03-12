package com.app.Reports;

import com.app.BookName.BookName;
import com.app.Challan.Challan;
import com.app.Languages.Languages;
import com.app.Type.Type;
import com.app.User.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ReportsRepository extends CrudRepository<Challan, Integer> {

    @Query("SELECT b.id, bn.name,l.name,t.name, sum(cb.quantity-cb.returned) as sales,  sum(cb.saleValue) as salevalue FROM" +
            " CbDetails cb, Book b,BookName bn,Languages l,Type t " +
            "where  " +
            "cb.challan IN (SELECT ch.id FROM Challan ch, User u  " +
                            "where  " +
                            "ch.userByIssuedTo = u.id AND " +
                            "ch.userByIssuedTo IN ( SELECT u.id FROM User u WHERE u.center in ( SELECT c.id from User u, Center c WHERE u.center=c.id and u.username= :username ) ) AND " +
            "ch.settled=1  AND " +
            " ch.settledDate between :startDate and :endDate ) AND " +
            "b.languages =l.id AND " +
            "b.name=bn.id AND " +
            "b.type=t.id AND " +
            "cb.book=b.id  " +
            "group by cb.book, bn.name,l.name,t.name")
    public Optional<List<Object[]>> findByUserCenterLangNameandType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("username") String userName);

    @Query("SELECT b.id, bn.name,l.name,t.name, sum(cb.quantity-cb.returned) as sales,  sum(cb.saleValue) as salevalue,u.id, u.name FROM" +
            " CbDetails cb, Book b,BookName bn,Languages l,Type t,User u, Challan ch " +
            "where  " +
            "cb.challan IN (SELECT ch.id FROM Challan ch, User u  " +
                            "where  " +
                            "ch.userByIssuedTo = u.id AND " +
                            "ch.userByIssuedTo IN (:userList) AND " +
            "ch.settled=1  AND " +
            " ch.settledDate between :startDate and :endDate ) AND " +
            "b.languages =l.id AND " +
            "b.name=bn.id AND " +
            "b.type=t.id AND " +
            "cb.book=b.id AND " +
            "ch.userByIssuedTo=u.id AND "+
            "cb.challan=ch.id "+
            "group by u.id,u.name,cb.book, bn.name,l.name,t.name")
    public Optional<List<Object[]>> findByUserListLangNameandType(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("userList") List<User> userList);


}
