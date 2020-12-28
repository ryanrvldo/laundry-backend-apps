package com.lawencon.laundry.dao.nativeImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.laundry.dao.ItemDao;
import com.lawencon.laundry.entity.Item;

/**
 * @author Rian Rivaldo
 */
@Repository("item-nq")
public class ItemDaoImpl extends BaseDaoImpl<Item> implements ItemDao {

  public ItemDaoImpl() {
	super(Item.class);
  }

  private final String getItemWithService = buildQueryOf(
      "SELECT i.id, i.cost, i.item_id, dtl.code as dtl_code, dtl.item_name, i.service_id, s.code s_code, s.service_name, s.hour_duration ",
      "FROM tb_m_item i INNER JOIN tb_m_item_dtl dtl ON dtl.id = i.item_id ",
      "INNER JOIN tb_m_service s ON s.id = i.service_id ");

  @Override
  public void insert(Item data) throws Exception {
	String sql = buildQueryOf(
	    "INSERT INTO tb_m_item (cost, item_id, service_id) ",
	    "VALUES (?1, ?2, ?3) RETURNING id");
	Long id = insertWithReturnId(sql, data.getCost(), BigInteger.valueOf(data.getItemDetails().getId()),
	    BigInteger.valueOf(data.getServices().getId()));
	data.setId(id);
  }

  @Override
  public void update(Item data) throws Exception {
	String sql = buildQueryOf(
	    "UPDATE tb_m_item ",
	    "SET item_id = ?1, service_id = ?2, cost = ?3 WHERE id = ?4");
	executeUpdateWithQuery(sql, BigInteger.valueOf(data.getItemDetails().getId()),
	    BigInteger.valueOf(data.getServices().getId()), data.getCost(), BigInteger.valueOf(data.getId()));
  }

  @Override
  public void delete(Item data) throws Exception {
	executeUpdateWithQuery("DELETE FROM tb_m_item WHERE id = ?1", data.getId());
  }

  @Override
  public List<Item> findAll() throws Exception {
	List<Item> itemList = new ArrayList<>();
	getAllWithQuery(getItemWithService, objArr -> itemList.add(setupItem(objArr)));
	return itemList;
  }

  @Override
  public Item findById(Long id) throws Exception {
	String sql = buildQueryOf(getItemWithService, "WHERE i.id = ?1");
	Object obj = getEntityManager().createNativeQuery(sql)
	    .setParameter(1, id)
	    .getSingleResult();
	return setupItem((Object[]) obj);
  }

}
