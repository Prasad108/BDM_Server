package com.app.Book;

import com.app.BookName.BookName;
import com.app.Languages.Languages;
import com.app.Type.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query("SELECT b FROM Book b,BookName bn, Type t, Languages l " +
            "WHERE " +
            "b.name= bn.id " +
            "AND b.languages= l.id " +
            "AND b.type= t.id " +
            "AND b.name= :name " +
            "AND b.languages= :lang " +
            "AND b.type= :type "
    )
    public Optional<Book> FindByNameTypeLang(@Param("name") BookName bn,@Param("lang") Languages l,@Param("type") Type t);
}
