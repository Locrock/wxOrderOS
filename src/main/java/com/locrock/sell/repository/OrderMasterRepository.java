package com.locrock.sell.repository;

import com.locrock.sell.dataObject.OrderMaster;
import com.locrock.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String openId, Pageable pageable);


}
