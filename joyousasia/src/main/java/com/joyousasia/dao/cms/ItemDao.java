package com.joyousasia.dao.cms;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyousasia.dao.BaseJPARepository;
import com.joyousasia.model.ItemDTO;


@Repository
public interface ItemDao extends BaseJPARepository<ItemDTO, Integer> {

	List<ItemDTO> findByStatus(Integer status);

}
