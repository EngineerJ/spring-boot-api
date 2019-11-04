package spring_boot_api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import junit.framework.Assert;
import spring_boot_api.model.Post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class, webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

	 @Autowired
	 private TestRestTemplate restTemplate;
	 
	 @LocalServerPort
	 private int port;
	 
	 private String getRootUrl() {
		 return "http://localhost:"+port;
	 }
	 
	 @Test
	 public void contextLoads() {
		 
	 }
	 
	 @Test
	 public void testGetAllPosts() {
		 HttpHeaders headers = new HttpHeaders();
		 HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		 
		 ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+ "/posts", HttpMethod.GET, entity, String.class);
		 
		 Assert.assertNotNull(response.getBody());
	 }
	 
	 @Test
	 public void testCreatePost() {
		 Post post = new Post();
		 post.setTitle("Test post");
		 post.setBody("Test body");
		 post.setCreatedBy("admin");
		 post.setUpdatedBy("admin");

		 ResponseEntity<Post> postResponse = restTemplate.postForEntity(getRootUrl()+"/posts", post, Post.class);
		 Assert.assertNotNull(postResponse);
		 Assert.assertNotNull(postResponse.getBody());
	 }
	 
	 @Test
	 public void testUpdatePost() {
		 int id = 1;
		 Post post = restTemplate.getForObject(getRootUrl()+"/posts/"+id, Post.class);
		 post.setTitle("Test post");
		 post.setBody("Test body");
		 
		 restTemplate.put(getRootUrl()+"/posts/"+id, Post.class);
		 
		 Post updatedPost = restTemplate.getForObject(getRootUrl()+"/posts/"+id, Post.class);
		 Assert.assertNotNull(updatedPost);
	 }
	 
	 @Test
	 public void testDeletePost() {
		 int id = 2;
		 Post post = restTemplate.getForObject(getRootUrl()+"/users/"+id, Post.class);
		 Assert.assertNotNull(post);
		 
		 restTemplate.delete(getRootUrl()+"/posts/"+id);
		 
		 try {
			 post=restTemplate.getForObject(getRootUrl()+"/posts/"+id, Post.class);
		 } catch(final HttpClientErrorException e) {
			 Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		 }
	 }
}
