package com.lawencon.laundry.dao.nativeImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ItemDetailsDao;
import com.lawencon.laundry.entity.ItemDetails;

/**
 * @author Rian Rivaldo
 */
@Repository("item-dtl-nq")
public class ItemDetailsDaoImpl extends BaseDaoImpl<ItemDetails> implements ItemDetailsDao {

  public ItemDetailsDaoImpl() {
	super(ItemDetails.class);
  }

  @Override
  public void insert(ItemDetails data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_item_dtl (code, item_name) ",
	    "VALUES (?1, ?2) RETURNING id");
	Long id = insertWithReturnId(sql, data.getCode(), data.getName());
	data.setId(id);
  }

  @Override
  public void update(ItemDetails data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_item_dtl ",
	    "SET code = ?1, item_name = ?2 ",
	    "WHERE id = ?3 ");
	executeUpdateWithQuery(sql, data.getCode(), data.getName(), data.getId());
  }

  @Override
  public void delete(ItemDetails data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_item_dtl WHERE id = ?1 ", data.getId());
  }

  @Override
  public List<ItemDetails> findAll() throws Exception {
	String sql = "SELECT id, code, item_name FROM tb_m_item_dtl ";
	List<ItemDetails> results = new ArrayList<>();
	getAllWithQuery(sql, objArr -> {
	  ItemDetails item = new ItemDetails(Long.parseLong(objArr[0].toString()), (String) objArr[1], (String) objArr[2]);
	  results.add(item);
	});
	return results;
  }

  @Override
  public ItemDetails findById(Long id) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_item_dtl WHERE id = ?1", id);
  }

  @Override
  public ItemDetails findByCode(String code) throws Exception {
	return getSingleResultWithQuery("SELECT * FROM tb_m_item_dtl WHERE code = ?1", code);
  }

}
