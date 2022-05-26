package com.nhnacademy.jdbc.board.index.web;


public class PostEntityTest {


}




//
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
// import com.nhnacademy.jdbc.board.config.RootConfig;
// import com.nhnacademy.jdbc.board.config.WebConfig;
// import com.nhnacademy.jdbc.board.entity.Post;
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;
// import org.assertj.core.api.Assertions;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.ContextHierarchy;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
// import org.springframework.test.context.web.WebAppConfiguration;
// import org.springframework.test.util.ReflectionTestUtils;
// import org.springframework.transaction.annotation.Transactional;
//
//
// @Disabled("temporary")
// @ExtendWith(SpringExtension.class)
// @WebAppConfiguration
// @Transactional
// @ContextHierarchy({
// 	@ContextConfiguration(classes = RootConfig.class),
// 	@ContextConfiguration(classes = WebConfig.class)
// })
// public class PostEntityTest {
//
// 	@PersistenceContext
// 	private EntityManager entityManager;
//
// 	@Test
// 	public void testOrderEntity() {
// 		Post post = entityManager.find(Post.class, 100);
//
// 		//ReflectionTestUtils.invokeGetterMethod == order.getOrderId() 같다.
// 		assertThat(ReflectionTestUtils.invokeGetterMethod(post, "orderId")).isEqualTo(1001L);
// 		assertThat(ReflectionTestUtils.invokeGetterMethod(post, "orderDate")).isNotNull();
// 	}
// }
//
//
// package com.nhnacademy.springjpa.entity;
//
// 	import static org.assertj.core.api.Assertions.assertThat;
//
// 	import com.nhnacademy.springjpa.config.RootConfig;
// 	import com.nhnacademy.springjpa.config.WebConfig;
// 	import javax.persistence.EntityManager;
// 	import javax.persistence.PersistenceContext;
// 	import org.junit.jupiter.api.Disabled;
// 	import org.junit.jupiter.api.Test;
// 	import org.junit.jupiter.api.extension.ExtendWith;
// 	import org.springframework.test.context.ContextConfiguration;
// 	import org.springframework.test.context.ContextHierarchy;
// 	import org.springframework.test.context.junit.jupiter.SpringExtension;
// 	import org.springframework.test.context.web.WebAppConfiguration;
// 	import org.springframework.transaction.annotation.Transactional;
//
// @ExtendWith(SpringExtension.class)
// @WebAppConfiguration
// @Transactional
// @ContextHierarchy({
// 	@ContextConfiguration(classes = RootConfig.class),
// 	@ContextConfiguration(classes = WebConfig.class)
// })
// public class ItemEntityTest {
// 	@PersistenceContext
// 	private EntityManager entityManager;
//
// 	@Test
// 	public void testItemEntity() {
// 		Item item1 = entityManager.find(Item.class, 1L);
// 		Assertions.assertThat(item1.getItemId().longValue()).isEqualTo(1L);
// 	}
//
// }
