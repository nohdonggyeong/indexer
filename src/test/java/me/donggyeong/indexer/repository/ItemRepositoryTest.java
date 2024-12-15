package me.donggyeong.indexer.repository;

import me.donggyeong.indexer.config.QuerydslConfig;
import me.donggyeong.indexer.entity.Item;
import me.donggyeong.indexer.enums.Action;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(QuerydslConfig.class)
public class ItemRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void save() {
		// given
		Map<String, Object> documentBody = new HashMap<>();
		documentBody.put("category", "test-category");
		documentBody.put("title", "test-title");
		documentBody.put("description", "test-description");
		Item item = Item.builder()
			.action(Action.CREATE)
			.target("blog")
			.docId("1")
			.docBody(documentBody)
			.build();

		// when
		Item savedItem = itemRepository.save(item);

		// then
		assertThat(savedItem).isNotNull();
		assertThat(savedItem.getId()).isNotNull();
		assertThat(savedItem.getDocBody()).isEqualTo(documentBody);
		assertThat(savedItem.getConsumedAt()).isNotNull();
	}
}