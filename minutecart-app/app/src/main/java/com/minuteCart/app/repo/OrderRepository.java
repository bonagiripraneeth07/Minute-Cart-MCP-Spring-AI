package com.minuteCart.app.repo;

import com.minuteCart.app.model.Order;
import com.minuteCart.app.model.Status;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {



    List<Order> findTop4ByOrderByCreatedAtDesc() ;

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :id")
    void updateStatus(@Param("id") long id, @Param("status") Status status);


    List<Order> findByStatusNot(Status status);
}
