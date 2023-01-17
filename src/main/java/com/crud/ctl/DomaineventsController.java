package com.crud.ctl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crud.pojo.Domainevents;

/**
 * @author abhijna
 *
 */
@RestController
public class DomaineventsController {

	@Autowired
	private MongoOperations mongoOperation;

	/**
	 * @param oldID GroupID of new netting service
	 * @param newID GroupID of old netting service
	 * @return
	 */
	@GetMapping("/setNewGID/{oldID}/{newID}")
	@ResponseBody
	public List<Domainevents> updateGroupID(@PathVariable String oldID, @PathVariable String newID) {
		

		Map<String, String> ab = new HashMap<String, String>();
		Query query = new Query();
//		final String criteria = "groupCreated\":"; // this can be replaced with 'oldID'
		query.addCriteria(Criteria.where("serializedPayload").regex(oldID));
		List<Domainevents> dm = mongoOperation.find(query, Domainevents.class);
		
		System.out.println("Total documents found : " + dm.size());
		for (Iterator<Domainevents> iterator = dm.iterator(); iterator.hasNext();) {
			Domainevents domainevents = iterator.next();
			String s1 = getGroupID(domainevents.getSerializedPayload());
			ab.put(domainevents.get_id(), s1);
		}
		List<String> ids = getRecID(oldID, ab);

		for (Iterator<String> iterator = ids.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			updateIDs(string, oldID, newID);
		}

		return dm;
	}

	/**
	 * @param recID
	 * @param oldID
	 * @param newID
	 */
	private void updateIDs(String recID, String oldID, String newID) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(recID));
		List<Domainevents> dm = mongoOperation.find(query, Domainevents.class);
		System.out.println(dm.get(0).get_id() + " :\n dm : " + dm.size() + "\n : " + dm.get(0).getSerializedPayload());
		final String spl = dm.get(0).getSerializedPayload();
		final String spl_new = spl.replaceAll(oldID, newID);

		Query query1 = new Query(Criteria.where("_id").is(recID));
		Update update = new Update();//
		System.out.println("old_spl : " + spl);
		System.out.println("spl_new : " + spl_new);
		update.set("serializedPayload", spl_new);
		mongoOperation.findAndModify(query1, update, Domainevents.class);

	}

	/**
	 * @param serializedPayload
	 * @return
	 */
	private String getGroupID(String serializedPayload) {
		int index = serializedPayload.indexOf("\\\"id\\\":");
		String start = serializedPayload.substring(index);
		start = start.substring(0, start.indexOf(",")).replaceAll("\"", "").replaceAll("id", "").replaceAll("\\\\:", "")
				.substring(3);
		return start.substring(0, start.length() - 1);

	}

	/**
	 * @param id
	 * @param ab
	 * @return
	 */
	private List<String> getRecID(String id, Map<String, String> ab) {
		final List<String> keys = new ArrayList<String>();
		final Iterator<String> itr = ab.keySet().iterator();
		while (itr.hasNext()) {
			String key = itr.next();
			if (ab.get(key).equals(id)) {
				keys.add(key);
			}
		}
		return keys;
	}
}
